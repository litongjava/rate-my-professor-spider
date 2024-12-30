package com.litongjava.spider.rmp.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson2.JSONObject;
import com.litongjava.db.activerecord.Db;
import com.litongjava.spider.rmp.constants.TableNames;
import com.litongjava.spider.rmp.model.RumiRmpSchoolSummary;

public class RmpSchoolSummaryService {

  public static final Map<Long, ReentrantLock> lockMap = new ConcurrentHashMap<>();

  public void save(Long id, JSONObject summary) {

    RumiRmpSchoolSummary model = summary.toJavaObject(RumiRmpSchoolSummary.class);
    model.setId(id);

    Db.tx(() -> {
      Db.deleteById(TableNames.rumi_rmp_school_summary, id);
      model.save();
      return true;
    });

  }
}
