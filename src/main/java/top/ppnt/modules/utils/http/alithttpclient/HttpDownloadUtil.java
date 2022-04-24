package top.ppnt.modules.utils.http.alithttpclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;
import top.ppnt.modules.utils.io.IOUtils;

/**
 * 
 * @author litong
 * @desc 文件下载工具类
 */
@Slf4j
public class HttpDownloadUtil {
  public static final int BUFFER_SIZE = 4096;

  public static String downloadFile(String localFilePath, String remoteURL) {
    log.info("开始下载:" + System.currentTimeMillis());
    HttpURLConnection httpConnection = getHttpConnection(remoteURL);

    try {
      while (httpConnection.getResponseCode() > 300) {
        String location = httpConnection.getHeaderField("location");
        httpConnection = getHttpConnection(location);
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    save(localFilePath, httpConnection);
    log.info("结束下载:" + System.currentTimeMillis());
    return localFilePath;
  }

  private static void save(String localFilePath, HttpURLConnection httpConnection) {
    InputStream inputStream = null;
    BufferedInputStream bufferedInputStream = null;
    FileOutputStream fileOutputStream = null;
    BufferedOutputStream bufferedOutputStream = null;
    try {
      inputStream = httpConnection.getInputStream(); // 获取远程文件流
      bufferedInputStream = new BufferedInputStream(inputStream); // 使用缓冲流
      fileOutputStream = new FileOutputStream(localFilePath);
      bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
      byte[] buffer = new byte[BUFFER_SIZE];
      int bytesRead = -1;
      while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
        bufferedOutputStream.write(buffer, 0, bytesRead);
      }
      bufferedOutputStream.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      IOUtils.closeQuietly(bufferedOutputStream);
      IOUtils.closeQuietly(bufferedInputStream);
      httpConnection.disconnect();
    }
  }

  private static HttpURLConnection getHttpConnection(String remoteURL) {
    URL httpURL = null;
    try {
      httpURL = new URL(remoteURL);// 封装成URL
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    HttpURLConnection httpConnection = null;
    try {
      httpConnection = (HttpURLConnection) httpURL.openConnection(); // 打开http连接
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    // httpConnection.setRequestProperty("Content-Type", "application/json;
    // charset=utf-8"); // 设置请求头,不知道为什么这么设置
    httpConnection.setDoInput(true);
    httpConnection.setDoOutput(true);
    return httpConnection;
  }
}
