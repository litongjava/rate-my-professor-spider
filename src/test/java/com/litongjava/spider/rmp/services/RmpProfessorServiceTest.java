package com.litongjava.spider.rmp.services;

import java.io.File;

import org.junit.AfterClass;
import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.config.AppConfig;
import com.litongjava.tio.boot.testing.TioBootTest;
import com.litongjava.tio.utils.hutool.FileUtil;

public class RmpProfessorServiceTest {

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void test() {
    TioBootTest.runWith(AppConfig.class);
    String filePath = "F:\\document\\dev-docs\\02_Java\\03_Java-EE\\35_GraphQL\\teacher_details.txt";
    String jsonString = FileUtil.readString(new File(filePath));
    Aop.get(RmpProfessorService.class).saveProfessorDetail(1L, jsonString);
  }

}
