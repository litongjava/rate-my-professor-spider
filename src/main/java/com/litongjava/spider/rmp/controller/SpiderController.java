package com.litongjava.spider.rmp.controller;

import com.litongjava.annotation.RequestPath;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.spider.rmp.services.RmpTeacherSpiderService;
import com.litongjava.tio.utils.thread.TioThreadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestPath("/api/v1/spider")
public class SpiderController {

  public RespBodyVo professors() {
    TioThreadUtils.submit(() -> {
      Aop.get(RmpTeacherSpiderService.class).spiderAllTeacher();
    });
    return RespBodyVo.ok();
  }

  public RespBodyVo professorsBySchoolId(Long schoolId) {
    TioThreadUtils.submit(() -> {
      try {
        Aop.get(RmpTeacherSpiderService.class).professorsBySchoolId(schoolId);
      } catch (Exception e) {
        log.error(e.getMessage(), e);
      }

    });
    return RespBodyVo.ok();
  }

}
