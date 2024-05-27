package capstone.recipable.domain.category.repository;

import capstone.recipable.domain.category.entity.Category;
import capstone.recipable.domain.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByRefrigeratorId(Refrigerator refrigerator);
}
