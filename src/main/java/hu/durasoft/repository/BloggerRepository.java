package hu.durasoft.repository;

import hu.durasoft.domain.Blogger;
//import org.springframework.data.repository.CrudRepository;
import hu.durasoft.domain.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface BloggerRepository extends CrudRepository<Blogger, Long> {
public class BloggerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BloggerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Blogger> findAll() {
        String sql = "SELECT * FROM blogger ORDER BY id";
        return jdbcTemplate.query(sql, (rs, i) -> new Blogger(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("age")
        ));
    }

}
