package capstone.recipable.domain.recipe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecipeVideos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String videoUrl;

    private String title;

    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public static RecipeVideos of(String videoUrl, String title, String thumbnail, Recipe recipe) {
        return RecipeVideos.builder()
                .videoUrl("https://www.youtube.com/watch?v="+videoUrl)
                .title(title)
                .thumbnail(thumbnail)
                .recipe(recipe)
                .build();
    }
}
