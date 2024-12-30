package com.litongjava.spider.rmp.services;

import java.io.IOException;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.client.RMPGraphqlClient;

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
      try {
        Response response = rmpGraphqlClient.getTeacherDetailsById(i);
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
          log.error("Failed to fetch:{}", i);
          notFoundCount++;
          if (notFoundCount > 3) {
            break;
          }
          log.info("End to fetch:{}", i);

        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
