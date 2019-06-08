package lk.shashan.client.bussiness.Custom;

import lk.shashan.client.dto.ActorDTO;

import java.util.List;

public interface ActorBO {

    public List<ActorDTO> getAllActor() throws Exception;

    public void saveActor(ActorDTO dto)  throws Exception;

    public void updateActor(ActorDTO dto)throws Exception;

    public void removeActor(int id)throws Exception;

    public ActorDTO getActorById(int  id)throws Exception;

    public int newId();
}
