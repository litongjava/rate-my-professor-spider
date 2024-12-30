package com.litongjava.spider.rmp;

import com.litongjava.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class MainApp {

  public static void main(String[] args) {
    TioApplication.run(MainApp.class, args);
  }
}
