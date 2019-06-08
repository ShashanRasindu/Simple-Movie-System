package lk.shashan.client.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movies extends SuperEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    List<ActorMovie> actorMovies =new ArrayList<>();


    public Movies() {
    }

    public Movies(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public List<ActorMovie> getactorMovies() {
        return actorMovies;
    }

    public void setActor(List<ActorMovie> actorMovies) {
        this.actorMovies = actorMovies;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
