package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/2.
 * iClass
 */

public class QualityScoreOBJ implements Serializable {
    private int score_id;    //分数序号
    private int group_id;   //组号
    private int crab_sex;   //螃蟹性别
    private int user_id;    //评委用户ID
    private float score_fin;   //最终评分
    private float score_bts;  //体色（背）得分
    private float score_fts;   //体色（腹）得分
    private float score_ec;    //额齿
    private float score_dscc;  //第4侧齿
    private float score_bbyzt; //背部疣状突
    private int competition_id; //比赛id

    public QualityScoreOBJ() {

    }

    //    评委评分界面
    public QualityScoreOBJ(int group_id, int competition_id) {
        this.group_id = group_id;
        this.competition_id = competition_id;
    }

    //    查分
    public QualityScoreOBJ(int group_id, int crab_sex, float score_fin, float score_bts, float score_fts, float score_ec, float score_dscc, float score_bbyzt, int competition_id) {
        this.group_id = group_id;
        this.crab_sex = crab_sex;
        this.score_fin = score_fin;
        this.score_bts = score_bts;
        this.score_fts = score_fts;
        this.score_ec = score_ec;
        this.score_dscc = score_dscc;
        this.score_bbyzt = score_bbyzt;
        this.competition_id = competition_id;
    }

    //    评分
    public QualityScoreOBJ(int group_id, int crab_sex, int user_id, float score_fin, float score_bts,
                           float score_fts, float score_ec, float score_dscc, float score_bbyzt, int competition_id) {
        this.group_id = group_id;
        this.crab_sex = crab_sex;
        this.user_id = user_id;
        this.score_fin = score_fin;
        this.score_bts = score_bts;
        this.score_fts = score_fts;
        this.score_fts = score_fts;
        this.score_ec = score_ec;
        this.score_dscc = score_dscc;
        this.score_bbyzt = score_bbyzt;
        this.competition_id = competition_id;
    }

    public float getScore_bbyzt() {
        return score_bbyzt;
    }

    public void setScore_bbyzt(float score_bbyzt) {
        this.score_bbyzt = score_bbyzt;
    }

    public float getScore_bts() {
        return score_bts;
    }

    public void setScore_bts(float score_bts) {
        this.score_bts = score_bts;
    }

    public float getScore_dscc() {
        return score_dscc;
    }

    public void setScore_dscc(float score_dscc) {
        this.score_dscc = score_dscc;
    }

    public float getScore_ec() {
        return score_ec;
    }

    public void setScore_ec(float score_ec) {
        this.score_ec = score_ec;
    }

    public float getScore_fin() {
        return score_fin;
    }

    public void setScore_fin(float score_fin) {
        this.score_fin = score_fin;
    }

    public float getScore_fts() {
        return score_fts;
    }

    public void setScore_fts(float score_fts) {
        this.score_fts = score_fts;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
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

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

}
