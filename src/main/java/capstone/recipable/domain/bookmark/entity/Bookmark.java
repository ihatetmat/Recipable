package capstone.recipable.domain.bookmark.entity;

import capstone.recipable.domain.recipe.entity.Recipe;
import capstone.recipable.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userId;

    @OneToOne
    private Recipe recipeId;

    public static Bookmark of(Long id, User userId, Recipe recipeId) {
        return Bookmark.builder()
                .id(id)
                .userId(userId)
                .recipeId(recipeId)
                .build();
    }
}
