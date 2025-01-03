package com.litongjava.spider.rmp.utils;

import com.litongjava.tio.utils.http.Http;

public class NotificationUtils {

  String url = "https://api-gpt-translator.max-kb.com/api/v1/telegram/sendMessage?chatId=7752895627&content=";

  public void pushtoAdmin(String text) {
    Http.get(url + text);
  }
}
