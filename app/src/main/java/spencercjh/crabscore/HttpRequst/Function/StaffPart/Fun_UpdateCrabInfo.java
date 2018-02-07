package spencercjh.crabscore.HttpRequst.Function.StaffPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.StaffPart.UpdateCrabInfo;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_UpdateCrabInfo {
    public static boolean http_UpdateCrabInfo(CrabOBJ crabOBJ, UserOBJ update_user) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateCrabInfo;
        UpdateCrabInfo thread = new UpdateCrabInfo(url, crabOBJ.getCrab_id(), crabOBJ.getCrab_label(), crabOBJ.getCrab_weight(), crabOBJ.getCrab_length(), update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
