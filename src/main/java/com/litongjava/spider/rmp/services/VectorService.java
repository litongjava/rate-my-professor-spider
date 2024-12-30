package com.litongjava.spider.rmp.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.postgresql.util.PGobject;

import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.db.utils.PgVectorUtils;
import com.litongjava.openai.client.OpenAiClient;
import com.litongjava.openai.constants.OpenAiModels;
import com.litongjava.openai.embedding.EmbeddingData;
import com.litongjava.openai.embedding.EmbeddingRequestVo;
import com.litongjava.openai.embedding.EmbeddingResponseVo;
import com.litongjava.openai.utils.EmbeddingVectorUtils;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.tio.utils.crypto.Md5Utils;
import com.litongjava.tio.utils.snowflake.SnowflakeIdUtils;
import com.litongjava.tio.utils.thread.TioThreadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VectorService {

  private final Object vectorLock = new Object();
  private final Object writeLock = new Object();

  public String getVector(String text) {
    String v = null;
    String md5 = Md5Utils.getMD5(text);
    String sql = String.format("select v from %s where md5=? and m=?", TableNames.rumi_embedding);
    PGobject pGobject = Db.queryFirst(sql, md5, OpenAiModels.text_embedding_3_large);
    if (pGobject != null) {
      v = pGobject.getValue();
    } else {
      float[] embeddingArray = null;
      synchronized (vectorLock) {
        embeddingArray = OpenAiClient.embeddingArray(text, OpenAiModels.text_embedding_3_large);
      }

      String string = Arrays.toString(embeddingArray);
      long id = SnowflakeIdUtils.id();
      v = (String) string;
      PGobject pgVector = PgVectorUtils.getPgVector(v);
      Row saveRecord = new Row().set("t", text).set("v", pgVector).set("id", id).set("md5", md5)
          //
          .set("m", OpenAiModels.text_embedding_3_large);
      synchronized (writeLock) {
        Db.save("rumi_embedding", saveRecord);
      }
    }
    return v;
  }

  public synchronized EmbeddingResponseVo getVector(String text, String model) {
    String md5 = Md5Utils.getMD5(text);
    String sql = String.format("select v from %s where md5=? and m=?", TableNames.rumi_embedding);
    PGobject pGobject = Db.queryFirst(sql, md5, model);
    if (pGobject != null) {
      String value = pGobject.getValue();
      float[] floats = EmbeddingVectorUtils.toFloats(value);
      List<EmbeddingData> lists = new ArrayList<>(1);
      EmbeddingData embeddingData = new EmbeddingData();
      embeddingData.setEmbedding(floats);
      lists.add(embeddingData);

      EmbeddingResponseVo embeddingResponseVo = new EmbeddingResponseVo();
      embeddingResponseVo.setModel(model);
      embeddingResponseVo.setData(lists);
      return embeddingResponseVo;
    } else {
      EmbeddingRequestVo embeddingRequestVo = new EmbeddingRequestVo(text, model);
      EmbeddingResponseVo embeddingResponseVo = null;
      synchronized (vectorLock) {
        embeddingResponseVo = OpenAiClient.embeddings(embeddingRequestVo);
      }

      float[] embeddingArray = embeddingResponseVo.getData().get(0).getEmbedding();
      String string = Arrays.toString(embeddingArray);

      TioThreadUtils.submit(() -> {
        long id = SnowflakeIdUtils.id();
        PGobject pgVector = PgVectorUtils.getPgVector(string);
        Row saveRecord = Row.by("id", id).set("md5", md5).set("t", text).set("v", pgVector)
            //
            .set("m", model).setTableName(TableNames.rumi_embedding);
        synchronized (writeLock) {
          Db.save(saveRecord);
        }

      });

      return embeddingResponseVo;

    }
  }
}
