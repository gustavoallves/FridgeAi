package dev.studies.fridgeai.dto;

import dev.studies.fridgeai.model.FoodItemCategory;

import java.time.LocalDate;

public record FoodItemRequestDTO(  //Adicionar validações
        String name,
        FoodItemCategory category,
        Integer quantity,
        LocalDate expirationDate
) {
}
