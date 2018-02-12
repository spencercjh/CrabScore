package spencercjh.crabscore.HttpRequst.Function.CompanyPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CompanyPart.QueryQualityScoreInfo;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class Fun_QueryQualityScoreInfo {
    public static String http_QueryQualityScoreInfo(QualityScoreOBJ qualityScoreOBJ) throws InterruptedException {
        String url = ServerURL.sever_url + "QueryQualityScoreInfo";
        QueryQualityScoreInfo thread = new QueryQualityScoreInfo(url, qualityScoreOBJ.getGroup_id(), qualityScoreOBJ.getCompetition_id(), qualityScoreOBJ.getCrab_sex());
        thread.start();
        thread.join();
        if (thread.getFlag()) {
            return thread.getJsonstr();
        } else {
            return ServerURL.sign_fail;
        }
    }
}
