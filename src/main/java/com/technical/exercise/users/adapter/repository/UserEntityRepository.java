package com.technical.exercise.users.adapter.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmailAndIsactiveTrue(String email);

  Optional<UserEntity> findByUuidAndIsactiveTrue(String uuid);

  List<UserEntity> findByIsactiveTrue();



}
