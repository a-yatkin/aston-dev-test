package com.example.astondevtest.mapper;

import com.example.astondevtest.dto.MoneyTransferDto;
import com.example.astondevtest.model.MoneyTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MoneyTransferMapper {

    @Mapping(target = "id", ignore = true)
    MoneyTransferEntity toEntity(MoneyTransferDto moneyTransferDto);

    MoneyTransferDto toDto(MoneyTransferEntity moneyTransferEntity);

    List<MoneyTransferDto> toDtoList(List<MoneyTransferEntity> moneyTransferEntityList);
}
