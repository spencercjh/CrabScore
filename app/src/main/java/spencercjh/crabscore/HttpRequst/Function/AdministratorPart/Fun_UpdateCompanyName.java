package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.UpdateCompanyName;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_UpdateCompanyName {
    public static boolean http_UpdateCompanyName(CompanyOBJ companyOBJ, UserOBJ userOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_UpdateCompanyName;
        UpdateCompanyName thread = new UpdateCompanyName(url, userOBJ.getUser_name(), companyOBJ.getCompany_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
