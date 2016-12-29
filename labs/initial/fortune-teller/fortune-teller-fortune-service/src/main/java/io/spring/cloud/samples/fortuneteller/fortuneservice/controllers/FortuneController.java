package io.spring.cloud.samples.fortuneteller.fortuneservice.controllers;

import io.spring.cloud.samples.fortuneteller.fortuneservice.domain.Fortune;
import io.spring.cloud.samples.fortuneteller.fortuneservice.repositories.FortuneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

/**
 * @author sgupta
 * @since 1/10/16.
 */
@RestController
public class FortuneController {

  private static final Logger LOGGER = LoggerFactory.getLogger(FortuneController.class);

  @Autowired
  FortuneRepository repository;

  @RequestMapping("/fortunes")
  public Iterable<Fortune> fortunes() {
    return repository.findAll();
  }

  @RequestMapping("/random")
  public Fortune randomFortune() {
    LOGGER.info("debug is enabled: " + LOGGER.isDebugEnabled());
    if(LOGGER.isDebugEnabled()) {
      Iterator<Fortune> fortunes = fortunes().iterator();
      while(fortunes.hasNext()){
        LOGGER.debug(fortunes.next().toString());
      }
    }

    List<Fortune> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
    return randomFortunes.get(0);
  }
}