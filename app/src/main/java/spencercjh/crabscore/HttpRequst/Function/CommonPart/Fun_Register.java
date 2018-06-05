package spencercjh.crabscore.HttpRequst.Function.CommonPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CommonPart.Register;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_Register {
    public static boolean http_Register(UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "Register";
        Register thread = new Register(url, userOBJ.getUser_name(), userOBJ.getPassword(), userOBJ.getDisplay_name(), userOBJ.getEmail(), userOBJ.getRole_id());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
