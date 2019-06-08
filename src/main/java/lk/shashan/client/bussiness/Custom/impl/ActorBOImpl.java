package lk.shashan.client.bussiness.Custom.impl;

import lk.shashan.client.bussiness.Custom.ActorBO;
import lk.shashan.client.dao.ActorDAO;
import lk.shashan.client.dto.ActorDTO;
import lk.shashan.client.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class ActorBOImpl implements ActorBO {
    @Autowired
    private ActorDAO actorDAO;
    @Override
    public List<ActorDTO> getAllActor() throws Exception {

        List<ActorDTO> actorDTOS = actorDAO.findAll().stream().map(actor -> new ActorDTO(actor.getId(), actor.getName(), actor.getAddress())).collect(Collectors.toList());
        return actorDTOS;

    }

    @Override
    public void saveActor(ActorDTO dto) throws Exception {
        actorDAO.save(new Actor(dto.getId(), dto.getName(), dto.getAddress()));

    }

    @Override
    public void updateActor(ActorDTO dto) throws Exception {
        actorDAO.save(new Actor(dto.getId(), dto.getName(), dto.getAddress()));
    }

    @Override
    public void removeActor(int id) throws Exception {
        actorDAO.deleteById(id);
    }

    @Override
    public ActorDTO getActorById(int id) throws Exception {
        Actor actor = actorDAO.getOne(id);
        ActorDTO actorDTO = new ActorDTO(actor.getId(), actor.getName(), actor.getAddress());

        return actorDTO;
    }

    @Override
    public int newId() {
        return actorDAO.getOrderByIdDesc()+1;
    }
}
