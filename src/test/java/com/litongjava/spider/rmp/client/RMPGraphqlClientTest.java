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
      // TODO Auto-generated catch block
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


}
