package com.litongjava.spider.rmp.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.litongjava.tio.utils.encoder.Base64Utils;
import com.litongjava.tio.utils.http.OkHttpClientPool;
import com.litongjava.tio.utils.hutool.FileUtil;
import com.litongjava.tio.utils.hutool.ResourceUtil;
import com.litongjava.tio.utils.json.FastJson2Utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RMPGraphqlClient {
  private String serverUrl = "https://www.ratemyprofessors.com/graphql";
  private String authorization = "Basic dGVzdDp0ZXN0";
  private String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36";

  /**
   * 1.发送请求
   * 2.解析数据
   * 3.入库
   * String name = "Andrew Carter";
   * String base64SchoolId = "U2Nob29sLTg4MQ==";
   * @return 
   * @throws IOException 
   */
  public Response teacherSearch(String name, Long schoolId) throws IOException {
    String query = FileUtil.readURLAsString(ResourceUtil.getResource("query_teacher.txt")).toString();

    String base64SchoolId = Base64Utils.encodeToString("School-" + schoolId);

    Map<String, Object> variablesQuery = new HashMap<>();
    variablesQuery.put("text", name);
    variablesQuery.put("schoolID", base64SchoolId);
    variablesQuery.put("fallback", false);
    variablesQuery.put("departmentID", null);

    Map<String, Object> variables = new HashMap<>();
    variables.put("query", variablesQuery);

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("query", query);
    payloadMap.put("variables", variables);

    String payload = FastJson2Utils.toJson(payloadMap);

    MediaType mediaType = MediaType.parse("application/json");
    @SuppressWarnings("deprecation")
    RequestBody body = RequestBody.create(mediaType, payload);

    // request
    Request request = new Request.Builder().url(serverUrl).method("POST", body).addHeader("Authorization", authorization).addHeader("User-Agent", userAgent)
        .addHeader("Content-Type", "application/json").build();
    return MyHttpClient.httpClient.newCall(request).execute();

  }

  public Response getSchoolDetailsById(Long schoolId) throws IOException {
    String query = FileUtil.readURLAsString(ResourceUtil.getResource("GetSchoolDetailsById.txt")).toString();

    String base64SchoolId = Base64Utils.encodeToString("School-" + schoolId);

    Map<String, Object> variables = new HashMap<>();
    variables.put("schoolID", base64SchoolId);

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("query", query);
    payloadMap.put("variables", variables);

    String payload = FastJson2Utils.toJson(payloadMap);

    OkHttpClient httpClient = OkHttpClientPool.getHttpClient();

    MediaType mediaType = MediaType.parse("application/json");
    @SuppressWarnings("deprecation")
    RequestBody body = RequestBody.create(mediaType, payload);

    // request
    Request request = new Request.Builder().url(serverUrl).method("POST", body)
        //
        .addHeader("Authorization", authorization).addHeader("User-Agent", userAgent)
        //
        .addHeader("Content-Type", "application/json")
        //
        .build();
    return httpClient.newCall(request).execute();
  }

  public Response getTeacherDetailsById(Long id) throws IOException {
    String query = FileUtil.readURLAsString(ResourceUtil.getResource("GetTeacherById.txt")).toString();

    String base64SchoolId = Base64Utils.encodeToString("Teacher-" + id);

    Map<String, Object> variables = new HashMap<>();
    variables.put("id", base64SchoolId);

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("query", query);
    payloadMap.put("variables", variables);

    String payload = FastJson2Utils.toJson(payloadMap);


    MediaType mediaType = MediaType.parse("application/json");
    @SuppressWarnings("deprecation")
    RequestBody body = RequestBody.create(mediaType, payload);

    // request
    Request request = new Request.Builder().url(serverUrl).method("POST", body)
        //
        .addHeader("Authorization", authorization).addHeader("User-Agent", userAgent)
        //
        .addHeader("Content-Type", "application/json").build();
    return MyHttpClient.httpClient.newCall(request).execute();
  }

  public Response pageTeacherBySchoolId(Long schoolId, Integer cursorInt, Integer count) throws IOException {
    String query = FileUtil.readURLAsString(ResourceUtil.getResource("TeacherSearchPaginationQueryBySchool.txt")).toString();

    String base64SchoolId = Base64Utils.encodeToString("School-" + schoolId);

    Map<String, Object> variablesQuery = new HashMap<>();
    variablesQuery.put("text", "");
    variablesQuery.put("schoolID", base64SchoolId);
    variablesQuery.put("fallback", false);

    Map<String, Object> variables = new HashMap<>();
    variables.put("query", variablesQuery);
    variables.put("count", count);
    if (cursorInt > 1) {
      String base64cursor = Base64Utils.encodeToString("arrayconnection:" + cursorInt);
      variables.put("cursor", base64cursor);
    } else {
      variables.put("cursor", "");
    }

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("query", query);
    payloadMap.put("variables", variables);

    String payload = FastJson2Utils.toJson(payloadMap);


    MediaType mediaType = MediaType.parse("application/json");
    @SuppressWarnings("deprecation")
    RequestBody body = RequestBody.create(mediaType, payload);

    // request
    Request request = new Request.Builder().url(serverUrl).method("POST", body)
        //
        .addHeader("Authorization", authorization).addHeader("User-Agent", userAgent)
        //
        .addHeader("Content-Type", "application/json").build();
    return MyHttpClient.httpClient.newCall(request).execute();

  }

  public Integer countTeacherBySchool(Long schoolId) throws IOException {
    String query = FileUtil.readURLAsString(ResourceUtil.getResource("CounTeacherBySchool.txt")).toString();

    String base64SchoolId = Base64Utils.encodeToString("School-" + schoolId);

    Map<String, Object> variablesQuery = new HashMap<>();
    variablesQuery.put("text", "");
    variablesQuery.put("schoolID", base64SchoolId);
    variablesQuery.put("fallback", false);

    Map<String, Object> variables = new HashMap<>();
    variables.put("query", variablesQuery);
    variables.put("count", 1);
    variables.put("cursor", "");

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("query", query);
    payloadMap.put("variables", variables);

    String payload = FastJson2Utils.toJson(payloadMap);


    MediaType mediaType = MediaType.parse("application/json");
    @SuppressWarnings("deprecation")
    RequestBody body = RequestBody.create(mediaType, payload);

    // request
    Request request = new Request.Builder().url(serverUrl).method("POST", body)
        //
        .addHeader("Authorization", authorization).addHeader("User-Agent", userAgent)
        //
        .addHeader("Content-Type", "application/json").build();
    try (Response response = MyHttpClient.httpClient.newCall(request).execute()) {
      String string = response.body().string();
      JSONObject jsonObject = FastJson2Utils.parseObject(string);
      return jsonObject.getJSONObject("data").getJSONObject("search").getJSONObject("teachers").getInteger("resultCount");
    }
  }
}
