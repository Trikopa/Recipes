package recipes;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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
        val recipes = recipeService.getAllRecipes();
        val recipe = recipes.stream().filter(it -> it.getId() == id).toList();
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recipe.get(0));
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
        val recipes = recipeService.getAllRecipes();
        val recipe = recipes.stream().filter(it -> it.getId() == id).toList();
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        recipeService.deleteRecipe(recipe.get(0));
        return ResponseEntity.noContent().build();
    }
}

