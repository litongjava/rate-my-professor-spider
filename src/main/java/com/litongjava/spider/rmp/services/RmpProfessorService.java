package com.litongjava.spider.rmp.services;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpProfessor;
import com.litongjava.tio.utils.encoder.Base64Utils;
import com.litongjava.tio.utils.json.FastJson2Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RmpProfessorService {
  public void saveOrUpdate(Long teacherId, Row professor, String sourceUrl) {
    boolean exists = Db.exists(TableNames.rmp_professor, "id", teacherId);
    if (exists) {
      log.info("update table:{},{}", TableNames.rmp_professor, teacherId);
      Db.update(TableNames.rmp_professor, "id", professor);

    } else {
      log.info("insert table:{},{}", TableNames.rmp_professor, teacherId);
      Db.save(TableNames.rmp_professor, professor);
    }
  }

  private void save(String sourceUrl, Long id, Long mostUsefulRatingId, JSONObject jsonObject) {
    String firstName = jsonObject.getString("firstName");
    String lastName = jsonObject.getString("lastName");
    String name = firstName + " " + lastName;

    jsonObject.remove("id");
    RumiRmpProfessor m = jsonObject.toJavaObject(RumiRmpProfessor.class);
    m.setId(id);
    m.setName(name);
    m.setMostUsefulRatingId(mostUsefulRatingId);
    m.setSourceUrl(sourceUrl);

    Db.tx(() -> {
      Db.deleteById(RumiRmpProfessor.tableName, id);
      m.save();
      return true;
    });

  }

  public void saveProfessorDetail(Long id, String jsonString) {
    RmpSchoolSpiderService rmpSchoolSpiderService = Aop.get(RmpSchoolSpiderService.class);

    String sourceUrl = "https://www.ratemyprofessors.com/professor/" + id;
    JSONObject jsonObject = FastJson2Utils.parseObject(jsonString);
    jsonObject = jsonObject.getJSONObject("data").getJSONObject("teacher");
    if (jsonObject == null) {
      log.info("please check:{}", sourceUrl);
      return;
    }
    JSONObject school = jsonObject.getJSONObject("school");
    jsonObject.remove("school");
    if (school != null) {
      String idString = school.getString("id");
      String idLongString = Base64Utils.decodeToString(idString).split("-")[1];
      Long schoolId = Long.valueOf(idLongString);
      rmpSchoolSpiderService.fetchAndSaveIfNotExists(schoolId);
    }

    JSONArray teacherRatingTags = jsonObject.getJSONArray("teacherRatingTags");
    jsonObject.remove("teacherRatingTags");

    if (teacherRatingTags != null) {
      Aop.get(RMPProfessorRatingTagService.class).update(id, teacherRatingTags);
    }
    JSONObject ratings = jsonObject.getJSONObject("ratings");
    jsonObject.remove("ratings");
    if (ratings != null) {
      Aop.get(RMPRatingService.class).save(sourceUrl, id, ratings);
    }
    JSONObject mostUsefulRating = jsonObject.getJSONObject("mostUsefulRating");

    jsonObject.remove("mostUsefulRating");
    Long mostUsefulRatingId = null;
    if (mostUsefulRating != null) {
      String idString = Base64Utils.decodeToString(mostUsefulRating.getString("id"));
      String[] split = idString.split("-");
      mostUsefulRatingId = Long.valueOf(split[1]);
    }
    save(sourceUrl, id, mostUsefulRatingId, jsonObject);

  }

}
