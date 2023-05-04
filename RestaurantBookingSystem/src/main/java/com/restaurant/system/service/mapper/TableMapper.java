package com.restaurant.system.service.mapper;

import com.restaurant.system.model.RestaurantTable;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableMapper {
    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    RestaurantTable populateTableWithPresentTableFields(@MappingTarget RestaurantTable table1, RestaurantTable table2);
}
