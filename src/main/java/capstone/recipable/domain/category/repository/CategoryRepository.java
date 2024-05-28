package capstone.recipable.domain.category.repository;

import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByRefrigerator(Refrigerator refrigerator);

    Optional<Category> findByCategoryName(String newCategoryName);

    Optional<Category> findByCategoryNameAndRefrigerator(String newCategoryName, Refrigerator refrigerator);
}
