package com.restaurant.system.service.mapper;

import com.restaurant.system.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {
    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    Restaurant populateRestaurantWithPresentRestaurantFields(@MappingTarget Restaurant restaurant1, Restaurant restaurant2);
}
