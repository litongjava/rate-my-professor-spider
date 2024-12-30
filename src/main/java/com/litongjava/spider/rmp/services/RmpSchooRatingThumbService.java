package com.litongjava.spider.rmp.services;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpSchoolRatingThumb;
import com.litongjava.tio.utils.encoder.Base64Utils;

public class RmpSchooRatingThumbService {

  public void save(Long schoolId, List<JSONArray> userThumbsList) {

    List<RumiRmpSchoolRatingThumb> list = new ArrayList<>();

    for (JSONArray jsonArray : userThumbsList) {
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        String idString = jsonObject.getString("id");
        jsonObject.remove("id");
        String decodeToString = Base64Utils.decodeToString(idString);
        Long id = Long.valueOf(decodeToString.split("-")[1]);

        RumiRmpSchoolRatingThumb m = jsonObject.toJavaObject(RumiRmpSchoolRatingThumb.class);
        m.setSchoolId(schoolId);
        m.setId(id);
        list.add(m);

      }

    }

    Db.tx(() -> {
      String sql = "delete from %s where school_id=?";
      sql = String.format(sql, TableNames.rumi_rmp_school_rating_thumb);
      Db.delete(sql, schoolId);
      Db.batchSave(list, 100);
      return true;
    });

  }

}
