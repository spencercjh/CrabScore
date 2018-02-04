package spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.CheckScore_Ranking_Part.QueryFatnessScore;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class Fun_QueryFatnessScore {
    public static String http_QueryHighQualityScore() throws InterruptedException {
        String url= ServerURL.sever_url+ServerURL.servlet_QueryFatnessScore;
        QueryFatnessScore thread=new QueryFatnessScore(url);
        thread.start();
        thread.join();
        if(thread.getFlag()){
            return thread.getJsonstr();
        }else{
            return ServerURL.sign_fail;
        }
    }
}
