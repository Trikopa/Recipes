package recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipesApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecipesApplication.class, args);
    }
}


class ArrayUtils {
    public <E> E getFirst (E[] arr) {
        if (arr.length == 0) {
            return null;
        } else {
            return arr[0];
        }
    }
}