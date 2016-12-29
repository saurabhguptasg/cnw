package io.spring.cloud.samples.fortuneteller.fortuneservice.domain;

import javax.persistence.*;

/**
 * @author sgupta
 * @since 1/10/16.
 */
@Entity
@Table(name = "fortunes")
public class Fortune {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return "Fortune [id=" + id + ", text=" + text + "]\n";
    }
}