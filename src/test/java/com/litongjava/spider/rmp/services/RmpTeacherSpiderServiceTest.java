package com.litongjava.spider.rmp.services;

import java.io.File;

import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.config.AppConfig;
import com.litongjava.tio.boot.testing.TioBootTest;
import com.litongjava.tio.utils.hutool.FileUtil;

public class RmpTeacherSpiderServiceTest {

  @Test
  public void test() {
    TioBootTest.runWith(AppConfig.class);
    Aop.get(RmpTeacherSpiderService.class).spiderAllTeacher();
  }

  @Test
  public void testSave() {
    TioBootTest.runWith(AppConfig.class);
    String string = FileUtil.readString(new File("F:\\document\\dev-docs\\02_Java\\03_Java-EE\\35_GraphQL\\sjsu-1-8.txt"));
    Aop.get(RmpTeacherSpiderService.class).save(string);
  }

}
