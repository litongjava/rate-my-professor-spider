package com.litongjava.spider.rmp.services;

import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.config.AppConfig;
import com.litongjava.tio.boot.testing.TioBootTest;

public class RmpTeacherSpiderServiceTest {

  @Test
  public void test() {
    TioBootTest.runWith(AppConfig.class);
    Aop.get(RmpTeacherSpiderService.class).spiderAllTeacher();
  }

}
