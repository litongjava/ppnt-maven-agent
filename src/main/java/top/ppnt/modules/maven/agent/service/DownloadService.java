package top.ppnt.modules.maven.agent.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import lombok.extern.slf4j.Slf4j;
import top.ppnt.modules.utils.http.alithttpclient.HttpDownloadUtil;

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
  @Value("${maven.local.path}")
  private String mavenLocalPath;
//  @Autowired
//  private ResourcesLocationComponent rlc;
//
//  private String jarDirName = "data" + File.separator + "jar";
//  private String jarDownloadPath = null;

  public void download(String requestURI, OutputStream outputStream) {
    String downloadPath = getDownloadPath();
    //log.info("本地保存路径:{}",downloadPath);
    String prefixUri = FilenameUtils.getPath(requestURI);
    
    String jarFolderPath = downloadPath + File.separator+prefixUri;
    File jarFolder = new File(jarFolderPath);
    if (!jarFolder.exists()) {
      log.info("create dir:" + jarFolder.getAbsolutePath());
      jarFolder.mkdirs();
    }

    String jarFilePath = downloadPath + requestURI;
    File jarFile = new File(jarFilePath);
    if (!jarFile.exists()) {
      String downloadAddr = mavenCenterUrl + requestURI;
      log.info("downloadAddr : " + downloadAddr);
      String downloadFile = HttpDownloadUtil.downloadFile(jarFile.getAbsolutePath(), downloadAddr);
      if (downloadFile == null) {
        // 下载出现异常,返回
        log.info("下载出现异常");
        return;
      }
    }else {
      log.info("使用本地jar:{}",jarFilePath);
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
//    if (jarDownloadPath == null) {
//      jarDownloadPath = rlc.getLocations().get(0) + File.separator + jarDirName;
//      log.info("jarDownloadPath : " + jarDownloadPath);
//      File file = new File(jarDownloadPath);
//      if (!file.exists()) {
//        log.info("create dir:" + file.getAbsolutePath());
//        file.mkdirs();
//      }
//      jarDownloadPath = file.getAbsolutePath();
//    }
//    return jarDownloadPath;
    return mavenLocalPath;
  }
}