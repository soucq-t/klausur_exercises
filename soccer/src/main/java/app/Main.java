package app;

import domain.Formation422;
import domain.Formation433;
import domain.SoccerTeam;

public class Main {
    public static void main(String []args) {
        SoccerTeam team = new SoccerTeam();
        team.setFormation(new Formation433());
        team.play();
        team.setFormation(new Formation422());
         team.play();
    }
}
