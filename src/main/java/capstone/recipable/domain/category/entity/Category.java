package capstone.recipable.domain.category.entity;

import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

}
