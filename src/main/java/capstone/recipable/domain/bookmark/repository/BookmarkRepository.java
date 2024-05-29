package capstone.recipable.domain.bookmark.repository;

import capstone.recipable.domain.bookmark.entity.Bookmark;
import capstone.recipable.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findAllByUser(User user);
}
