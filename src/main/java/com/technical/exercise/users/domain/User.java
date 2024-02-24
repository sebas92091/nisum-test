package com.technical.exercise.users.domain;

import java.io.Serializable;
import java.util.List;
import com.technical.exercise.users.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class User implements Serializable {

  @Schema(description = "User UUID", type = "string", example = Constants.UUID_DUMMY)
  String uuid;

  @Schema(description = "User name", type = "string", example = "Name Lastname")
  String name;

  @Schema(description = "User email", type = "string", example = "correo@dominio.co")
  String email;

  @Schema(description = "User password", type = "string", example = "passwo12QW")
  String password;

  @Schema(description = "User creation date", type = "string", example = "23/02/24, 11:27 p. m.")
  String created;

  @Schema(description = "User last modification date", type = "string",
      example = "23/02/24, 11:35 p. m.")
  String modified;

  @Schema(description = "User last login date", type = "string", example = "23/02/24, 11:27 p. m.")
  String lastLogin;

  @Schema(description = "token access", type = "string", example = Constants.TOKEN_DUMMY)
  String token;

  @Schema(description = "User is active", type = "string", example = "true")
  Boolean isactive;

  List<Phone> phones;
}
