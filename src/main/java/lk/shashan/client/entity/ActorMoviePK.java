package lk.shashan.client.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class ActorMoviePK implements Serializable {

    private int actorID;
    private int movieID;

    public ActorMoviePK() {
    }

    public ActorMoviePK(int actoeID, int movieID) {
        this.actorID = actoeID;
        this.movieID = movieID;
    }

    public int getActoeID() {
        return actorID;
    }

    public void setActoeID(int actorID) {
        this.actorID = actorID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "ActorMoviePK{" +
                "actoeID=" + actorID +
                ", movieID=" + movieID +
                '}';
    }
}
