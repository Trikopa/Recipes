package recipes;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import recipes.model.Recipe;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@Validated
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") int id) {
       val recipe = recipeService.getRecipeById(id);
        return recipe.map(value -> ResponseEntity.ok().body(value)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "name", required = false) String name) {
        if (category != null) {
            return recipeService.getRecipesByCategory(category);
        } else if (name != null) {
            return recipeService.getRecipesByName(name);
        } else {
            // Handle invalid request with no category or name parameter
            return ResponseEntity.badRequest().build();
        }
    }



    @PutMapping("{id}")
    public ResponseEntity<Recipe> updateRecipe(
            @Valid @RequestBody Recipe newRecipe,
            @PathVariable("id") int id) {
        return recipeService.updateRecipe(newRecipe, id);
    }

    @PostMapping("/new")
    public Map<String, Object> postRecipe(@Valid @RequestBody Recipe newRecipe) {
        val recipe = recipeService.saveRecipe(newRecipe);
        return new HashMap<>() {{
            put("id", recipe.getId());
        }};
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable("id") int id) {
        val recipe = recipeService.getRecipeById(id);
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        recipeService.deleteRecipe(recipe.get());
        return ResponseEntity.noContent().build();
    }
}

