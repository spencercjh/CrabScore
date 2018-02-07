package spencercjh.crabscore.HttpRequst.Function.CompanyPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CompanyPart.QueryAllGroup;
import spencercjh.crabscore.OBJ.CompanyOBJ;

/**
 * Created by spencercjh on 2018/2/7.
 * iClass
 */

public class Fun_QueryAllGroup {
    public static String http_QueryAllGroup(CompanyOBJ companyOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryAllGroup;
        QueryAllGroup thread = new QueryAllGroup(url, companyOBJ.getCompany_id(), companyOBJ.getCompetition_id());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
