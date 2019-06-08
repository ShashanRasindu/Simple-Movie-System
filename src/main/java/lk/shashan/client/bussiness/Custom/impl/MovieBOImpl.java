package lk.shashan.client.bussiness.Custom.impl;

import lk.shashan.client.bussiness.Custom.MovieBO;
import lk.shashan.client.dao.MovieDAO;
import lk.shashan.client.dto.MoviesDTO;
import lk.shashan.client.entity.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class MovieBOImpl implements MovieBO {
    @Autowired
    private MovieDAO movieDAO;
    @Override
    public List<MoviesDTO> getAllMovies() throws Exception {
        List<MoviesDTO> moviesDTOS = movieDAO.findAll().stream().map(movies -> new MoviesDTO(movies.getId(), movies.getName())).collect(Collectors.toList());
        return moviesDTOS;
    }

    @Override
    public void saveMovie(MoviesDTO dto) throws Exception {
        movieDAO.save(new Movies(dto.getId(), dto.getName()));
    }

    @Override
    public void updateMovie(MoviesDTO dto) throws Exception {
        movieDAO.save(new Movies(dto.getId(), dto.getName()));
    }

    @Override
    public void removeMovie(int id) throws Exception {
        movieDAO.deleteById(id);
    }



    @Override
    public int getNewId() {
        return movieDAO.getOrderByIdDesc()+1;
    }

}
