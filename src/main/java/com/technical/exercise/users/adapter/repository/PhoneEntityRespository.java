package com.technical.exercise.users.adapter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneEntityRespository extends JpaRepository<PhoneEntity, Long> {

}
