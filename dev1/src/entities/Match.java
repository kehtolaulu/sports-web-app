package entities;

import java.util.Date;

public class Match {
    private int id;
    private Date datetime;
    private String result;
    private Sport sport;
    private Tournament tournament;
    private Team team1;
    private Team team2;

    public Match(int id, Date datetime, String result, Sport sport, Tournament tournament, Team team1, Team team2) {
        this.id = id;
        this.datetime = datetime;
        this.result = result;
        this.sport = sport;
        this.tournament = tournament;
        this.team1 = team1;
        this.team2 = team2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
