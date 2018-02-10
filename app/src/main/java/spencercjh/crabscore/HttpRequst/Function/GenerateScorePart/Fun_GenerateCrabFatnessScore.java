package spencercjh.crabscore.HttpRequst.Function.GenerateScorePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.GenerateScorePart.GenerateCrabFatnessScore;

/**
 * Created by spencercjh on 2018/2/10.
 * iClass
 */

public class Fun_GenerateCrabFatnessScore {
    public static boolean http_GenerateCrabFatnessScore(float var_weight, int group_id, float var_fatness_sd, int crab_sex, int competition_id, String update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_GenerateCrabFatnessScore;
        GenerateCrabFatnessScore thread = new GenerateCrabFatnessScore(url, group_id, var_weight, var_fatness_sd, crab_sex, competition_id, update_user);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
