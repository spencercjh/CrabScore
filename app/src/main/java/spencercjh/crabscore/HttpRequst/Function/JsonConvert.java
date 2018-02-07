package spencercjh.crabscore.HttpRequst.Function;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.CompetitionOBJ;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

@SuppressWarnings("LoopStatementThatDoesntLoop")
public class JsonConvert {
    //    大赛切换中的大赛列表：比赛号-年份-备注
    public static ArrayList<CompetitionOBJ> convert_Competition_List(String json_string) {
        ArrayList<CompetitionOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                int competition_id = list_item.getInt("competition_id");
                String competition_year = list_item.getString("competition_year");
                String note = list_item.getString("note");
                CompetitionOBJ competition_info_obj = new CompetitionOBJ(competition_id, competition_year, note);
                list.add(competition_info_obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    用户：用户名-显示名-用户组-状态-email-比赛id 多用户
    public static ArrayList<UserOBJ> convert_User_List(String json_string) {
        ArrayList<UserOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                String user_name = list_item.getString("user_name");
                String display_name = list_item.getString("display_name");
                int role_id = list_item.getInt("role_id");
                int status = list_item.getInt("status");
                String email = list_item.getString("email");
                int competition_id = list_item.getInt("competition_id");
                UserOBJ userOBJ = new UserOBJ(user_name, display_name, role_id, status, email, competition_id);
                list.add(userOBJ);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    用户：用户名-显示名-用户组-状态-email-比赛id 单用户
    public static UserOBJ convert_UserOBJ(String json_string) {
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                String user_name = list_item.getString("user_name");
                String display_name = list_item.getString("display_name");
                int role_id = list_item.getInt("role_id");
                int status = list_item.getInt("status");
                String email = list_item.getString("email");
                int competition_id = list_item.getInt("competition_id");
                return new UserOBJ(user_name, display_name, role_id, status, email, competition_id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new UserOBJ();
    }

    //    参赛单位：参选单位
    public static ArrayList<CompanyOBJ> convert_Company_List(String json_string) {
        ArrayList<CompanyOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                String company_name = list_item.getString("company_name");
                CompanyOBJ companyOBJ = new CompanyOBJ(company_name);
                list.add(companyOBJ);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    大赛管理：年份-备注-雄蟹肥满度参数-雄蟹体重参数-雄蟹肥满度标准差参数-雄蟹体重标准差参数-雄蟹肥满度参数
    //    -雌蟹体重参数-雌蟹肥满度标准差参数-雌蟹体重标准差参数-肥满度及蟹王蟹后结果-种质评比排名-口感评比排名
    public static CompetitionOBJ convert_CompetitionOBJ(String json_string) {
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                String competition_year = list_item.getString("competition_year");
                String note = list_item.getString("note");
                double var_fatness_m = list_item.getDouble("var_fatness_m");
                double var_weight_m = list_item.getDouble("var_weight_m");
                double var_mfatness_sd = list_item.getDouble("var_mfatness_sd");
                double var_mweight_sd = list_item.getDouble("var_mweight_sd");
                double var_fatness_f = list_item.getDouble("var_fatness_f");
                double var_weight_f = list_item.getDouble("var_weight_f");
                double var_ffatness_sd = list_item.getDouble("var_ffatness_sd");
                double var_fweight_sd = list_item.getDouble("var_fweight_sd");
                int result_fatness = list_item.getInt("result_fatness");
                int result_quality = list_item.getInt("result_quality");
                int result_taste = list_item.getInt("result_taste");
                return new CompetitionOBJ(competition_year,
                        note, var_fatness_m, var_weight_m, var_mfatness_sd, var_mweight_sd, var_fatness_f,
                        var_weight_f, var_ffatness_sd, var_fweight_sd, result_fatness, result_quality, result_taste);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new CompetitionOBJ();
    }

    //    小组号列表
    public static ArrayList<GroupOBJ> convert_Group_List(String json_string) {
        ArrayList<GroupOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                int group_id = list_item.getInt("group_id");
                GroupOBJ groupOBJ = new GroupOBJ(group_id);
                list.add(groupOBJ);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    优质奖：小组号-参选单位号-比赛号-雄蟹肥满度评分-雌蟹肥满度评分
    public static ArrayList<GroupOBJ> convert_fatness_score(String json_string) {
        ArrayList<GroupOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                int group_id = list_item.getInt("group_id");
                int company_id = list_item.getInt("company_id");
                double fatness_score_m = list_item.getDouble("fatness_score_m");
                double fatness_score_f = list_item.getDouble("fatness_score_f");
                GroupOBJ groupOBJ = new GroupOBJ(group_id, company_id, fatness_score_m, fatness_score_f);
                list.add(groupOBJ);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    优质奖小分：小组号-性别-最终得分-体色(背)-体色(腹)-额齿-第4侧齿-背部疣状突-比赛ID
    public static QualityScoreOBJ convert_QualityScoreOBJ(String json_string) {
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                int group_id = list_item.getInt("group_id");
                int crab_sex = list_item.getInt("crab_sex");
                double score_fin = list_item.getDouble("score_fin");
                double score_bts = list_item.getDouble("score_bts");
                double score_fts = list_item.getDouble("score_fts");
                double score_ec = list_item.getDouble("score_ec");
                double score_dscc = list_item.getDouble("score_dscc");
                double score_bbyzt = list_item.getDouble("score_bbyzt");
                int competition_id = list_item.getInt("competition_id");
                return new QualityScoreOBJ(group_id, crab_sex, score_fin, score_bts, score_fts, score_ec, score_dscc, score_bbyzt, competition_id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new QualityScoreOBJ();
    }

    //    口感奖小分：小组号-性别-最终得分-蟹盖颜色-鳃颜色-膏、黄颜色-腥味、香味-膏、黄-腹部肌肉-第二、三步足肌肉-比赛ID
    public static TasteScoreOBJ convert_TasteScoreOBJ(String json_string) {
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                int group_id = list_item.getInt("group_id");
                int crab_sex = list_item.getInt("crab_sex");
                double score_fin = list_item.getDouble("score_fin");
                double score_ygys = list_item.getDouble("score_ygys");
                double score_sys = list_item.getDouble("score_sys");
                double score_ghys = list_item.getDouble("score_ghys");
                double score_xwxw = list_item.getDouble("score_xwxw");
                double score_gh = list_item.getDouble("score_gh");
                double score_fbjr = list_item.getDouble("score_fbjr");
                double score_bzjr = list_item.getDouble("score_bzjr");
                int competition_id = list_item.getInt("competition_id");
                return new TasteScoreOBJ(group_id, crab_sex, score_fin, score_ygys, score_sys, score_ghys, score_xwxw, score_gh, score_fbjr, score_bzjr, competition_id);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new TasteScoreOBJ();
    }

    //    螃蟹：螃蟹id-小组号-性别-标签-质量-长度-肥满度-比赛ID
    public static ArrayList<CrabOBJ> convert_Crab_List(String json_string) {
        ArrayList<CrabOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json_string);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                int crab_id = list_item.getInt("crab_id");
                int group_id = list_item.getInt("group_id");
                int crab_sex = list_item.getInt("crab_sex");
                String crab_label = list_item.getString("crab_label");
                double crab_weight = list_item.getDouble("crab_weight");
                double crab_length = list_item.getDouble("crab_length");
                double crab_fatness = list_item.getDouble("crab_fatness");
                int competition_id = list_item.getInt("competition_id");
                list.add(new CrabOBJ(crab_id, group_id, crab_sex, crab_label, crab_weight, crab_length, crab_fatness, competition_id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
