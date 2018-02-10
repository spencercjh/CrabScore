package spencercjh.crabscore.HttpRequst.Function.GenerateScorePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.GenerateScorePart.GenerateCrabFatnessScore;

/**
 * Created by spencercjh on 2018/2/10.
 * iClass
 */

public class Fun_GenerateCrabFatnessScore {
    public static boolean http_GenerateCrabFatnessScore(float var_weight, float var_fatness_sd, int crab_sex, String update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_GenerateCrabFatnessScore;
        GenerateCrabFatnessScore thread = new GenerateCrabFatnessScore(url, var_weight, var_fatness_sd, crab_sex, update_user);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
