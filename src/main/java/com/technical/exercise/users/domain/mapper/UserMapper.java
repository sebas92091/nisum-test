package com.technical.exercise.users.domain.mapper;

import java.util.List;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.technical.exercise.users.adapter.repository.PhoneEntity;
import com.technical.exercise.users.adapter.repository.UserEntity;
import com.technical.exercise.users.domain.Phone;
import com.technical.exercise.users.domain.User;
import com.technical.exercise.users.domain.request.UserRequest;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "uuid", expression = "java(generateUUID())")
  @Mapping(target = "isactive", constant = "true")
  @Mapping(target = "token", source = "token")
  UserEntity dtoToEntity(UserRequest user, String token);

  User entityToDto(UserEntity userEntity);

  List<User> map(List<UserEntity> userList);

  void update(@MappingTarget UserEntity user, UserRequest userRequest);

  UserEntity userToEntity(User user);

  default PhoneEntity phoneToPhoneEntity(Phone phone) {
    return Mappers.getMapper(PhoneMapper.class).dtoToEntity(phone);
  }

  default Phone phoneEntityToPhone(PhoneEntity entity) {
    return Mappers.getMapper(PhoneMapper.class).entityToDto(entity);
  }

  default String generateUUID() {
    return UUID.randomUUID().toString();
  }


}
