package spencercjh.crabscore.OBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class TasteScoreOBJ {
    private int group_id;
    private int crab_sex;
    private int user_id;
    private double score_fin;
    private double score_ygys;
    private double score_sys;
    private double score_ghys;
    private double score_lxwxw;
    private double score_gh;
    private double score_fbjr;
    private double score_bzjr;
    private int competition_id;
    private String company_name;

    public TasteScoreOBJ() {

    }

    //    查看成绩页面
    public TasteScoreOBJ(int group_id, String company_name, double score_fin) {
        this.group_id = group_id;
        this.company_name = company_name;
        this.score_fin = score_fin;
    }

    public TasteScoreOBJ(int group_id, int crab_sex, int user_id, double score_fin, double score_ygys,
                         double score_sys, double score_ghys, double score_lxwxw, double score_gh,
                         double score_fbjr, double score_bzjr, int competition_id) {
        this.group_id = group_id;
        this.crab_sex = crab_sex;
        this.user_id = user_id;
        this.score_fin = score_fin;
        this.score_ygys = score_ygys;
        this.score_sys = score_sys;
        this.score_ghys = score_ghys;
        this.score_lxwxw = score_lxwxw;
        this.score_gh = score_gh;
        this.score_fbjr = score_fbjr;
        this.score_bzjr = score_bzjr;
        this.competition_id = competition_id;
    }

}
