package spencercjh.crabscore.OBJ;

/**
 * Created by spencercjh on 2018/2/2.
 * iClass
 */

public class CompititionOBJ {
    private int ranking;
    private int group;
    private String unit;
    private String score;

    public CompititionOBJ() {

    }

    public CompititionOBJ(int ranking, int group, String unit, String score) {
        this.ranking = ranking;
        this.group = group;
        this.unit = unit;
        this.score = score;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
