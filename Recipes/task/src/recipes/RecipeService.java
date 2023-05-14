package recipes;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }
    public Recipe getRecipeById(int id) {
        return recipeRepository.getById(id);
    }

    public Recipe saveRecipe(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipeRepository.delete(recipe);
    }
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
