package com.litongjava.spider.rmp.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import com.alibaba.fastjson2.JSONArray;
import com.litongjava.tio.utils.environment.EnvUtils;
import com.litongjava.tio.utils.json.FastJson2Utils;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class RMPGraphqlClientUtils {

  public static final RMPGraphqlClient rmpClient = new RMPGraphqlClient();

  private static OkHttpClient cachedClient;
  private static long expireTime = 0L;

  public static Response getTeacherDetailsById(long id) throws IOException {
    OkHttpClient client = getProxyClient();
    return rmpClient.getTeacherDetailsById(client, id);
  }

  public static Response getSchoolDetailsById(Long i) throws IOException {
    OkHttpClient client = getProxyClient();
    return rmpClient.getSchoolDetailsById(client, i);
  }

  private static synchronized OkHttpClient getProxyClient() throws IOException {
    // 1. 如果当前时间还没到过期时间，直接返回缓存的client
    if (System.currentTimeMillis() < expireTime && cachedClient != null) {
      return cachedClient;
    }

    String apiKey = EnvUtils.getStr("QY_API_KEY");
    String bodyString = QyIpProxyClient.getClient(apiKey, 10);
    JSONArray array = FastJson2Utils.parseArray(bodyString);
    for (int i = 0; i < 10; i++) {
      String proxyString = array.getJSONObject(i).getString("proxy");
      String[] split = proxyString.split(":");

      // 创建代理
      Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(split[0], Integer.valueOf(split[1])));
      OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(proxy).build();
      String url = "https://www.ratemyprofessors.com/";
      okhttp3.Request request = new okhttp3.Request.Builder().url(url).head().build();

      try (Response response = okHttpClient.newCall(request).execute()) {
        if (response.isSuccessful()) {
          cachedClient = okHttpClient;
          // 设置 2 分钟以后过期
          expireTime = System.currentTimeMillis() + 2 * 60 * 1000;
          return cachedClient;
        }
      } catch (IOException e) {
        continue;
      }
    }
    return getProxyClient();
  }

}
