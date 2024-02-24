package com.technical.exercise.users.domain;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class Phone {

  @Schema(description = "Phone number of the user to create.", type = "string",
      example = "3013214569")
  @NotBlank(message = "Name is void")
  String number;

  @Schema(description = "City code for phone of the user to create.", type = "string",
      example = "01")
  @NotBlank(message = "Name is void")
  String citycode;

  @Schema(description = "Country code for phone of the user to create.", type = "string",
      example = "57")
  @NotBlank(message = "Name is void")
  String countrycode;
}
