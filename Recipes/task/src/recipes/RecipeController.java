package recipes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final Map<Integer, Recipe> recipes = new HashMap<>();

    @GetMapping("{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") int id) {
        Recipe recipe = recipes.get(id);
        if (recipe == null) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok().body(recipe);
        }
    }

            @PostMapping("/new")
            public Map<String, Object> postRecipe(@RequestBody Recipe newRecipe) {
                int hs = newRecipe.hashCode();
                recipes.put(hs, newRecipe);
                return new HashMap<>() {{
                    put("id", hs);
                }};
            }
        }