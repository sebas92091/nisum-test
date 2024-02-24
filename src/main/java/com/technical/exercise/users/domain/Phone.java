package com.technical.exercise.users.domain;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Phone {

  @NotBlank(message = "Name is void")
  String number;

  @NotBlank(message = "Name is void")
  String citycode;

  @NotBlank(message = "Name is void")
  String countrycode;
}
