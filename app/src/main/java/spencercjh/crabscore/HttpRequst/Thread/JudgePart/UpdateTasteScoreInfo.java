package spencercjh.crabscore.HttpRequst.Thread.JudgePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import spencercjh.crabscore.OBJ.TasteScoreOBJ;
import spencercjh.crabscore.OBJ.UserOBJ;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class UpdateTasteScoreInfo extends Thread {
    private boolean flag;
    private String url;
    private UserOBJ update_user;
    private String update_status;
    private TasteScoreOBJ tasteScoreOBJ = new TasteScoreOBJ();

    public UpdateTasteScoreInfo(String url, TasteScoreOBJ tasteScoreOBJ, UserOBJ update_user) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.update_user = update_user;
        this.tasteScoreOBJ = tasteScoreOBJ;
    }

    private void doGet() throws IOException {
        url = url + "?group_id=" + tasteScoreOBJ.getGroup_id() +
                "&competition_id=" + tasteScoreOBJ.getCompetition_id() +
                "&crab_sex=" + tasteScoreOBJ.getCrab_sex() +
                "&user_id=" + update_user.getUser_id() +
                "&score_fin=" + tasteScoreOBJ.getScore_fin() +
                "&score_ygys=" + tasteScoreOBJ.getScore_ygys() +
                "&score_sys=" + tasteScoreOBJ.getScore_sys() +
                "&score_ghys=" + tasteScoreOBJ.getScore_ghys() +
                "&score_xwxw=" + tasteScoreOBJ.getScore_xwxw() +
                "&score_gh=" + tasteScoreOBJ.getScore_gh() +
                "&score_fbjr=" + tasteScoreOBJ.getScore_fbjr() +
                "&score_bzjr=" + tasteScoreOBJ.getScore_bzjr() +
                "&update_user=" + update_user.getUser_name();
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
                update_status = buffer.toString();
                update_status = URLDecoder.decode(update_status, "UTF-8");
            }
            System.out.println("result:" + update_status);
            if (update_status.equals("update taste score failed")) {
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