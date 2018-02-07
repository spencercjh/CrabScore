package spencercjh.crabscore.HttpRequst.Thread.AdministratorPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2018/2/7.
 * iClass
 */

public class UpdatePresentCompetition extends Thread {
    private boolean flag;
    private String url;
    private int old_competition_id;
    private int new_competition_id;
    private String update_state;
    private String update_user;

    public UpdatePresentCompetition(String url, int old_competition_id, String update_user, int new_competition_id) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.old_competition_id = old_competition_id;
        this.new_competition_id = new_competition_id;
        this.update_user = update_user;
    }

    private void doGet() throws IOException {
        url = url + "?old_competition_id=" + old_competition_id + "&update_user=" + update_user + "&new_competition_id=" + new_competition_id;
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
            if (update_state.equals("update present competition failed")) {
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

    public String getUpdate_state() {
        return update_state;
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

