package recipes.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import recipes.data.RecipeRepository;
import recipes.model.Recipe;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Optional<Recipe> getRecipeById(int id) {
        return recipeRepository.findById(id);
    }

    public Recipe saveRecipe(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    public ResponseEntity<Recipe> updateRecipe(Recipe newRecipe, int id) {
       val recipe = recipeRepository.findById(id);
       if (recipe.isPresent()) {
           val r = recipe.get();
           r.setName(newRecipe.getName());
           r.setDescription(newRecipe.getDescription());
           r.setDirections(newRecipe.getDirections());
           r.setIngredients(newRecipe.getIngredients());
           r.setDate(LocalDateTime.now().toString());
           recipeRepository.save(r);
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }

    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }

    public ResponseEntity<List<Recipe>> getRecipesByCategory(String category) {
        val recipes = recipeRepository
                .findAll()
                .stream()
                .filter(it -> Objects.equals(it.getCategory().toLowerCase(), category.toLowerCase()))
                .sorted(Comparator.comparing(Recipe::getDate).reversed())
                .toList();
        return ResponseEntity.ok(recipes);
    }

    public ResponseEntity<List<Recipe>> getRecipesByName(String name) {
        val recipes = recipeRepository
                .findAll()
                .stream()
                .filter(it -> it.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted(Comparator.comparing(Recipe::getDate).reversed())
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }


}
