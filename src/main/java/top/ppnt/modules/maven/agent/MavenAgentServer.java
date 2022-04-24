package top.ppnt.modules.maven.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Import(ManualImport.class)
@ComponentScan(value = MavenAgentServer.basePackage, excludeFilters = {
    @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
    @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })

public class MavenAgentServer {
  public static final String basePackage = "top.ppnt.modules.maven.agent";

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    SpringApplication.run(MavenAgentServer.class, args);
    long end = System.currentTimeMillis();
    log.info("共使用了:" + (end - start) + "ms");
  }
}
