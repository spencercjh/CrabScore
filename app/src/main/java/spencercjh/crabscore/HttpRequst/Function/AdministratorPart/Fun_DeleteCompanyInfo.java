package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.DeleteCompanyInfo;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_DeleteCompanyInfo {
    public static boolean http_DeleteCompanyInfo(UserOBJ update_user, CompanyOBJ companyOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "DeleteCompanyInfo";
        DeleteCompanyInfo thread = new DeleteCompanyInfo(url, companyOBJ.getCompany_id(),update_user.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
