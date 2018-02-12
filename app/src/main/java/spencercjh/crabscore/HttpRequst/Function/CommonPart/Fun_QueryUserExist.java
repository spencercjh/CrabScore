package spencercjh.crabscore.HttpRequst.Function.CommonPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CommonPart.QueryUserExist;

/**
 * Created by spencercjh on 2018/2/9.
 * iClass
 */

public class Fun_QueryUserExist {
    public static boolean http_QueryUserExist(String user_name) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryUserExist";
        QueryUserExist thread = new QueryUserExist(url, user_name);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
