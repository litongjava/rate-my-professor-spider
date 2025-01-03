package com.litongjava.spider.rmp.services;

import java.io.IOException;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.client.RMPGraphqlClient;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.utils.TelegramNotificaitonUtils;
import com.litongjava.tio.utils.json.FastJson2Utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

@Slf4j
public class RmpTeacherSpiderService {
  RmpProfessorService service = Aop.get(RmpProfessorService.class);

  public void spiderAllTeacher() {
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    RmpProfessorService service = Aop.get(RmpProfessorService.class);
    int notFoundCount = 0;
    long i = 0;
    while (true) {
      i++;
      log.info("fetch:{}", i);
      if (Db.exists(TableNames.rumi_rmp_professor, "id", i)) {
        continue;
      }
      if (Db.exists(TableNames.rumi_rmp_professor_not_found_id, "id", i)) {
        continue;
      }

      try (Response response = rmpGraphqlClient.getTeacherDetailsById(i)) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e1) {
          e1.printStackTrace();
        }
        if (response.isSuccessful()) {
          String string = response.body().string();
          try {
            service.saveProfessorDetail(i, string);
          } catch (Exception e) {
            log.error("Failed to save:{}", i, e);
          }
          notFoundCount = 0;
        } else {
          log.error("Failed to fetch:{},code:{},body:{}", i, response.code(), response.body().toString());
          notFoundCount++;
          log.info("End to fetch:{} notFoundCount:{}", i, notFoundCount);

        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void professorsBySchoolId(Long schoolId) {
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    Integer count = 0;
    try {
      count = rmpGraphqlClient.countTeacherBySchool(881L);
      log.info("count:{}", 4732);
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
        long start = System.currentTimeMillis();
        try (Response response = rmpGraphqlClient.pageTeacherBySchoolId(schoolId, i - 1, pageSize)) {
          long end = System.currentTimeMillis();
          String msg = "fetch " + schoolId + " " + i + " elapsed " + (end - start);
          log.info(msg);
          TelegramNotificaitonUtils.pushtoAdmin(msg);
          String string = response.body().string();
          save(string);
        } catch (Exception e) {
          log.error("Failed to save:" + i, e);
        }
      }
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
