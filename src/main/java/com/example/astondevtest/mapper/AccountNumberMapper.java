package com.example.astondevtest.mapper;

import com.example.astondevtest.dto.AccountNumberDto;
import com.example.astondevtest.model.AccountNumberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountNumberMapper {

    @Mapping(target = "id", ignore = true)
    AccountNumberEntity toEntity(AccountNumberDto accountNumberDto);

    AccountNumberDto toDto(AccountNumberEntity accountNumberEntity);

    List<AccountNumberDto> toDtoList(List<AccountNumberEntity> accountNumberEntityList);

    @Mapping(target = "id", ignore = true)
    AccountNumberEntity mergeToEntity(@MappingTarget AccountNumberEntity accountNumberEntity,
                                      AccountNumberDto accountNumberDto);
}
