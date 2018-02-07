package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class TasteScoreOBJ implements Serializable {
    private int group_id;
    private int crab_sex;
    private int user_id;
    private double score_fin;
    private double score_ygys;
    private double score_sys;
    private double score_ghys;
    private double score_xwxw;
    private double score_gh;
    private double score_fbjr;
    private double score_bzjr;
    private int competition_id;

    public TasteScoreOBJ() {

    }

    //    评委评分界面
    public TasteScoreOBJ(int group_id, int competition_id) {
        this.competition_id = competition_id;
        this.group_id = group_id;
    }

    //  查分
    public TasteScoreOBJ(int group_id, int crab_sex, double score_fin, double score_ygys,
                         double score_sys, double score_ghys, double score_xwxw, double score_gh,
                         double score_fbjr, double score_bzjr, int competition_id) {
        this.group_id = group_id;
        this.crab_sex = crab_sex;
        this.score_fin = score_fin;
        this.score_ygys = score_ygys;
        this.score_sys = score_sys;
        this.score_ghys = score_ghys;
        this.score_xwxw = score_xwxw;
        this.score_gh = score_gh;
        this.score_fbjr = score_fbjr;
        this.score_bzjr = score_bzjr;
        this.competition_id = competition_id;
    }

    //    评分
    public TasteScoreOBJ(int group_id, int crab_sex, int user_id, double score_fin, double score_ygys,
                         double score_sys, double score_ghys, double score_xwxw, double score_gh,
                         double score_fbjr, double score_bzjr, int competition_id) {
        this.group_id = group_id;
        this.crab_sex = crab_sex;
        this.user_id = user_id;
        this.score_fin = score_fin;
        this.score_ygys = score_ygys;
        this.score_sys = score_sys;
        this.score_ghys = score_ghys;
        this.score_xwxw = score_xwxw;
        this.score_gh = score_gh;
        this.score_fbjr = score_fbjr;
        this.score_bzjr = score_bzjr;
        this.competition_id = competition_id;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public double getScore_bzjr() {
        return score_bzjr;
    }

    public void setScore_bzjr(double score_bzjr) {
        this.score_bzjr = score_bzjr;
    }

    public double getScore_fbjr() {
        return score_fbjr;
    }

    public void setScore_fbjr(double score_fbjr) {
        this.score_fbjr = score_fbjr;
    }

    public double getScore_fin() {
        return score_fin;
    }

    public void setScore_fin(double score_fin) {
        this.score_fin = score_fin;
    }

    public double getScore_gh() {
        return score_gh;
    }

    public void setScore_gh(double score_gh) {
        this.score_gh = score_gh;
    }

    public double getScore_ghys() {
        return score_ghys;
    }

    public void setScore_ghys(double score_ghys) {
        this.score_ghys = score_ghys;
    }

    public double getScore_xwxw() {
        return score_xwxw;
    }

    public void setScore_xwxw(double score_xwxw) {
        this.score_xwxw = score_xwxw;
    }

    public double getScore_sys() {
        return score_sys;
    }

    public void setScore_sys(double score_sys) {
        this.score_sys = score_sys;
    }

    public double getScore_ygys() {
        return score_ygys;
    }

    public void setScore_ygys(double score_ygys) {
        this.score_ygys = score_ygys;
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
