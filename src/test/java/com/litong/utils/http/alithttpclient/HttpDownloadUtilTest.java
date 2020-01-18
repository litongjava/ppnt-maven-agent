package com.litong.utils.http.alithttpclient;

import org.junit.Test;

/**
 * Created by litng on 2020/1/18.
 */
public class HttpDownloadUtilTest {

  @Test
  public void downloadFile() throws Exception {
    String localFilePath = "D:/dev_workspace/java/hg_project/bill-maven-agent/target/classes/bill-maven-agent/data/jar/com/alibaba/fastjson/1.2.60/fastjson-1.2.60.jar";
    String remoteURL = "https://maven.aliyun.com/repository/public/com/alibaba/fastjson/1.2.60/fastjson-1.2.60.jar";
    // String remoteURL =
    // "http://archiva-maven-storage-prod.oss-cn-beijing.aliyuncs.com/repository/central/com/alibaba/fastjson/1.2.60/fastjson-1.2.60.jar?Expires=1579330717&OSSAccessKeyId=LTAIfU51SusnnfCC&Signature=NIjFWkF24FxYIsKZ4peilKhXdBY%3D";
    HttpDownloadUtil.downloadFile(localFilePath, remoteURL);
  }
}