package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.InsertUnitInfo;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 * 这里的userOBJ是参赛单位
 */

public class Fun_InsertUnitInfo {
    public static boolean http_InsertUnitInfo(UserOBJ userOBJ) throws InterruptedException {
        String url= ServerURL.sever_url+ServerURL.servlet_InsertUnitInfo;
        InsertUnitInfo thread=new InsertUnitInfo(url,userOBJ.getUser_name());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
