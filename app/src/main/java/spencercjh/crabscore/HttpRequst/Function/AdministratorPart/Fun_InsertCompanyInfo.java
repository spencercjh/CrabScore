package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.InsertCompanyInfo;
import spencercjh.crabscore.OBJ.CompanyOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_InsertCompanyInfo {
    public static boolean http_InsertUnitInfo(CompanyOBJ companyOBJ,UserOBJ userOBJ) throws InterruptedException {
        String url= ServerURL.sever_url+ServerURL.servlet_InsertCompanyInfo;
        InsertCompanyInfo thread=new InsertCompanyInfo(url,companyOBJ.getCompany_name(),userOBJ.getUser_name(),companyOBJ.getCompetition_id());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
