package lk.shashan.client.bussiness.Custom;

import lk.shashan.client.dto.MoviesDTO;

import java.util.List;

public interface MovieBO {

    public List<MoviesDTO> getAllMovies() throws Exception;

    public void saveMovie(MoviesDTO dto)  throws Exception;

    public void updateMovie(MoviesDTO dto)throws Exception;

    public void removeMovie(int id)throws Exception;



    public int getNewId();
}
