package spencercjh.crabscore.HttpRequst.Function.GenerateScorePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.GenerateScorePart.GenerateCrabTasteScore;

/**
 * Created by spencercjh on 2018/2/10.
 * iClass
 */

public class Fun_GenerateCrabTasteScore {
    public static boolean http_GenerateCrabTasteScore(int crab_sex, int group_id, int competition_id, String update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_GenerateCrabTasteScore;
        GenerateCrabTasteScore thread = new GenerateCrabTasteScore(url, competition_id, group_id, crab_sex, update_user);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
