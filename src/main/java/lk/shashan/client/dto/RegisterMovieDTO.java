package lk.shashan.client.dto;

public class RegisterMovieDTO {
    private int actor;
    private int movie;
    private String role;
    public RegisterMovieDTO() {
    }


    public RegisterMovieDTO(int actor, int movie,String role) {
        this.setActor(actor);
        this.setMovie(movie);
        this.setRole(role);
    }

    public int getActor() {
        return actor;
    }

    public void setActor(int actor) {
        this.actor = actor;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
