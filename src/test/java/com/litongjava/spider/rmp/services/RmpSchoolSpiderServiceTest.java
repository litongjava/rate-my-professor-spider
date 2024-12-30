package com.litongjava.spider.rmp.services;

import org.junit.AfterClass;
import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.config.AppConfig;
import com.litongjava.tio.boot.testing.TioBootTest;

public class RmpSchoolSpiderServiceTest {

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void test() {
    TioBootTest.runWith(AppConfig.class);
    Aop.get(RmpSchoolSpiderService.class).spiderAllSchool();
  }

}
