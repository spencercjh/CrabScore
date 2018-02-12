package spencercjh.crabscore.HttpRequst.Thread.JudgePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/12.
 * iClass
 */

public class InsertQualityScoreInfo extends Thread {
    private boolean flag;
    private String url;
    private UserOBJ create_user;
    private String insert_status;
    private QualityScoreOBJ qualityScoreOBJ = new QualityScoreOBJ();

    public InsertQualityScoreInfo(String url, UserOBJ create_user, QualityScoreOBJ qualityScoreOBJ) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.create_user = create_user;
        this.qualityScoreOBJ = qualityScoreOBJ;
    }

    private void doGet() throws IOException {
        url = url + "?group_id=" + qualityScoreOBJ.getGroup_id() +
                "&competition_id=" + qualityScoreOBJ.getCompetition_id() +
                "&crab_sex=" + qualityScoreOBJ.getCrab_sex() +
                "&user_id=" + create_user.getUser_id() +
                "&score_fin=" + qualityScoreOBJ.getScore_fin() +
                "&score_bts=" + qualityScoreOBJ.getScore_bts() +
                "&score_fts=" + qualityScoreOBJ.getScore_fts() +
                "&score_ec=" + qualityScoreOBJ.getScore_ec() +
                "&score_dscc=" + qualityScoreOBJ.getScore_dscc() +
                "&score_bbyzt=" + qualityScoreOBJ.getScore_bbyzt() +
                "&create_user=" + create_user.getUser_name();
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(2000);
            conn.setConnectTimeout(2000);
            conn.connect();
            int code = conn.getResponseCode();
            if (code == 200) {
                InputStream inStream = conn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuffer buffer = new StringBuffer();
                String line;
                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }
                insert_status = buffer.toString();
                insert_status = URLDecoder.decode(insert_status, "UTF-8");
            }
            System.out.println("result:" + insert_status);
            if (insert_status.equals("insert quality score failed")) {
                setFlag(false);
            } else {
                setFlag(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean getFlag() {
        return flag;
    }

    private void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            doGet();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
