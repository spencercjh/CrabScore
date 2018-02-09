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
 * Created by spencercjh on 2018/2/2.
 * iClass
 */

public class UpdateUserProperty extends Thread {
    private boolean flag;
    private String url;
    private String update_status;
    private String user_name;
    private String display_name;
    private String email;
    private String update_user;
    private int role_id;

    public UpdateUserProperty(String url, String user_name, String display_name, String email, int role_id, String update_user) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
        this.display_name = display_name;
        this.email = email;
        this.role_id = role_id;
        this.update_user = update_user;
    }

    private void doGet() throws IOException {
        display_name = URLEncoder.encode(display_name, "utf-8");
        display_name = URLEncoder.encode(display_name, "utf-8");
        url = url + "?user_name=" + user_name + "&display_name=" + display_name + "&email=" + email + "&role_id=" + role_id + "&update_user=" + update_user;
        /*将username和password传给Tomcat服务器*/
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
            if (update_status.equals("update user property failed")) {
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