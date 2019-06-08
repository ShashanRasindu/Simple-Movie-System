package lk.shashan.client.bussiness.Custom;

import lk.shashan.client.dto.RegisterMovieDTO;

import java.util.List;

public interface RegisterBO {
    public List<RegisterMovieDTO> getAllActor() throws Exception;

    public void saveRegisterMovie(RegisterMovieDTO dto)  throws Exception;

    public void updateRegisterMovie(RegisterMovieDTO dto)throws Exception;

    public void removeRegisterMovie(int id)throws Exception;

    public RegisterMovieDTO getRegisterMovieeById(int  id)throws Exception;

}
