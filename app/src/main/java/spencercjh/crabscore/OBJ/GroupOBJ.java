package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/5.
 * iClass
 */

public class GroupOBJ implements Serializable {
    private int group_id;
    private int company_id;
    private double fatness_score_m;
    private double quality_score_m;
    private double taste_score_m;
    private double fatness_score_f;
    private double quality_score_f;
    private double taste_score_f;

    public GroupOBJ() {
    }

    public GroupOBJ(int group_id) {
        this.group_id = group_id;
    }
//  优质奖
    public GroupOBJ(int group_id, int company_id, double fatness_score_m, double fatness_score_f) {
        this.group_id = group_id;
        this.company_id = company_id;
        this.fatness_score_m = fatness_score_m;
        this.fatness_score_f = fatness_score_f;
    }

    public GroupOBJ(int group_id, int company_id, double fatness_score_m, double quality_score_m,
                    double taste_score_m, double fatness_score_f, double quality_score_f, double taste_score_f) {
        this.group_id = group_id;
        this.company_id = company_id;
        this.fatness_score_m = fatness_score_m;
        this.quality_score_m = quality_score_m;
        this.taste_score_m = taste_score_m;
        this.fatness_score_f = fatness_score_f;
        this.quality_score_f = quality_score_f;
        this.taste_score_f = taste_score_f;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public double getFatness_score_f() {
        return fatness_score_f;
    }

    public void setFatness_score_f(double fatness_score_f) {
        this.fatness_score_f = fatness_score_f;
    }

    public double getFatness_score_m() {
        return fatness_score_m;
    }

    public void setFatness_score_m(double fatness_score_m) {
        this.fatness_score_m = fatness_score_m;
    }

    public double getQuality_score_f() {
        return quality_score_f;
    }

    public void setQuality_score_f(double quality_score_f) {
        this.quality_score_f = quality_score_f;
    }

    public double getQuality_score_m() {
        return quality_score_m;
    }

    public void setQuality_score_m(double quality_score_m) {
        this.quality_score_m = quality_score_m;
    }

    public double getTaste_score_f() {
        return taste_score_f;
    }

    public void setTaste_score_f(double taste_score_f) {
        this.taste_score_f = taste_score_f;
    }

    public double getTaste_score_m() {
        return taste_score_m;
    }

    public void setTaste_score_m(double taste_score_m) {
        this.taste_score_m = taste_score_m;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }
}
