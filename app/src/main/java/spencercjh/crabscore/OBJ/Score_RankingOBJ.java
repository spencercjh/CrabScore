package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Score_RankingOBJ implements Serializable {
    private int order;
    private int group_id;
    private String company_name;
    private double score;

    public Score_RankingOBJ() {

    }

    public Score_RankingOBJ(int order, int group_id, String company_name, double score) {
        this.order = order;
        this.group_id = group_id;
        this.company_name = company_name;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
