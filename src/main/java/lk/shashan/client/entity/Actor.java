package lk.shashan.client.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actor extends SuperEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private  String name;
    private String address;

    @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    List<ActorMovie> actorMovies =new ArrayList<>();

    public Actor() {
    }

    public Actor(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public List<ActorMovie> getActorMovies() {
        return actorMovies;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}
