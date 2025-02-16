package com.litongjava.spider.rmp.client;

import java.io.IOException;

import org.junit.Test;

import com.litongjava.tio.utils.environment.EnvUtils;

import okhttp3.Response;

public class RMPGraphqlClientUtilsTest {
  @Test
  public void test() {
    EnvUtils.load();
    try (Response response = RMPGraphqlClientUtils.getTeacherDetailsById(2729642L)) {
      System.out.println(response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
