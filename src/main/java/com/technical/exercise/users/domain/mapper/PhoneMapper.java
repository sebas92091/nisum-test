package com.technical.exercise.users.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.technical.exercise.users.adapter.repository.PhoneEntity;
import com.technical.exercise.users.domain.Phone;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneMapper {
  PhoneMapper INSTANCE = Mappers.getMapper(PhoneMapper.class);

  PhoneEntity dtoToEntity(Phone phone);

  Phone entityToDto(PhoneEntity phoneEntity);

}
