package domain;

public class SoccerTeam {
    private Formation formation;

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
    public void play(){
        formation.play();
    }
}
