package io.spring.cloud.samples.fortuneteller.ui.service.fortunes;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sgupta
 * @since 1/10/16.
 */
@Service
@ConfigurationProperties(prefix="fortune")
public class FortuneService {

  private final Random RANDOM = new Random();

  @Autowired
  RestTemplate restTemplate;

  private List<String> messages = new ArrayList<String>();

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  public List<String> getMessages() {
    return this.messages;
  }

  @HystrixCommand(fallbackMethod = "randomRemoteFortune")
  public Fortune randomFortune() {
    return restTemplate.getForObject("http://fortunes/random", Fortune.class);
  }


  @HystrixCommand(fallbackMethod = "fallbackFortune")
  public Fortune randomRemoteFortune() {
    int id = RANDOM.nextInt(this.messages.size());
    return new Fortune((long)id, this.messages.get(id));
  }

  private Fortune fallbackFortune() {
    return new Fortune(42L, "Your future is unclear.");
  }
}