package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("booking")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto implements Persistable<String> {

  @Id
  @Column("booking_id")
  private String id;

  private String source;
  private String destination;
  private String date;
  private String passengerName;
  private String passengerAge;
  private String passengerGender;

  @JsonIgnore @Transient private boolean newEntity;

  @JsonIgnore
  @Override
  public boolean isNew() {
    return this.newEntity || id == null;
  }

  public BookingDto setAsNew() {
    this.newEntity = true;
    return this;
  }
}
