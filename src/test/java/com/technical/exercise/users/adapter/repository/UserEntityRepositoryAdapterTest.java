package com.technical.exercise.users.adapter.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.request.UserRequest;
import com.technical.exercise.users.exceptions.ResourceNotFoundException;

public class UserEntityRepositoryAdapterTest {

  @Mock
  private UserEntityRepository userEntityRepository;

  @InjectMocks
  private UserEntityRepositoryAdapter userEntityRepositoryAdapter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

  }

  @Test
  void save() {
    UserEntity userEntity = new UserEntity();
    when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    User savedUser = userEntityRepositoryAdapter.save(userEntity);

    assertNotNull(savedUser);
  }

  @Test
  void getUserByUuid() {
    UserEntity userEntity = new UserEntity();
    when(userEntityRepository.findByUuidAndIsactiveTrue(anyString()))
        .thenReturn(Optional.of(userEntity));

    User user = userEntityRepositoryAdapter.getUserByUuid("dummyUuid");

    assertNotNull(user);
  }

  @Test
  void getAll() {
    when(userEntityRepository.findByIsactiveTrue()).thenReturn(Collections.emptyList());

    List<User> users = userEntityRepositoryAdapter.getAll();

    assertNotNull(users);
  }

  @Test
  void update() {
    UserEntity userEntity = new UserEntity();
    when(userEntityRepository.findByUuidAndIsactiveTrue(anyString()))
        .thenReturn(Optional.of(userEntity));
    when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    User updatedUser = userEntityRepositoryAdapter.update(new UserRequest(), "dummyUuid");

    assertNotNull(updatedUser);
  }

  @Test
  void delete() {
    UserEntity userEntity = new UserEntity();
    when(userEntityRepository.findByUuidAndIsactiveTrue(anyString()))
        .thenReturn(Optional.of(userEntity));
    when(userEntityRepository.save(any(UserEntity.class))).thenReturn(userEntity);

    User deletedUser = userEntityRepositoryAdapter.delete("dummyUuid");

    assertNotNull(deletedUser);
  }

  @Test
  void existsByEmail() {
    when(userEntityRepository.findByEmailAndIsactiveTrue(anyString()))
        .thenReturn(Optional.of(new UserEntity()));

    boolean exists = userEntityRepositoryAdapter.existsByEmail("dummyEmail");

    assertTrue(exists);
  }

  @Test
  void getUserEntityByUuid_UserExists_ReturnsUserEntity() {
    UserEntity expectedUserEntity = new UserEntity();
    when(userEntityRepository.findByUuidAndIsactiveTrue("dummyUuid"))
        .thenReturn(Optional.of(expectedUserEntity));

    UserEntity userEntity = userEntityRepositoryAdapter.getUserEntityByUuid("dummyUuid");

    assertNotNull(userEntity);
    assertEquals(expectedUserEntity, userEntity);
  }

  @Test
  void getUserEntityByUuid_UserDoesNotExist_ThrowsException() {
    when(userEntityRepository.findByUuidAndIsactiveTrue("nonExistingUuid"))
        .thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> {
      userEntityRepositoryAdapter.getUserEntityByUuid("nonExistingUuid");
    });
  }
}
