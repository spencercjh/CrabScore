package spencercjh.crabscore.OBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Competition_Info_OBJ {
    private String competition_id;  //比赛id
    private int competition_year;   //年份
    private String note;    //备注
    private double var_fatness_m;    //雄蟹肥满度参数
    private double var_weight_m; //雄蟹体重参数
    private double var_mfatness_sd;  //雄蟹肥满度标准差参数
    private double var_mweight_sd;   //雄蟹体重标准差参数
    private double var_fatness_f;    //雌蟹肥满度参数
    private double var_weight_f; //雌蟹体重参数
    private double var_ffatness_sd;  //雌蟹肥满度标准差参数
    private double var_fweight_sd;   //雌蟹体重标准差参数
    private int result_fatness; //肥满度及蟹王蟹后结果
    private int result_quality; //种质评比排名
    private int result_taste;   //口感评比排名

    public Competition_Info_OBJ() {

    }

    //    大赛列表中 年份-备注 构造函数
    public Competition_Info_OBJ(int competition_year, String note) {
        this.competition_year = competition_year;
        this.note = note;
    }

    public Competition_Info_OBJ(int competition_year, String note, double var_fatness_m, double var_fatness_f,
                                double var_weight_m, double var_mfatness_sd, double var_mweight_sd,
                                double var_weight_f, double var_ffatness_sd, double var_fweight_sd,
                                int result_fatness, int result_quality, int result_taste) {
        this.competition_year = competition_year;
        this.var_fatness_m = var_fatness_m;
        this.var_fatness_f = var_fatness_f;
        this.var_weight_m = var_weight_m;
        this.var_mfatness_sd = var_mfatness_sd;
        this.var_mweight_sd = var_mweight_sd;
        this.var_weight_f = var_weight_f;
        this.var_ffatness_sd = var_ffatness_sd;
        this.var_fweight_sd = var_fweight_sd;
        this.result_fatness = result_fatness;
        this.result_quality = result_quality;
        this.result_taste = result_taste;
        this.note = note;
    }

    public double getVar_fatness_f() {
        return var_fatness_f;
    }

    public void setVar_fatness_f(double var_fatness_f) {
        this.var_fatness_f = var_fatness_f;
    }

    public double getVar_fatness_m() {
        return var_fatness_m;
    }

    public void setVar_fatness_m(double var_fatness_m) {
        this.var_fatness_m = var_fatness_m;
    }

    public double getVar_ffatness_sd() {
        return var_ffatness_sd;
    }

    public void setVar_ffatness_sd(double var_ffatness_sd) {
        this.var_ffatness_sd = var_ffatness_sd;
    }

    public double getVar_fweight_sd() {
        return var_fweight_sd;
    }

    public void setVar_fweight_sd(double var_fweight_sd) {
        this.var_fweight_sd = var_fweight_sd;
    }

    public double getVar_mfatness_sd() {
        return var_mfatness_sd;
    }

    public void setVar_mfatness_sd(double var_mfatness_sd) {
        this.var_mfatness_sd = var_mfatness_sd;
    }

    public double getVar_mweight_sd() {
        return var_mweight_sd;
    }

    public void setVar_mweight_sd(double var_mweight_sd) {
        this.var_mweight_sd = var_mweight_sd;
    }

    public double getVar_weight_f() {
        return var_weight_f;
    }

    public void setVar_weight_f(double var_weight_f) {
        this.var_weight_f = var_weight_f;
    }

    public double getVar_weight_m() {
        return var_weight_m;
    }

    public void setVar_weight_m(double var_weight_m) {
        this.var_weight_m = var_weight_m;
    }

    public int getCompetition_year() {
        return competition_year;
    }

    public void setCompetition_year(int competition_year) {
        this.competition_year = competition_year;
    }

    public int getResult_fatness() {
        return result_fatness;
    }

    public void setResult_fatness(int result_fatness) {
        this.result_fatness = result_fatness;
    }

    public int getResult_quality() {
        return result_quality;
    }

    public void setResult_quality(int result_quality) {
        this.result_quality = result_quality;
    }

    public int getResult_taste() {
        return result_taste;
    }

    public void setResult_taste(int result_taste) {
        this.result_taste = result_taste;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}