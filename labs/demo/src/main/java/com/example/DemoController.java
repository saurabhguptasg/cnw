package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sgupta
 * @since 7/15/16.
 */
@RestController
public class DemoController {

  @RequestMapping(path = "/", method = RequestMethod.GET, produces = "application/json")
  public Map<String,String> getTime() {
    Map<String,String> map = new HashMap<>();
    map.put("time", new Date().toString());
    return map;
  }
}
