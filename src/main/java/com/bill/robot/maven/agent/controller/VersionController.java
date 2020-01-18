package com.bill.robot.maven.agent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bill robot
 * @date 2020年1月18日_上午11:28:06 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("version")
public class VersionController {
  @RequestMapping
  public String version() {
    return "version";
  }
}
