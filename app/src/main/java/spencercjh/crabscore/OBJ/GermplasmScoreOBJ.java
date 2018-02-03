package spencercjh.crabscore.OBJ;

/**
 * Created by spencercjh on 2018/2/2.
 * iClass
 */

public class GermplasmScoreOBJ {
    private int group_id;   //组号
    private int crab_sex;   //螃蟹性别
    private int user_id;    //评委用户ID
    private double score_fin;   //最终评分
    private double score_bts;  //体色（背）得分
    private double score_fts;   //体色（腹）得分
    private double score_ec;    //额齿
    private double score_dscc;  //第4侧齿
    private double score_bbyzt; //背部疣状突
    private int competition_id; //比赛id
    private String company_name;    //参选单位

    public GermplasmScoreOBJ() {

    }

    //查看成绩界面
    public GermplasmScoreOBJ(int group_id, String company_name, double score_fin) {
        this.group_id = group_id;
        this.company_name = company_name;
        this.score_fin = score_fin;
    }

    public GermplasmScoreOBJ(int group_id, int crab_sex, int user_id, double score_fin, double score_bts,
                             double score_fts, double score_ec, double score_dscc, double score_bbyzt, int competition_id) {
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

    public double getScore_bbyzt() {
        return score_bbyzt;
    }

    public void setScore_bbyzt(double score_bbyzt) {
        this.score_bbyzt = score_bbyzt;
    }

    public double getScore_bts() {
        return score_bts;
    }

    public void setScore_bts(double score_bts) {
        this.score_bts = score_bts;
    }

    public double getScore_dscc() {
        return score_dscc;
    }

    public void setScore_dscc(double score_dscc) {
        this.score_dscc = score_dscc;
    }

    public double getScore_ec() {
        return score_ec;
    }

    public void setScore_ec(double score_ec) {
        this.score_ec = score_ec;
    }

    public double getScore_fin() {
        return score_fin;
    }

    public void setScore_fin(double score_fin) {
        this.score_fin = score_fin;
    }

    public double getScore_fts() {
        return score_fts;
    }

    public void setScore_fts(double score_fts) {
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

}
