package com.litong.utils.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author litong
 * @date 2019年6月1日_下午1:37:43
 * @version 1.0
 * @desc
 */
public class IOUtils {
  public static StringBuffer toStringBuffer(InputStream in) {
    StringBuffer sb = new StringBuffer();
    byte[] b = new byte[1024 * 8];
    int length = -1;
    try {
      while ((length = in.read(b)) != -1) {
        sb.append(new String(b, 0, length));
      }
      return sb;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      closeQuietly(in);
    }
    return null;
  }

  public static void closeQuietly(Closeable io) {
    if (io != null) {
      try {
        io.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
