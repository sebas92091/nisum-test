package com.technical.exercise.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import com.technical.exercise.users.adapter.repository.UserEntity;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.request.UserRequest;
import com.technical.exercise.users.exceptions.EmailAlreadyRegisteredException;
import com.technical.exercise.users.exceptions.PasswordValidationException;
import com.technical.exercise.users.port.out.UserEntityPort;


public class UserServiceTest {

  static final String dummyToken = "Bearer 3a2bb73c-be20-4a5d-af48-614e7174992A";

  @InjectMocks
  private UserService userService;

  @Mock
  private UserEntityPort userEntityPort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    ReflectionTestUtils.setField(userService, "PASSWORD_REGEX", "^(?=.*[A-Z])(?=.*\\d).{10,}$");
  }

  @Test
  void createUser_ValidUser_ReturnsUser() {

    when(userEntityPort.existsByEmail(anyString())).thenReturn(false);
    when(userEntityPort.save(any(UserEntity.class))).thenReturn(new User());
    UserRequest userRequest = UserRequest.builder().name("nombre").password("Password12").build();

    User createdUser = userService.createUser(userRequest, dummyToken);


    assertNotNull(createdUser);
  }

  @Test
  void createUser_InvalidPassword_ThrowsException() {

    when(userEntityPort.existsByEmail(anyString())).thenReturn(false);

    UserRequest userRequest = UserRequest.builder().name("nombre").password("Password").build();

    assertThrows(PasswordValidationException.class,
        () -> userService.createUser(userRequest, "dummyToken"));
  }

  @Test
  void createUser_InvalidEmail_ThrowsException() {

    when(userEntityPort.existsByEmail(anyString())).thenReturn(true);

    UserRequest userRequest = UserRequest.builder().name("nombre").password("Password12")
        .email("correo@correo.com").build();

    assertThrows(EmailAlreadyRegisteredException.class,
        () -> userService.createUser(userRequest, dummyToken));
  }

  @Test
  void getUsers_ReturnsListOfUsers() {

    when(userEntityPort.getAll()).thenReturn(Collections.singletonList(new User()));

    List<User> users = userService.getUsers();

    assertNotNull(users);
    assertEquals(1, users.size());
  }

  @Test
  void getUserByUuid_ValidUuid_ReturnsUser() {
    when(userEntityPort.getUserByUuid(anyString())).thenReturn(new User());

    User user = userService.getUserByUuid("dummyUuid");

    assertNotNull(user);
  }

  @Test
  void UpdateUser_ReturnsUser() {
    when(userEntityPort.update(any(UserRequest.class), anyString())).thenReturn(new User());

    UserRequest userRequest = UserRequest.builder().name("nombre").password("Password12")
        .email("correo@correo.com").build();

    User user = userService.updateUser(userRequest, "dummyUuid");

    assertNotNull(user);
  }

  @Test
  void deleteUser_ValidUuid_ReturnsUser() {
    String DELETE_MESSAGE = "User deleted successfully";
    when(userEntityPort.delete(anyString())).thenReturn(new User());

    String delete = userService.deleteUser("dummyUuid");

    assertEquals(DELETE_MESSAGE, delete);
  }

}
