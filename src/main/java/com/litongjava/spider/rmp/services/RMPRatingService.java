package com.litongjava.spider.rmp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.db.activerecord.Row;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpRating;
import com.litongjava.tio.utils.date.DateParseUtils;
import com.litongjava.tio.utils.encoder.Base64Utils;
import com.litongjava.tio.utils.hutool.StrUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RMPRatingService {

  public void saveIfNotExists(Long teacherId, JSONArray jsonArray, String sourceUrl, Long rmpSchoolId) {
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("node");
      String idString = jsonObject.getString("id");
      Long id = 0L;

      if (StrUtil.isNotEmpty(idString)) {

        String[] array = Base64Utils.decodeToString(idString).split("-");
        if (array.length > 1) {
          id = Long.valueOf(array[1]);

        } else {
          log.error("please check id");
          return;
        }
      }

      boolean exists = Db.exists(TableNames.rumi_rmp_rating, "id", id);
      if (exists) {
        return;
      }

      RumiRmpRating rmpRating = parse(sourceUrl, rmpSchoolId, teacherId, id, jsonObject);

      Row record = Row.fromBean(rmpRating);
      boolean save = Db.save(record.getTableName(), record);
      log.info("save result:{}", save);
    }
  }

  public void save(String sourceUrl, Long teacherId, JSONObject ratings) {
    JSONArray edges = ratings.getJSONArray("edges");
    List<JSONArray> userThumbsList = new ArrayList<>(edges.size());

    List<RumiRmpRating> saveList = new ArrayList<>();
    for (int i = 0; i < edges.size(); i++) {
      JSONObject connection = edges.getJSONObject(i);
      JSONObject jsonObject = connection.getJSONObject("node");

      JSONArray userThumbs = jsonObject.getJSONArray("userThumbs");
      jsonObject.remove("userThumbs");
      if (userThumbs != null && userThumbs.size() > 0) {
        userThumbsList.add(userThumbs);
      }

      String idString = jsonObject.getString("id");
      Long id = 0L;

      if (StrUtil.isNotEmpty(idString)) {

        String[] array = Base64Utils.decodeToString(idString).split("-");
        if (array.length > 1) {
          id = Long.valueOf(array[1]);

        } else {
          log.error("please check id");
          return;
        }
      }
      RumiRmpRating rmpRating = parse(sourceUrl, null, teacherId, id, jsonObject);
      saveList.add(rmpRating);
    }

    Db.tx(() -> {
      String sql = String.format("delete from %s where teacher_id=?", TableNames.rumi_rmp_rating);
      Db.delete(sql, teacherId);
      Db.batchSave(saveList, 100);
      return true;
    });

    Aop.get(RmpProfessorRatingThumbService.class).save(teacherId, userThumbsList);

  }

  private RumiRmpRating parse(String sourceUrl, Long rmpSchoolId, Long teacherId, Long id, JSONObject jsonObject) {
    RumiRmpRating rmpRating = new RumiRmpRating();
    rmpRating.setId(id);
    String adminReviewedAt = jsonObject.getString("adminReviewedAt");
    if (adminReviewedAt != null) {
      rmpRating.setAdminReviewedAt(Date.from(DateParseUtils.parseUTCDateString(adminReviewedAt).toInstant()));
    }

    rmpRating.setAttendanceMandatory(jsonObject.getString("attendanceMandatory"));
    rmpRating.setClarityRatingRounded(jsonObject.getInteger("clarityRatingRounded"));
    rmpRating.setClassName(jsonObject.getString("class"));
    rmpRating.setComment(jsonObject.getString("comment"));
    rmpRating.setCourseType(jsonObject.getInteger("courseType"));
    rmpRating.setCreatedByUser(jsonObject.getBoolean("createdByUser"));
    rmpRating.setDate(Date.from(DateParseUtils.parseUTCDateString(jsonObject.getString("date")).toInstant()));

    rmpRating.setDifficultyRatingRounded(jsonObject.getInteger("difficultyRatingRounded"));
    rmpRating.setFlagStatus(jsonObject.getString("flagStatus"));
    rmpRating.setGrade(jsonObject.getString("grade"));
    rmpRating.setHelpfulRatingRounded(jsonObject.getInteger("helpfulRatingRounded"));
    rmpRating.setIsForCredit(jsonObject.getBoolean("isForCredit"));
    rmpRating.setIsForOnlineClass(jsonObject.getBoolean("isForOnlineClass"));
    rmpRating.setQualityRating(jsonObject.getInteger("qualityRating"));
    rmpRating.setRatingTags(jsonObject.getString("qualityRating"));
    rmpRating.setTextbookIsUsed(jsonObject.getBoolean("textbookIsUsed"));
    rmpRating.setThumbsDownTotal(jsonObject.getInteger("thumbsDownTotal"));
    rmpRating.setThumbsUpTotal(jsonObject.getInteger("thumbsUpTotal"));
    rmpRating.setMaskCount(jsonObject.getInteger("maskCount"));
    rmpRating.setWouldLikeTakeAgain(jsonObject.getInteger("wouldTakeAgain"));

    rmpRating.setTeacherId(teacherId);
    rmpRating.setSourceUrl(sourceUrl);
    rmpRating.setSchoolId(rmpSchoolId);

    return rmpRating;
  }
}
