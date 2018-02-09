package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class CrabOBJ implements Serializable {
    private int crab_id;
    private int group_id;
    private int crab_sex;
    private String crab_label;
    private float crab_weight;
    private float crab_length;
    private float crab_fatness;
    private int competition_id;

    public CrabOBJ() {

    }

    public CrabOBJ(int group_id, int crab_sex, int competition_id) {
        this.crab_sex = crab_sex;
        this.competition_id = competition_id;
        this.group_id = group_id;
    }

    public CrabOBJ(int crab_id, int group_id, int crab_sex, String crab_label, float crab_weight, float crab_length, float crab_fatness, int competition_id) {
        this.crab_id = crab_id;
        this.group_id = group_id;
        this.crab_sex = crab_sex;
        this.crab_label = crab_label;
        this.crab_weight = crab_weight;
        this.crab_length = crab_length;
        this.crab_fatness = crab_fatness;
        this.competition_id = competition_id;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public float getCrab_fatness() {
        return crab_fatness;
    }

    public void setCrab_fatness(float crab_fatness) {
        this.crab_fatness = crab_fatness;
    }

    public float getCrab_length() {
        return crab_length;
    }

    public void setCrab_length(float crab_length) {
        this.crab_length = crab_length;
    }

    public float getCrab_weight() {
        return crab_weight;
    }

    public void setCrab_weight(float crab_weight) {
        this.crab_weight = crab_weight;
    }

    public int getCrab_id() {
        return crab_id;
    }

    public void setCrab_id(int crab_id) {
        this.crab_id = crab_id;
    }

    public int getCrab_sex() {
        return crab_sex;
    }

    public void setCrab_sex(int crab_sex) {
        this.crab_sex = crab_sex;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getCrab_label() {
        return crab_label;
    }

    public void setCrab_label(String crab_label) {
        this.crab_label = crab_label;
    }
}
