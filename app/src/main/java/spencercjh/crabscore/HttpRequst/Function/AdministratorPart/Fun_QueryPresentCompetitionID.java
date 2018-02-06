package spencercjh.crabscore.HttpRequst.Function.AdministratorPart;

import spencercjh.crabscore.HttpRequst.Function.ServerURL;
import spencercjh.crabscore.HttpRequst.Thread.AdministratorPart.QueryPresentCompetitionID;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class Fun_QueryPresentCompetitionID {
    public static int http_QueryPresentCompetitionID() throws InterruptedException {
        String url= ServerURL.sever_url+ServerURL.servlet_QueryPresentCompetitionID;
        QueryPresentCompetitionID thread=new QueryPresentCompetitionID(url);
        thread.start();
        thread.join();
        if(thread.getFlag()){
            int competition_id;
            try{
                competition_id=Integer.parseInt(thread.getCompetition_id());
            }catch (NumberFormatException e){
                e.printStackTrace();
                competition_id=-1;
            }
            return competition_id;
        }else {
            return -1;
        }
    }
}
