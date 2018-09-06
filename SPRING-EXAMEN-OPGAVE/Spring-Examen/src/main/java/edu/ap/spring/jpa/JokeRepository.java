package edu.ap.spring.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends CrudRepository<String, Long> {
    public List<String> findAll();
}
