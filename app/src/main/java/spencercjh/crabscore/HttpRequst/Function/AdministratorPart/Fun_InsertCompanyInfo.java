package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.InsertCompanyInfo;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 * 这里的userOBJ是参赛单位
 */

public class Fun_InsertCompanyInfo {
    public static boolean http_InsertUnitInfo(UserOBJ userOBJ) throws InterruptedException {
        String url= ServerURL.sever_url+ServerURL.servlet_InsertCompanyInfo;
        InsertCompanyInfo thread=new InsertCompanyInfo(url,userOBJ.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
