package com.technical.exercise.users.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.mapper.UserMapper;
import com.technical.exercise.users.domain.request.UserRequest;
import com.technical.exercise.users.exceptions.ResourceNotFoundException;
import com.technical.exercise.users.port.out.UserEntityPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEntityRepositoryAdapter implements UserEntityPort {
  private final UserEntityRepository userEntityRepository;

  @Override
  public User save(UserEntity userEntity) {
    return UserMapper.INSTANCE.entityToDto(userEntityRepository.save(userEntity));
  }

  @Override
  public User getUserByUuid(String uuid) {
    UserEntity user = getUserEntityByUuid(uuid);
    return UserMapper.INSTANCE.entityToDto(user);
  }

  @Override
  public List<User> getAll() {
    List<UserEntity> user = userEntityRepository.findByIsactiveTrue();
    return UserMapper.INSTANCE.map(user);
  }

  @Override
  public User update(UserRequest user, String uuid) {
    UserEntity userExisting = getUserEntityByUuid(uuid);
    UserMapper.INSTANCE.update(userExisting, user);
    return save(userExisting);
  }

  @Override
  public User delete(String uuid) {
    UserEntity user = getUserEntityByUuid(uuid);
    user.setIsactive(false);
    return save(user);
  }

  @Override
  public boolean existsByEmail(String email) {
    Optional<UserEntity> existingUser = userEntityRepository.findByEmailAndIsactiveTrue(email);
    return existingUser.isPresent();
  }

  public UserEntity getUserEntityByUuid(String uuid) {
    return userEntityRepository.findByUuidAndIsactiveTrue(uuid)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with uuid: " + uuid));
  }
}
