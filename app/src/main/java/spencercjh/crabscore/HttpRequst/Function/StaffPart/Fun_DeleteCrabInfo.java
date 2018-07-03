package spencercjh.crabscore.HttpRequst.Function.StaffPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.DeleteCompanyInfo;
import spencercjh.crabscore.HttpRequst.Thread.StaffPart.DeleteCrabInfo;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_DeleteCrabInfo {
    public static boolean http_DeleteCrabInfo(int crab_id) throws InterruptedException {
        String url = ServerURL.sever_url + "DeleteCrabInfo";
        DeleteCrabInfo thread = new DeleteCrabInfo(url, crab_id);
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
