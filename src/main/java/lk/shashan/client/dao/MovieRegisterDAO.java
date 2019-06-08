package lk.shashan.client.dao;

import lk.shashan.client.entity.ActorMovie;
import lk.shashan.client.entity.ActorMoviePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRegisterDAO extends JpaRepository<ActorMovie, ActorMoviePK> {



}
