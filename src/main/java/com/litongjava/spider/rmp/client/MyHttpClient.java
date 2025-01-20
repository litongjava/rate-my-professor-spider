package com.litongjava.spider.rmp.client;

import java.net.InetSocketAddress;
import java.net.Proxy;

import com.litongjava.tio.utils.environment.EnvUtils;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class MyHttpClient {

  // 代理配置信息
  private static final String PROXY_HOST = EnvUtils.getStr("PROXY_HOST");
  private static final int PROXY_PORT = EnvUtils.getInt("PROXY_PORT");
  private static final String PROXY_USERNAME = EnvUtils.getStr("PROXY_USERNAME");
  private static final String PROXY_PASSWORD = EnvUtils.getStr("PROXY_PASSWORD");

  //使用代理的 OkHttpClient
  public static final OkHttpClient httpClient = createHttpClientWithProxy();

  // 创建带有代理和认证的 OkHttpClient 实例
  private static OkHttpClient createHttpClientWithProxy() {
    // 创建代理
    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));

    // 设置代理认证器
    Authenticator proxyAuthenticator = new Authenticator() {
      @Override
      public Request authenticate(Route route, Response response) {
        String credential = Credentials.basic(PROXY_USERNAME, PROXY_PASSWORD);
        return response.request().newBuilder().header("Proxy-Authorization", credential).build();
      }
    };

    return new OkHttpClient.Builder().proxy(proxy).proxyAuthenticator(proxyAuthenticator).build();
  }

}
