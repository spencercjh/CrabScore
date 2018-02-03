package spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CheckScore_Ranking_Part.QueryBestGermplasmScore;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryBestGermplasmScore {
    public static String http_QueryBestGermplasmScore() throws InterruptedException {
        String url= ServerURL.sever_url+ServerURL.servlet_QueryBestGermplasmScore;
        QueryBestGermplasmScore thread=new QueryBestGermplasmScore(url);
        thread.start();
        thread.join();
        if(thread.getFlag()){
            return thread.getJsonstr();
        }else{
            return ServerURL.sign_fail;
        }
    }
}
