package com.example.astondevtest.mapper;

import com.example.astondevtest.dto.SuperAccountDto;
import com.example.astondevtest.model.SuperAccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SuperAccountMapper {

    @Mapping(target = "id", ignore = true)
    SuperAccountEntity toEntity(SuperAccountDto superAccountDto);

    SuperAccountDto toDto(SuperAccountEntity superAccountEntity);

    List<SuperAccountDto> toDtoList(List<SuperAccountEntity> superAccountEntityList);

    @Mapping(target = "id", ignore = true)
    SuperAccountEntity mergeToEntity(@MappingTarget SuperAccountEntity superAccountEntity,
                                     SuperAccountDto superAccountDto);
}


