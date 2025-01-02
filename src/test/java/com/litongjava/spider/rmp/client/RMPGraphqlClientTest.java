package com.litongjava.spider.rmp.client;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.Test;

import com.litongjava.jfinal.aop.Aop;

import okhttp3.Response;

public class RMPGraphqlClientTest {

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void getSchoolDetailsById() {
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    try {
      Response response = rmpGraphqlClient.getSchoolDetailsById(1L);
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void getTeacherDetailsById() {
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    try {
      Response response = rmpGraphqlClient.getTeacherDetailsById(1L);
      System.out.println(response.body().string());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void pageTeacherBySchoolId() {
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    Long schoolId = 881L;
    for (int i = 0; i < 100; i += 10) {
      try {
        Response response = rmpGraphqlClient.pageTeacherBySchoolId(schoolId, i - 1, 10);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(response.body().string());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Test
  public void countTeacherBySchool() {
    RMPGraphqlClient rmpGraphqlClient = Aop.get(RMPGraphqlClient.class);
    try {
      Integer count = rmpGraphqlClient.countTeacherBySchool(881L);
      System.out.println(count);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
