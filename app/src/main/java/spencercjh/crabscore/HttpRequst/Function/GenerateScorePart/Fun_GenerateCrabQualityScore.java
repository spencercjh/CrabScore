package spencercjh.crabscore.HttpRequst.Function.GenerateScorePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.GenerateScorePart.GenerateCrabQualityScore;

/**
 * Created by spencercjh on 2018/2/10.
 * iClass
 */

public class Fun_GenerateCrabQualityScore {
    public static boolean http_GenerateCrabQualityScore(int crab_sex, int group_id, int competition_id, String update_user) throws InterruptedException {
        String url = ServerURL.sever_url + "GenerateCrabQualityScore";
        GenerateCrabQualityScore thread = new GenerateCrabQualityScore(url, competition_id, group_id, crab_sex, update_user);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
