package lk.shashan.client.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ActorMovie extends SuperEntity {

    @EmbeddedId
    private ActorMoviePK actorMoviePK;
    private String role;
    @ManyToOne
    @JoinColumn(name="actorID", referencedColumnName = "id",insertable = false,updatable = false)
    private Actor actor;
    @ManyToOne
    @JoinColumn(name="movieID", referencedColumnName = "id",insertable = false,updatable = false)
    private Movies movies;

    public ActorMovie() {
    }

    public ActorMovie(ActorMoviePK actorMoviePK, String role) {
        this.actorMoviePK = actorMoviePK;
        this.role = role;
    }
    public ActorMovie(int actorID,int movieID, String role) {
        this.actorMoviePK = actorMoviePK;
        this.role = role;
    }


    public ActorMoviePK getActorMoviePK() {
        return actorMoviePK;
    }

    public void setActorMoviePK(ActorMoviePK actorMoviePK) {
        this.actorMoviePK = actorMoviePK;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ActorMovie{" +
                "actorMoviePK=" + actorMoviePK +
                ", role='" + role + '\'' +
                '}';
    }

    public Actor getActor() {
        return actor;
    }

    public Movies getMovies() {
        return movies;
    }
}
