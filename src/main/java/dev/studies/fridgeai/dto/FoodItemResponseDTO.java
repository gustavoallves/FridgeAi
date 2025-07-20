package dev.studies.fridgeai.dto;

import dev.studies.fridgeai.model.FoodItemCategory;

import java.time.LocalDate;

public record FoodItemResponseDTO(
        Long id,
        String name,
        FoodItemCategory category,
        Integer quantity,
        LocalDate expirationDate
) {
}