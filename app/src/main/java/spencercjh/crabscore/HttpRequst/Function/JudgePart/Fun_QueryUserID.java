package spencercjh.crabscore.HttpRequst.Function.JudgePart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.JudgePart.QueryUserID;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/12.
 * iClass
 */

public class Fun_QueryUserID {
    public static int http_QueryUserID(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryUserID";
        QueryUserID thread = new QueryUserID(url, userOBJ.getUser_name());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            int user_id;
            try {
                user_id = Integer.parseInt(thread.getUser_id());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                user_id = -1;
            }
            return user_id;
        } else {
            return -1;
        }
    }
}
