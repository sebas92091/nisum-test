package com.technical.exercise.users.domain;

import java.io.Serializable;
import java.util.List;
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
public class User implements Serializable {

  String uuid;

  String name;

  String email;

  String password;

  String created;

  String modified;

  String lastLogin;

  String token;

  Boolean isactive;

  List<Phone> phones;
}
