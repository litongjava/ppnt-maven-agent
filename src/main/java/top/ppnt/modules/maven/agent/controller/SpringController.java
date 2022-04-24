package top.ppnt.modules.maven.agent.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.ppnt.modules.maven.agent.service.DownloadService;

/**
 * @author bill robot
 * @date 2020年1月18日_上午11:30:44 
 * @version 1.0 
 * @desc
 */
@RestController
@RequestMapping("spring")
public class SpringController {
  @Autowired
  private ApplicationContext ac;
  @Autowired
  private DownloadService ds;

  @RequestMapping("get-auto-configuration")
  public List<String> getAutoConfiguration() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    List<String> retval = new ArrayList<String>();
    for (String string : beanDefinitionNames) {
      if (string.endsWith("AutoConfiguration")) {
        retval.add(string);
      }
    }
    return retval;
  }

  @RequestMapping("get-jar-download-path")
  public String getJarDownloadPath() {
    String downloadPath = ds.getDownloadPath();
    return downloadPath;
  }
}
