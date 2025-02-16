package com.litongjava.spider.rmp.services;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.client.RMPGraphqlClient;
import com.litongjava.spider.rmp.client.RMPGraphqlClientUtils;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.utils.TelegramNotificaitonUtils;
import com.litongjava.tio.utils.json.FastJson2Utils;
import com.litongjava.tio.utils.thread.TioThreadUtils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

@Slf4j
public class RmpTeacherSpiderService {
  RmpProfessorService service = Aop.get(RmpProfessorService.class);
  private AtomicInteger notFoundCount = new AtomicInteger(0);

  public void spiderAllTeacher() {

    RmpProfessorService service = Aop.get(RmpProfessorService.class);

    for (long i = 2928065; i < 3500000; i++) {
      log.info("fetch:{}", i);
      if (Db.exists(TableNames.rumi_rmp_professor, "id", i)) {
        continue;
      }
      if (Db.exists(TableNames.rumi_rmp_professor_not_found_id, "id", i)) {
        continue;
      }

      long j = i;
      TioThreadUtils.execute(() -> {
        fetchAndSave(service, j);
      });
    }
  }

  private void fetchAndSave(RmpProfessorService service, long i) {
    try (Response response = RMPGraphqlClientUtils.getTeacherDetailsById(i)) {
      if (response.isSuccessful()) {
        String string = response.body().string();
        try {
          service.saveProfessorDetail(i, string);
        } catch (Exception e) {
          log.error("Failed to save:{}", i, e);
        }
        notFoundCount.set(0);
      } else {
        log.error("Failed to fetch:{},code:{},body:{}", i, response.code(), response.body().toString());
        int current = notFoundCount.incrementAndGet();
        log.info("End to fetch:{} notFoundCount:{}", i, notFoundCount);
        if (current > 30) {
          return;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void professorsBySchoolId(Long schoolId) {
    long start = System.currentTimeMillis();
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    Integer count = 0;
    try {
      count = rmpGraphqlClient.countTeacherBySchool(schoolId);
      log.info("count:{}", count);
    } catch (IOException e) {
      e.printStackTrace();
    }

    int pageSize = 100;
    if (count > 0) {
      for (int i = 0; i < count; i += pageSize) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        long start1 = System.currentTimeMillis();
        try (Response response = rmpGraphqlClient.pageTeacherBySchoolId(schoolId, i - 1, pageSize)) {
          long end = System.currentTimeMillis();
          String msg = "fetch " + schoolId + " " + i + " elapsed " + (end - start1);
          log.info(msg);
          TelegramNotificaitonUtils.pushToAdmin(msg);
          String string = response.body().string();
          save(string);
        } catch (Exception e) {
          log.error("Failed to save:" + i, e);
          TelegramNotificaitonUtils.pushToAdmin(e.getMessage());
        }
      }
      long end = System.currentTimeMillis();
      String msg = "finish " + schoolId + " " + count + " elapsed " + (end - start);
      TelegramNotificaitonUtils.pushToAdmin(msg);
      log.info(msg);
    }

  }

  public void save(String string) {
    JSONArray edges = FastJson2Utils.parseObject(string).getJSONObject("data").getJSONObject("search")
        //
        .getJSONObject("teachers").getJSONArray("edges");
    for (int j = 0; j < edges.size(); j++) {
      JSONObject node = edges.getJSONObject(j).getJSONObject("node");
      service.saveProfessorDetail(node);
    }

  }

}
