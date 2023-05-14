package recipes;

import lombok.Data;

@Data
public class Recipe {
    String name;
    String description;
    String[] ingredients;
    String[] directions;
}
