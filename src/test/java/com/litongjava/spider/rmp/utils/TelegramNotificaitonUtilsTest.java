package com.litongjava.spider.rmp.utils;

import org.junit.AfterClass;
import org.junit.Test;

public class TelegramNotificaitonUtilsTest {

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Test
  public void test() {
    TelegramNotificaitonUtils.pushToAdmin("fetch");
  }

}
