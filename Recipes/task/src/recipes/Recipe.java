package recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Recipe {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String description;

    @ElementCollection
    @NotNull
    @NotEmpty
    private List<String> ingredients;

    @ElementCollection
    @NotNull
    @NotEmpty
    private List<String> directions;
}