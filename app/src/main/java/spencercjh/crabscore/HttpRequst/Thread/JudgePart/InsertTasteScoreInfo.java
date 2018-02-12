package spencercjh.crabscore.HttpRequst.Thread.JudgePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import spencercjh.crabscore.OBJ.TasteScoreOBJ;

/**
 * Created by spencercjh on 2018/2/12.
 * iClass
 */

public class InsertTasteScoreInfo extends Thread {
    private boolean flag;
    private String url;
    private String create_user;
    private String insert_status;
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();

    public InsertTasteScoreInfo(String url, String create_user, TasteScoreOBJ tasteScoreOBJ) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.create_user = create_user;
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    private void doGet() throws IOException {
        url = url + "?group_id=" + tasteScoreOBJ.getGroup_id() +
                "&competition_id=" + tasteScoreOBJ.getCompetition_id() +
                "&crab_sex=" + tasteScoreOBJ.getCrab_sex() +
                "&user_id=" + create_user +
                "&score_fin=" + tasteScoreOBJ.getScore_fin() +
                "&score_ygys=" + tasteScoreOBJ.getScore_ygys() +
                "&score_sys=" + tasteScoreOBJ.getScore_sys() +
                "&score_ghys=" + tasteScoreOBJ.getScore_ghys() +
                "&score_xwxw=" + tasteScoreOBJ.getScore_xwxw() +
                "&score_gh=" + tasteScoreOBJ.getScore_gh() +
                "&score_fbjr=" + tasteScoreOBJ.getScore_fbjr() +
                "&score_bzjr=" + tasteScoreOBJ.getScore_bzjr() +
                "&create_user=" + create_user;
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
            if (insert_status.equals("insert taste score failed")) {
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