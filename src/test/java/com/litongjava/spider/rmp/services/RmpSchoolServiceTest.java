package com.litongjava.spider.rmp.services;

import java.io.File;
import java.util.Date;

import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.config.AppConfig;
import com.litongjava.spider.rmp.model.RumiRmpSchool;
import com.litongjava.tio.boot.testing.TioBootTest;
import com.litongjava.tio.utils.hutool.FileUtil;

public class RmpSchoolServiceTest {

  @Test
  public void test() {
    TioBootTest.runWith(AppConfig.class);
    RumiRmpSchool rumiRmpSchool = new RumiRmpSchool();
    rumiRmpSchool.setId(0L);
    rumiRmpSchool.setName("SJSJ");
    rumiRmpSchool.setCreateTime(new Date());

    rumiRmpSchool.save();
  }

  @Test
  public void testSaveSchoolDetail() {
    TioBootTest.runWith(AppConfig.class);
    String file = "F:\\document\\dev-docs\\02_Java\\03_Java-EE\\35_GraphQL\\school_detail.txt";
    String jsonString = FileUtil.readString(new File(file));
    Aop.get(RmpSchoolService.class).saveSchoolDetail(jsonString);
  }

}
