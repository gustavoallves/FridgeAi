package dev.studies.fridgeai.service;

import dev.studies.fridgeai.dto.FoodItemRequestDTO;
import dev.studies.fridgeai.dto.FoodItemResponseDTO;
import dev.studies.fridgeai.mapper.FoodItemMapper;
import dev.studies.fridgeai.model.FoodItem;
import dev.studies.fridgeai.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    private final FoodItemRepository foodItemRepository;
    private final FoodItemMapper foodItemMapper;

    public FoodItemService(FoodItemRepository foodItemRepository, FoodItemMapper foodItemMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemResponseDTO createFoodItem(FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItem = foodItemMapper.toModel(foodItemRequestDTO);
        FoodItem foodSaved = foodItemRepository.save(foodItem);
        return foodItemMapper.toResponseDTO(foodSaved);
    }

    public Optional<FoodItemResponseDTO> readFoodItem(Long id) {
        return foodItemRepository.findById(id)
                .map(foodItemMapper::toResponseDTO);
    }

    public List<FoodItemResponseDTO> readAllFoodItem() {
        List<FoodItem> foodItemList = foodItemRepository.findAll();
        return foodItemMapper.toResponseListDTO(foodItemList);
    }

    public FoodItemResponseDTO updateFoodItem(Long id, FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItemToUpdate = foodItemMapper.toModel(foodItemRequestDTO);
        foodItemToUpdate.setId(id);
        FoodItem foodItemUpdated = foodItemRepository.save(foodItemToUpdate);
        return foodItemMapper.toResponseDTO(foodItemUpdated);
    }

    public void deleteFoodItem(Long id) {
        //tratar com Exceptions
        foodItemRepository.deleteById(id);
    }
}