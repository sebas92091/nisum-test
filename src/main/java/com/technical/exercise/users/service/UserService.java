package com.technical.exercise.users.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.technical.exercise.users.adapter.repository.UserEntity;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.mapper.UserMapper;
import com.technical.exercise.users.domain.request.UserRequest;
import com.technical.exercise.users.exceptions.EmailAlreadyRegisteredException;
import com.technical.exercise.users.exceptions.PasswordValidationException;
import com.technical.exercise.users.port.out.UserEntityPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  static final String DELETE_MESSAGE = "User deleted successfully";

  @Value("${validation.password.regex}")
  String PASSWORD_REGEX;

  private final UserEntityPort userEntityPort;

  public User createUser(UserRequest user, String token) {
    if (!user.getPassword().matches(PASSWORD_REGEX)) {
      throw new PasswordValidationException();
    }
    if (userEntityPort.existsByEmail(user.getEmail())) {
      throw new EmailAlreadyRegisteredException();
    }
    UserEntity userEntity = UserMapper.INSTANCE.dtoToEntity(user, token.split(" ")[1]);
    return userEntityPort.save(userEntity);
  }

  public List<User> getUsers() {
    return userEntityPort.getAll();
  }

  public User getUserByUuid(String uuid) {
    return userEntityPort.getUserByUuid(uuid);
  }

  public User updateUser(UserRequest userRequest, String uuid) {
    return userEntityPort.update(userRequest, uuid);
  }

  public String deleteUser(String uuid) {
    userEntityPort.delete(uuid);
    return DELETE_MESSAGE;
  }

}
