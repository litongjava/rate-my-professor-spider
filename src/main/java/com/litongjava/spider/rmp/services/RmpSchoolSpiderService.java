package com.litongjava.spider.rmp.services;

import java.io.IOException;

import com.litongjava.db.activerecord.Db;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.client.RMPGraphqlClient;
import com.litongjava.spider.rmp.constants.TableNames;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;

@Slf4j
public class RmpSchoolSpiderService {

  RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
  RmpSchoolService rmpSchoolService = Aop.get(RmpSchoolService.class);

  public void spiderAllSchool() {

    for (Long i = 1L; i < 2048L; i++) {
      try {
        fetchAndSave(i);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void fetchAndSave(Long i) throws IOException {
    Response response = rmpGraphqlClient.getSchoolDetailsById(i);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    if (response.isSuccessful()) {
      String string = response.body().string();
      try {
        rmpSchoolService.saveSchoolDetail(string);
      } catch (Exception e) {
        log.error("Failed to svae:{}", i, e);
      }
    } else {
      log.error("Failed to fetch:{}", i);
    }
  }

  public void fetchAndSaveIfNotExists(Long schoolId) {
    if (!Db.exists(TableNames.rumi_rmp_school, "id", schoolId)) {
      try {
        fetchAndSave(schoolId);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
