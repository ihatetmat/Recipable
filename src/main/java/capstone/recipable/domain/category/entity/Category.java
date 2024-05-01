package capstone.recipable.domain.category.entity;

import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String details;

    @ManyToOne(fetch=FetchType.LAZY)
    private Refrigerator refrigeratorId;

    public static Category of(Long id, String categoryName, String details, Refrigerator refrigerator) {
        return Category.builder()
                .id(id)
                .categoryName(categoryName)
                .details(details)
                .refrigeratorId(refrigerator)
                .build();
    }
}
