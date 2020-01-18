package com.bill.robot.maven.agent.component;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Service
public class ResourcesLocationComponent {
  private List<String> staticLocations;
  @Autowired
  private ResourceHttpRequestHandler rhrh;

  public List<String> getLocations() {
    if (staticLocations == null) {
      List<Resource> locations = rhrh.getLocations();
      staticLocations = new ArrayList<String>();
      for (int i = 0; i < locations.size(); i++) {
        String description = locations.get(i).getDescription();
        String path = description.substring(description.indexOf("[") + 1, description.lastIndexOf("]"));
        if (path.equals("") || path.equals("/")) {
          continue;
        }
        URI uri = null;
        try {
          uri = locations.get(i).getURI();
          staticLocations.add((uri.getPath()));
        } catch (IOException e) {
          // 发生异常,不理会
        }
      }
    }
    return staticLocations;
  }
}
