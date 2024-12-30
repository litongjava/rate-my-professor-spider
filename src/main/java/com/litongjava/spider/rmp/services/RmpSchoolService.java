package com.litongjava.spider.rmp.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpSchool;
import com.litongjava.tio.utils.encoder.Base64Utils;
import com.litongjava.tio.utils.json.FastJson2Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RmpSchoolService {

  public static final Map<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();

  public void saveIfNotExists(Long id, Row record) {

    boolean exists = Db.exists(TableNames.rumi_rmp_school, "id", id);
    if (!exists) {
      ReentrantLock lock = lockMap.computeIfAbsent(id, k -> new ReentrantLock());
      lock.lock();
      try {
        exists = Db.exists(TableNames.rumi_rmp_school, "id", id);
        log.info("insert table:{},{}", TableNames.rumi_rmp_school, id);
        record.set("id", id);
        Db.save(TableNames.rumi_rmp_school, record);
      } finally {
        lock.unlock();
        lockMap.remove(id);
      }
    }
  }

  public void saveSchoolDetail(String jsonString) {
    JSONObject jsonObject = FastJson2Utils.parseObject(jsonString);
    JSONObject school = jsonObject.getJSONObject("data").getJSONObject("school");
    if(school!=null) {
      String idString = school.getString("id");
      String[] split = Base64Utils.decodeToString(idString).split("-");
      Long id = Long.valueOf(split[1]);
      save(school, id);

      JSONArray departments = school.getJSONArray("departments");
      if (departments != null) {
        Aop.get(RmpSchoolDepartmentService.class).save(id, departments);
      }

      JSONObject summary = school.getJSONObject("summary");
      if (summary != null) {
        Aop.get(RmpSchoolSummaryService.class).save(id, summary);
      }

      JSONObject ratings = school.getJSONObject("ratings");
      if (ratings != null) {
        JSONArray edges = ratings.getJSONArray("edges");
        if (edges != null) {

          Aop.get(RmpSchooRatingService.class).save(id, edges);
        }
      }
    }

  }

  private void save(JSONObject school, Long id) {
    BigDecimal avgRatingRounded = school.getBigDecimal("avgRatingRounded");

    RumiRmpSchool rumiRmpSchool = new RumiRmpSchool();
    rumiRmpSchool.setAvgRatingRounded(avgRatingRounded);
    rumiRmpSchool.setCity(school.getString("city"));
    rumiRmpSchool.setCountry(school.getString("country"));
    rumiRmpSchool.setId(id);
    rumiRmpSchool.setLegacyId(school.getInteger("legacyId"));
    rumiRmpSchool.setName(school.getString("name"));
    rumiRmpSchool.setNumRatings(school.getInteger("numRatings"));
    rumiRmpSchool.setState(school.getString("state"));
    Db.tx(() -> {
      Db.deleteById(TableNames.rumi_rmp_school, id);
      rumiRmpSchool.save();
      return true;
    });
  }
}
