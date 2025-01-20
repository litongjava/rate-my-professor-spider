package com.litongjava.spider.rmp.utils;

import com.litongjava.tio.utils.http.HttpUtils;

public class TelegramNotificaitonUtils {

  public static final String url = "https://api-gpt-translator.max-kb.com/api/v1/telegram/sendMessage?chatId=7752895627&content=";

  public static void pushToAdmin(String text) {
    String targetUrl = url + text;
    HttpUtils.get(targetUrl);
  }
}
