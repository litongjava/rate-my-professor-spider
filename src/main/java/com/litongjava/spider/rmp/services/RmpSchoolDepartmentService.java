package com.litongjava.spider.rmp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.util.concurrent.Striped;
import com.litongjava.db.activerecord.Db;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpSchoolDepartments;
import com.litongjava.tio.utils.snowflake.SnowflakeIdUtils;

public class RmpSchoolDepartmentService {

  private static final Striped<Lock> LOCK_STRIPED = Striped.lock(64);

  public void save(Long schoolId, com.alibaba.fastjson2.JSONArray jsonArray) {

    List<RumiRmpSchoolDepartments> list = new ArrayList<>(jsonArray.size());
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      Integer id = jsonObject.getInteger("id");
      String name = jsonObject.getString("name");

      RumiRmpSchoolDepartments d = new RumiRmpSchoolDepartments();
      d.setDId(id).setName(name).setSchoolId(schoolId).setId(SnowflakeIdUtils.id());
      list.add(d);
    }

    Lock lock = LOCK_STRIPED.get(schoolId);
    lock.lock();

    try {
      Db.tx(() -> {
        String sql = "delete from %s where school_id=?";
        sql = String.format(sql, TableNames.rumi_rmp_school_departments);
        Db.delete(sql, schoolId);
        Db.batchSave(list, 100);
        return true;
      });
    } finally {
      lock.unlock();
    }
  }
}
