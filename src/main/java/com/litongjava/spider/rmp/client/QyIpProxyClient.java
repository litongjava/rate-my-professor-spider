package com.litongjava.spider.rmp.client;

import java.net.InetSocketAddress;
import java.net.Proxy;

import com.alibaba.fastjson2.JSONArray;
import com.litongjava.model.http.response.ResponseVo;
import com.litongjava.tio.utils.http.Http;
import com.litongjava.tio.utils.json.FastJson2Utils;

import okhttp3.OkHttpClient;

public class QyIpProxyClient {

  public static final String API_ENDPOINT = "http://gev.qydailiip.com/api/?apikey=%s&num=1&type=json&line=win&proxy_type=secret&end_time=1";

  public static OkHttpClient getClient(String apiKey) {
    String url = String.format(API_ENDPOINT, apiKey);
    ResponseVo responseVo = Http.get(url);
    String bodyString = responseVo.getBodyString();
    JSONArray array = FastJson2Utils.parseArray(bodyString);
    String proxyString = array.getJSONObject(0).getString("proxy");
    String[] split = proxyString.split(":");

    // 创建代理
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(split[0], Integer.valueOf(split[1])));

    return new OkHttpClient.Builder().proxy(proxy).build();

  }
}
