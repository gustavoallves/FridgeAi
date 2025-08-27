package dev.studies.fridgeai.mapper;

import dev.studies.fridgeai.dto.FoodItemRequestDTO;
import dev.studies.fridgeai.dto.FoodItemResponseDTO;
import dev.studies.fridgeai.model.FoodItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {
    FoodItem toModel(FoodItemRequestDTO foodItemRequestDTO);

    FoodItemResponseDTO toResponseDTO(FoodItem foodItem);

    List<FoodItemResponseDTO> toResponseListDTO(List<FoodItem> foodItemList);
}