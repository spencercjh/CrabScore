package spencercjh.crabscore.HttpRequst.Thread.StaffPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2018/2/8.
 * iClass
 */

public class UpdateCrabInfo extends Thread {
    private boolean flag;
    private String url;
    private int crab_id;
    private String crab_label;
    private double crab_weight;
    private double crab_length;
    private String update_state;
    private String update_user;

    public UpdateCrabInfo(String url, int crab_id, String crab_label, double crab_weight, double crab_length, String update_user) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.crab_id = crab_id;
        this.crab_label = crab_label;
        this.crab_weight = crab_weight;
        this.crab_length = crab_length;
        this.update_user = update_user;
    }

    private void doGet() throws IOException {
        url = url + "?crab_id=" + crab_id +
                "&crab_weight=" + crab_weight +
                "&crab_length=" + crab_length +
                "&crab_label=" + crab_label +
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
            if (update_state.equals("update crab info failed")) {
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