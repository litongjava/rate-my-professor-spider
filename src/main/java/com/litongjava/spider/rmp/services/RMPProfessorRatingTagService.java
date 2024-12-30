package com.litongjava.spider.rmp.services;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpProfessorRatingTag;
import com.litongjava.spider.rmp.model.RumiRmpProfessorRatingTagMapping;
import com.litongjava.tio.utils.encoder.Base64Utils;
import com.litongjava.tio.utils.snowflake.SnowflakeIdUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RMPProfessorRatingTagService {

  public void saveIfNotExists(JSONArray jsonArray) {
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject tag = jsonArray.getJSONObject(i);
      String idBase64 = tag.getString("id");
      Long id = 0L;
      String idString = Base64Utils.decodeToString(idBase64);
      if (idString != null) {
        String[] array = idString.split("-");
        if (array.length > 1) {
          id = Long.valueOf(array[1]);
        } else {
          log.error("please check the id of record");
          return;
        }
      }
      boolean exists = Db.exists(TableNames.rumi_rmp_professor_rating_tag, "id", id);
      if (exists) {
        return;
      }
      String tagName = tag.getString("tagName");
      RumiRmpProfessorRatingTag bean = new RumiRmpProfessorRatingTag();
      bean.setId(id).setTagName(tagName);
      boolean save = bean.save();
      if (!save) {
        log.error("Failed to save:{},{}", id, tagName);
      }
    }

  }

  public void update(Long id, JSONArray teacherRatingTags) {
    saveIfNotExists(teacherRatingTags);
    
    List<RumiRmpProfessorRatingTagMapping> saveList = new ArrayList<>(teacherRatingTags.size());
    for (int i = 0; i < teacherRatingTags.size(); i++) {
      JSONObject jsonObject = teacherRatingTags.getJSONObject(i);
      String[] idString = Base64Utils.decodeToString(jsonObject.getString("id")).split("-");
      Long tagId = Long.valueOf(idString[1]);
      Integer integer = jsonObject.getInteger("tagCount");
      RumiRmpProfessorRatingTagMapping model = new RumiRmpProfessorRatingTagMapping();
      model.setId(SnowflakeIdUtils.id()).setTeacherId(id).setTagId(tagId).setTagCount(integer);
      saveList.add(model);
    }

    Db.tx(() -> {
      String sql = String.format("delete from %s where teacher_id=?", TableNames.rumi_rmp_professor_rating_tag_mapping);
      Db.delete(sql, id);
      Db.batchSave(saveList, 100);
      return true;
    });

  }
}
