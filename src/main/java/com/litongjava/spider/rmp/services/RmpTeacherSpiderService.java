package com.litongjava.spider.rmp.services;

import java.io.IOException;

import com.litongjava.db.activerecord.Db;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.client.RMPGraphqlClient;
import com.litongjava.spider.rmp.constants.TableNames;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

@Slf4j
public class RmpTeacherSpiderService {

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

}
