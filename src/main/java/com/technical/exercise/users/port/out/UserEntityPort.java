package com.technical.exercise.users.port.out;

import java.util.List;
import com.technical.exercise.users.adapter.repository.UserEntity;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.request.UserRequest;

public interface UserEntityPort {
  User save(UserEntity user);

  User getUserByUuid(String uuid);

  List<User> getAll();

  User update(UserRequest user, String uuid);

  User delete(String uuid);

  boolean existsByEmail(String email);
}
