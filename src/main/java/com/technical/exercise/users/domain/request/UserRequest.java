package com.technical.exercise.users.domain.request;

import java.io.Serializable;
import java.util.List;
import com.technical.exercise.users.domain.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class UserRequest implements Serializable {

  @Schema(description = "Name of the user to create.", type = "string", example = "Name Lastname")
  @NotBlank(message = "Name cannot be null or blank")
  String name;

  @Schema(description = "Email of the user to create.", type = "string",
      example = "aaaaaa@dominio.co")
  @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{}~^.-]+@[a-zA-Z0-9.-]+\\.+[a-zA-Z]+",
      message = "Invalid email format")
  @NotBlank(message = "Email cannot be null or blank")
  String email;

  @Schema(description = "Password of the user to create.", type = "string", example = "passrd12QW")
  @NotBlank(message = "Password cannot be null or blank")
  String password;

  List<Phone> phones;

}
