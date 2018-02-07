package spencercjh.crabscore.HttpRequst.Thread.AdministratorPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class UpdateCompanyName extends Thread {
    private boolean flag;
    private String url;
    private String old_name;
    private String update_state;
    private String update_user;
    private String new_name;

    public UpdateCompanyName(String url, String update_user, String old_name, String new_name) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.old_name = old_name;
        this.update_user = update_user;
        this.new_name = new_name;
    }

    private void doGet() throws IOException {
        old_name = URLEncoder.encode(old_name, "utf-8");
        old_name = URLEncoder.encode(old_name, "utf-8");
        new_name = URLEncoder.encode(new_name, "utf-8");
        new_name = URLEncoder.encode(new_name, "utf-8");
        url = url + "?update_user=" + update_user + "&old_name=" + old_name + "&new_name" + new_name;
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
            if (update_state.equals("update company name failed")) {
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
