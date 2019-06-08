package lk.shashan.client.bussiness.Custom.impl;

import lk.shashan.client.bussiness.Custom.RegisterBO;
import lk.shashan.client.dao.MovieRegisterDAO;
import lk.shashan.client.dto.RegisterMovieDTO;
import lk.shashan.client.entity.ActorMovie;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
@Transactional
public class RegisterMovieimpl implements RegisterBO {
    private MovieRegisterDAO movieRegisterDAO;


    @Override
    public List<RegisterMovieDTO> getAllActor() throws Exception {
        return null;
    }

    @Override
    public void saveRegisterMovie(RegisterMovieDTO dto) throws Exception {
        movieRegisterDAO.save(new ActorMovie(dto.getActor(), dto.getMovie(),dto.getRole()));
    }

    @Override
    public void updateRegisterMovie(RegisterMovieDTO dto) throws Exception {

    }

    @Override
    public void removeRegisterMovie(int id) throws Exception {

    }

    @Override
    public RegisterMovieDTO getRegisterMovieeById(int id) throws Exception {
        return null;
    }
}
