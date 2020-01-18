package com.bill.robot.maven.agent;

import org.springframework.context.annotation.Import;

/**
 * @author bill robot
 * @date 2020年1月18日_上午11:35:00 
 * @version 1.0 
 * @desc
 */
@Import({
  org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration.class,
  org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class,
  org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.class,
  org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration.class,
  org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration.class,
  org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.HttpMessageConvertersAutoConfiguration.class,
  org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.HttpEncodingAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration.class,
  org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration.class,
  org.springframework.boot.devtools.autoconfigure.LocalDevToolsAutoConfiguration.class
})
public class ManualImport {

}
