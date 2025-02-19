package com.litongjava.spider.rmp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpSchoolRating;
import com.litongjava.tio.utils.date.DateParseUtils;
import com.litongjava.tio.utils.encoder.Base64Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RmpSchooRatingService {

  public void save(Long schoolId, com.alibaba.fastjson2.JSONArray jsonArray) {

    List<RumiRmpSchoolRating> list = new ArrayList<>(jsonArray.size());
    List<JSONArray> userThumbsList = new ArrayList<>();

    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i).getJSONObject("node");
      JSONArray userThumbs = jsonObject.getJSONArray("userThumbs");
      jsonObject.remove("userThumbs");
      if (userThumbs != null && userThumbs.size() > 0) {
        userThumbsList.add(userThumbs);
      }

      String idString = jsonObject.getString("id");
      String dateString = jsonObject.getString("date");
      Date date = Date.from(DateParseUtils.parseUTCDateString(dateString).toInstant());
      jsonObject.remove("id");
      jsonObject.remove("date");
      String decodeToString = Base64Utils.decodeToString(idString);
      Long id = Long.valueOf(decodeToString.split("-")[1]);
      RumiRmpSchoolRating m = jsonObject.toJavaObject(RumiRmpSchoolRating.class);
      m.setSchoolId(schoolId);
      m.setId(id);
      m.setDate(date);
      list.add(m);

    }

    Db.tx(() -> {
      String sql = "delete from %s where school_id=?";
      sql = String.format(sql, TableNames.rumi_rmp_school_rating);

      try {
        Db.delete(sql, schoolId);
        Db.batchSave(list, 100);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }

      return true;
    });

    Aop.get(RmpSchooRatingThumbService.class).save(schoolId, userThumbsList);

  }

}
