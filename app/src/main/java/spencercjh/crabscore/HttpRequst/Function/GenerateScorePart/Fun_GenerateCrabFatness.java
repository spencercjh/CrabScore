package spencercjh.crabscore.HttpRequst.Function.GenerateScorePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.GenerateScorePart.GenerateCrabFatness;

/**
 * Created by spencercjh on 2018/2/10.
 * iClass
 */

public class Fun_GenerateCrabFatness {
    public static boolean http_GenerateCrabFatness(float var_fatness, int crab_sex, int competition_id, String update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "GenerateCrabFatness";
        GenerateCrabFatness thread = new GenerateCrabFatness(url, var_fatness, crab_sex, competition_id, update_user);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
