package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryCompanyID;
import spencercjh.crabscore.OBJ.CompanyOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_QueryCompanyID {
    public static int http_QueryCompanyID(CompanyOBJ companyOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryCompanyID;
        QueryCompanyID thread = new QueryCompanyID(url, companyOBJ.getCompany_name());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            int company_id;
            try {
                company_id = Integer.parseInt(thread.getCompetition_id());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                company_id = -1;
            }
            return company_id;
        } else {
            return -1;
        }
    }
}
