package spencercjh.crabscore.HttpRequst.Function.StaffPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.StaffPart.QueryCrabLabelExist;
import spencercjh.crabscore.OBJ.CrabOBJ;

/**
 * Created by spencercjh on 2018/2/9.
 * iClass
 */

public class Fun_QueryCrabLabelExist {
    public static boolean http_QueryCrabLabelExist(CrabOBJ crabOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + ServerURL.servlet_QueryCrabLabelExist;
        QueryCrabLabelExist thread = new QueryCrabLabelExist(url, crabOBJ.getCrab_label());
        thread.start();
        thread.join();
        return thread.getFlag();
    }
}
