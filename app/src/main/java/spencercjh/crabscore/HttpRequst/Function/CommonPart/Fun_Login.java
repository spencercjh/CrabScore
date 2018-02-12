package spencercjh.crabscore.HttpRequst.Function.CommonPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CommonPart.Login;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 * 登陆
 */

public class Fun_Login {
    public static int http_login(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "Login";
        Login thread = new Login(url, userOBJ.getUser_name(), userOBJ.getPassword(), userOBJ.getRole_id());
        thread.start();
        thread.join();
        if (!thread.getFlag()) {
            return -1;  //servlet error
        } else {
            if (thread.getLogin_state().contains(ServerURL.sign_success)) {
                return 1;   //login success
            } else if (thread.getLogin_state().contains(ServerURL.sign_fail)) {
                return 0;   //login failed
            }
        }
        return -1;
    }
}
