package com.litongjava.spider.rmp.controller;

import com.litongjava.annotation.RequestPath;
import com.litongjava.jfinal.aop.Aop;
import com.litongjava.model.body.RespBodyVo;
import com.litongjava.spider.rmp.services.RmpTeacherSpiderService;
import com.litongjava.tio.utils.thread.TioThreadUtils;

@RequestPath("/api/v1/spider")
public class SpiderController {

  public RespBodyVo professors() {
    TioThreadUtils.submit(() -> {
      Aop.get(RmpTeacherSpiderService.class).spiderAllTeacher();
    });
    return RespBodyVo.ok();
  }

}
