package capstone.recipable.global.storage.category.jpa;

import capstone.recipable.global.storage.refrigerator.jpa.RefrigeratorEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    private String details;

    @ManyToOne(fetch=FetchType.LAZY)
    private RefrigeratorEntity refrigeratorId;

}
