package spencercjh.crabscore.HttpRequst.Function.StaffPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.StaffPart.QueryAllCrab;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_QueryAllCrab {
    public static String http_QueryAllCrab(int competition_id, int group_id, int crab_sex) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryAllCrab;
        QueryAllCrab thread = new QueryAllCrab(url, competition_id, group_id, crab_sex);
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
