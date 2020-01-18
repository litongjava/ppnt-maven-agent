package com.bill.robot.maven.agent.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bill.robot.maven.agent.service.DownloadService;

/**
 * @author bill robot
 * @date 2020年1月18日_上午11:11:23 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("/**")
public class IndexController {

  @Autowired
  private ServerProperties sp;
  // 获取启动地址和端口
  @Autowired
  private DownloadService ds;

  @RequestMapping()
  public void index(HttpServletRequest request, HttpServletResponse response) {
    String requestURI = request.getRequestURI();
    String contextPath = sp.getContextPath();
    requestURI = requestURI.replace(contextPath, "");
    try (OutputStream outputStream = response.getOutputStream()) {
      ds.download(requestURI, outputStream);
      outputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
