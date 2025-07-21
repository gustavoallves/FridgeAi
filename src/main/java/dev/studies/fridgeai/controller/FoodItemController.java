package dev.studies.fridgeai.controller;

import dev.studies.fridgeai.dto.FoodItemRequestDTO;
import dev.studies.fridgeai.dto.FoodItemResponseDTO;
import dev.studies.fridgeai.service.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fooditems")
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItemResponseDTO> createFoodItem(@RequestBody FoodItemRequestDTO foodItemRequestDTO) {
        FoodItemResponseDTO foodItemResponseDTO = foodItemService.createFoodItem(foodItemRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(foodItemResponseDTO.id())
                .toUri();
        return ResponseEntity.created(location).body(foodItemResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FoodItemResponseDTO>> readFoodItem(@PathVariable Long id) {
        return ResponseEntity.ok(foodItemService.readFoodItem(id));
    }

    @GetMapping
    public ResponseEntity<List<FoodItemResponseDTO>> readAllFoodItem(){
        return ResponseEntity.ok(foodItemService.readAllFoodItem());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemResponseDTO> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemRequestDTO foodItemRequestDTO) {
        return foodItemService.readFoodItem(id)
                .map( ifItemExist -> {
                    FoodItemResponseDTO updatedItem = foodItemService.updateFoodItem(id, foodItemRequestDTO);
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(updatedItem.id())
                            .toUri();
                    return ResponseEntity.created(location).body(updatedItem);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.ok("Food item deleted.");
    }

}