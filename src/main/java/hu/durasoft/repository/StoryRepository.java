package hu.durasoft.repository;

import hu.durasoft.domain.Story;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends CrudRepository<Story, Long> {

    List<Story> findAll();

    Story findFirstByOrderByPostedDesc();

    //@Query(value = "SELECT * FROM story WHERE title = ?1 LIMIT 1", nativeQuery = true)
    //Story findByTitle(String title);
    //@Query(value = "SELECT * FROM story WHERE title = :title LIMIT 1", nativeQuery = true)
    @Query(value = "SELECT s FROM Story s WHERE s.title = :title")
    Story findByTitle(@Param("title") String title);

    List<Story> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);

}
