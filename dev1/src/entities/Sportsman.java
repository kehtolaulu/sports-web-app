package entities;

public class Sportsman {
    private int id;
    private Team team;
    private String name;
    private String bio;
    private String photo;

    public Sportsman(int id, Team team, String name, String bio, String photo) {
        this.id = id;
        this.team = team;
        this.name = name;
        this.bio = bio;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
