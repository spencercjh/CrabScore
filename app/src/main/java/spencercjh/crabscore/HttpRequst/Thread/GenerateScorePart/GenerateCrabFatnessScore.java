package spencercjh.crabscore.HttpRequst.Thread.GenerateScorePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2018/2/10.
 * iClass
 */

public class GenerateCrabFatnessScore extends Thread {
    private boolean flag;
    private String url;
    private float var_weight; //体重参数
    private float var_fatness_sd;  //肥满度标准差参数
    private int crab_sex;
    private String update_user;
    private String update_state;
    private int competition_id;
    private int group_id;

    public GenerateCrabFatnessScore(String url, int group_id, float var_weight, float var_fatness_sd, int crab_sex, int competition_id, String update_user) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.group_id = group_id;
        this.var_weight = var_weight;
        this.var_fatness_sd = var_fatness_sd;
        this.crab_sex = crab_sex;
        this.update_user = update_user;
        this.competition_id = competition_id;
    }

    private void doGet() throws IOException {
        url = url + "?var_weight=" + var_weight +
                "&var_fatness_sd=" + var_fatness_sd +
                "&crab_sex=" + crab_sex +
                "&group_id=" + group_id +
                "&competition_id=" + competition_id +
                "&update_user=" + update_user;
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
                update_state = buffer.toString();
                update_state = URLDecoder.decode(update_state, "UTF-8");
            }
            System.out.println("result:" + update_state);
            if (update_state.equals("update group fatness score failed")) {
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