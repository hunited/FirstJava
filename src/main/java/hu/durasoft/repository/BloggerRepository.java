package hu.durasoft.repository;

import hu.durasoft.domain.Blogger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloggerRepository extends CrudRepository<Blogger, Long> {

    List<Blogger> findAll();

}
