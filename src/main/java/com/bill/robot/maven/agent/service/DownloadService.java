package com.bill.robot.maven.agent.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.bill.robot.maven.agent.component.ResourcesLocationComponent;
import com.litong.utils.http.alithttpclient.HttpDownloadUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bill robot
 * @date 2020年1月3日_下午3:49:27 
 * @version 1.0 
 * @desc
 */
@Service
@Slf4j
public class DownloadService {

  @Value("${maven.center.url}")
  private String mavenCenterUrl;
  @Autowired
  private ResourcesLocationComponent rlc;

  private String jarDirName = "data" + File.separator + "jar";
  private String jarDownloadPath = null;

  public void download(String requestURI, OutputStream outputStream) {
    String prefixUri = FilenameUtils.getPath(requestURI);
    String jarFolderPath = getDownloadPath() + File.separator+prefixUri;
    File jarFolder = new File(jarFolderPath);
    if (!jarFolder.exists()) {
      log.info("create dir:" + jarFolder.getAbsolutePath());
      jarFolder.mkdirs();
    }

    String downloadAddr = mavenCenterUrl + requestURI;
    log.info("downloadAddr : " + downloadAddr);
    String jarFilePath = getDownloadPath() + requestURI;
    File jarFile = new File(jarFilePath);
    if (!jarFile.exists()) {
      String downloadFile = HttpDownloadUtil.downloadFile(jarFile.getAbsolutePath(), downloadAddr);
      if (downloadFile == null) {
        // 下载出现异常,返回
        log.info("下载出现异常");
        return;
      }
    }
    BufferedOutputStream bufOut = new BufferedOutputStream(outputStream);
    try (BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(jarFile));) {
      FileCopyUtils.copy(bufIn, bufOut);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  public String getDownloadPath() {
    if (jarDownloadPath == null) {
      jarDownloadPath = rlc.getLocations().get(0) + File.separator + jarDirName;
      log.info("jarDownloadPath : " + jarDownloadPath);
      File file = new File(jarDownloadPath);
      if (!file.exists()) {
        log.info("create dir:" + file.getAbsolutePath());
        file.mkdirs();
      }
      jarDownloadPath = file.getAbsolutePath();
    }
    return jarDownloadPath;
  }
}