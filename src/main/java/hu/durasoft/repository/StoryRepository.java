package hu.durasoft.repository;

import hu.durasoft.domain.Blogger;
import hu.durasoft.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
//public interface StoryRepository extends CrudRepository<Story, Long> {
public class StoryRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Story> findAll() {
        String sql = "SELECT * FROM story ORDER BY posted DESC";
        return jdbcTemplate.query(sql, (rs, i) -> new Story(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("posted"),
                findBloggerById(rs.getLong("blogger_id"))
        ));
    }

    public Story findByTitleIgnoreCase(String title) {
        String sql = "SELECT * FROM story WHERE LOWER(title) = LOWER(?) ORDER BY posted DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Story(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("posted"),
                findBloggerById(rs.getLong("blogger_id"))
        ), title);
    }

    public Story findFirstByOrderByPostedDesc() {
        String sql = "SELECT * FROM story ORDER BY posted DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Story(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("posted"),
                findBloggerById(rs.getLong("blogger_id"))
        ));
    }

    public List<Story> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name) {
        Blogger blogger = findBloggerByName(name);
        String sql = "SELECT * FROM story WHERE blogger_id = (SELECT id FROM blogger WHERE LOWER(name) = LOWER(?)) ORDER BY posted DESC";
        return jdbcTemplate.query(sql, (rs, i) -> new Story(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getDate("posted"),
                blogger
        ), name);
    }

    public Blogger findBloggerById(Long id) {
        String sql = "SELECT * FROM blogger WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Blogger(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("age")
        ), id);
    }

    public Blogger findBloggerByName(String name) {
        String sql = "SELECT * FROM blogger WHERE LOWER(name) = LOWER(?)";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Blogger(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("age")
        ), name);
    }

//    List<Story> findAll();
//
//    Story findFirstByOrderByPostedDesc();
//
//    //@Query(value = "SELECT * FROM story WHERE title = ?1 LIMIT 1", nativeQuery = true)
//    //Story findByTitleIgnoreCase(String title);
//    //@Query(value = "SELECT * FROM story WHERE title = :title LIMIT 1", nativeQuery = true)
//    @Query(value = "SELECT s FROM Story s WHERE s.title = :title")
//    Story findByTitleIgnoreCase(@Param("title") String title);
//
//    List<Story> findAllByBloggerNameIgnoreCaseOrderByPostedDesc(String name);

}
