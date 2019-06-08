package lk.shashan.client.dao;

import lk.shashan.client.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieDAO extends JpaRepository<Movies,Integer> {



    @Query(value = "select id from movies order by id desc limit 1",nativeQuery = true)
    public int getOrderByIdDesc();
}
