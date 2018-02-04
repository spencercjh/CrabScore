package spencercjh.crabscore.HttpRequst.Function;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import spencercjh.crabscore.OBJ.Competition_InfoOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class JsonConvert {
    //    大赛切换中的大赛列表：年份-备注
    public static ArrayList<Competition_InfoOBJ> convert_year_note(String jsonstr) {
        ArrayList<Competition_InfoOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonstr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                String competition_year = list_item.getString("competition_year");
                String note = list_item.getString("note");
                Competition_InfoOBJ competition_info_obj = new Competition_InfoOBJ(competition_year, note);
                list.add(competition_info_obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    用户：用户名-显示名-用户组-状态-email-比赛id
    public static ArrayList<UserOBJ> convert_user_name_display_name_role_id_status(String jsonstr) {
        ArrayList<UserOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonstr);
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

    //    参赛单位：参选单位
    public static ArrayList<UserOBJ> convert_unit_user_name(String jsonstr) {
        ArrayList<UserOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonstr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject list_item = jsonArray.getJSONObject(i);
                String user_name = list_item.getString("user_name");
                UserOBJ userOBJ = new UserOBJ(user_name);
                list.add(userOBJ);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    //    大赛管理：年份-备注-雄蟹肥满度参数-雄蟹体重参数-雄蟹肥满度标准差参数-雄蟹体重标准差参数-雄蟹肥满度参数
    //    -雌蟹体重参数-雌蟹肥满度标准差参数-雌蟹体重标准差参数-肥满度及蟹王蟹后结果-种质评比排名-口感评比排名
    public static ArrayList<Competition_InfoOBJ> convert_competition_property(String jsonstr) {
        ArrayList<Competition_InfoOBJ> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonstr);
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
                Competition_InfoOBJ competition_info_obj = new Competition_InfoOBJ(competition_year,
                        note, var_fatness_m, var_weight_m, var_mfatness_sd, var_mweight_sd, var_fatness_f,
                        var_weight_f, var_ffatness_sd, var_fweight_sd, result_fatness, result_quality, result_taste);
                list.add(competition_info_obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
//
}
