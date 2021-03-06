package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */

public class GroupOBJ implements Serializable {
    private int group_id;
    private int company_id;
    private int competition_id;
    private float fatness_score_m;
    private float quality_score_m;
    private float taste_score_m;
    private float fatness_score_f;
    private float quality_score_f;
    private float taste_score_f;

    public GroupOBJ() {
    }

    public GroupOBJ(int group_id, int competition_id) {
        this.group_id = group_id;
        this.competition_id = competition_id;
    }

    public GroupOBJ(int group_id, int competition_id, int company_id) {
        this.group_id = group_id;
        this.competition_id = competition_id;
        this.company_id = company_id;
    }

    //  优质奖
    public GroupOBJ(int group_id, int company_id, int competition_id, float fatness_score_m, float fatness_score_f, int i) {
        this.group_id = group_id;
        this.company_id = company_id;
        this.competition_id = competition_id;
        this.fatness_score_m = fatness_score_m;
        this.fatness_score_f = fatness_score_f;
    }

    //    种质奖
    public GroupOBJ(int group_id, int company_id, int competition_id, float quality_score_m, float quality_score_f, float i) {
        this.group_id = group_id;
        this.company_id = company_id;
        this.competition_id = competition_id;
        this.quality_score_m = quality_score_m;
        this.quality_score_f = quality_score_f;
    }

    //    口感奖
    public GroupOBJ(int group_id, int company_id, int competition_id, float taste_score_m, float taste_score_f, double i) {
        this.group_id = group_id;
        this.company_id = company_id;
        this.competition_id = competition_id;
        this.taste_score_m = taste_score_m;
        this.taste_score_f = taste_score_f;
    }

    public GroupOBJ(int group_id, int company_id, float fatness_score_m, float quality_score_m,
                    float taste_score_m, float fatness_score_f, float quality_score_f, float taste_score_f) {
        this.group_id = group_id;
        this.company_id = company_id;
        this.fatness_score_m = fatness_score_m;
        this.quality_score_m = quality_score_m;
        this.taste_score_m = taste_score_m;
        this.fatness_score_f = fatness_score_f;
        this.quality_score_f = quality_score_f;
        this.taste_score_f = taste_score_f;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public float getFatness_score_f() {
        return fatness_score_f;
    }

    public void setFatness_score_f(float fatness_score_f) {
        this.fatness_score_f = fatness_score_f;
    }

    public float getFatness_score_m() {
        return fatness_score_m;
    }

    public void setFatness_score_m(float fatness_score_m) {
        this.fatness_score_m = fatness_score_m;
    }

    public float getQuality_score_f() {
        return quality_score_f;
    }

    public void setQuality_score_f(float quality_score_f) {
        this.quality_score_f = quality_score_f;
    }

    public float getQuality_score_m() {
        return quality_score_m;
    }

    public void setQuality_score_m(float quality_score_m) {
        this.quality_score_m = quality_score_m;
    }

    public float getTaste_score_f() {
        return taste_score_f;
    }

    public void setTaste_score_f(float taste_score_f) {
        this.taste_score_f = taste_score_f;
    }

    public float getTaste_score_m() {
        return taste_score_m;
    }

    public void setTaste_score_m(float taste_score_m) {
        this.taste_score_m = taste_score_m;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

}
