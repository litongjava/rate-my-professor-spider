package com.litongjava.spider.rmp.client;

import com.litongjava.model.http.response.ResponseVo;
import com.litongjava.tio.utils.http.Http;

public class QyIpProxyClient {

  public static final String API_ENDPOINT = "http://gev.qydailiip.com/api/?apikey=%s&num=%d&type=json&line=win&proxy_type=secret&end_time=1";

  public static String getClient(String apiKey, int num) {
    String url = String.format(API_ENDPOINT, apiKey, num);
    ResponseVo responseVo = Http.get(url);
    String bodyString = responseVo.getBodyString();
    return bodyString;
  }
}
