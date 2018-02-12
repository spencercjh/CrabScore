package spencercjh.crabscore.HttpRequst.Function.CommonPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CommonPart.QueryUserStatus;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryUserStatus {
    public static int http_QueryUserStatus(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryUserStatus";
        QueryUserStatus thread = new QueryUserStatus(url, userOBJ.getUser_name());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            if (thread.getUser_status().contains("1")) {
                return 1;   //账户开启 能登陆
            } else {
                return 0;   //账户被禁用
            }
        } else {
            return -1;  //与服务器连接不正常
        }
    }
}
