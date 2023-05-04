package com.restaurant.system.service.mapper;

import com.restaurant.system.model.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedbackMapper {
    FeedbackMapper INSTANCE = Mappers.getMapper(FeedbackMapper.class);

    Feedback populateFeedbackWithPresentFeedbackFields(@MappingTarget Feedback feedback1, Feedback feedback2);
}
