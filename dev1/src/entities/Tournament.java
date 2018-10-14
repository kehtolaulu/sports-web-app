package entities;

import java.util.Date;

public class Tournament {
    private int id;
    private String name;
    private Sport sport;
    private Date datetime; //?
    private String place;

    public Tournament(int id, String name, Sport sport, Date datetime, String place) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.datetime = datetime;
        this.place = place;
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

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
