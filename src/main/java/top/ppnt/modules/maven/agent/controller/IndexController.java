package top.ppnt.modules.maven.agent.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import top.ppnt.modules.maven.agent.service.DownloadService;

/**
 * @author bill robot
 * @date 2020年1月18日_上午11:11:23 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("/**")
@Slf4j
public class IndexController {

  @Autowired
  private ServerProperties sp;
  // 获取启动地址和端口
  @Autowired
  private DownloadService ds;

  @RequestMapping()
  public void index(HttpServletRequest request, HttpServletResponse response) {
    String requestURI = request.getRequestURI();
    log.info(requestURI.toString());
    String extension = FilenameUtils.getExtension(requestURI);
    if ("pom".equals(extension) || "jar".equals(extension)) {
      String contextPath = sp.getContextPath();
      //取出/maven后的内容
      requestURI = requestURI.substring(contextPath.length());
      //requestURI = requestURI.replace(contextPath, "");
      log.info(requestURI.toString());
      try (OutputStream outputStream = response.getOutputStream()) {
        ds.download(requestURI, outputStream);
        outputStream.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }else {
      try {
        response.getWriter().println("please input correct url");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
}
