package entities;

import java.util.Date;

public class Tournament {
    private int id;
    private String name;
    private Sport sport;
    private Date date_from;
    private Date date_to;
    private String place;
    String result;

    public Date getDate_from() {
        return date_from;
    }

    public void setDate_from(Date date_from) {
        this.date_from = date_from;
    }

    public Date getDate_to() {
        return date_to;
    }

    public void setDate_to(Date date_to) {
        this.date_to = date_to;
    }

    public Tournament(int id, String name, Sport sport, Date date_from, Date date_to, String place, String result) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.date_from = date_from;
        this.date_to = date_to;
        this.place = place;
        this.result = result;
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


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
