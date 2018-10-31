package entities;

public class Team {
    private int id;
    private Sport sport;
    private String name;

    public Team(int id, Sport sport, String name) {
        this.id = id;
        this.sport = sport;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
