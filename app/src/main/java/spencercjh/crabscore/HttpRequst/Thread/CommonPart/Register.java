package spencercjh.crabscore.HttpRequst.Thread.CommonPart;

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

public class Register extends Thread {
    private boolean flag;
    private String url;
    private String user_name;
    private String password;
    private String display_name;
    private int role_id;
    private String email;
    private String register_state;
    private String create_user;

    public Register(String url, String user_name, String password, String display_name, String email, int role_id) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
        this.password = password;
        this.display_name = display_name;
        this.email = email;
        this.role_id = role_id;
        this.create_user = user_name;
    }

    private void doGet() throws IOException {
        display_name = URLEncoder.encode(display_name, "utf-8");
        display_name = URLEncoder.encode(display_name, "utf-8");
        url = url + "?user_name=" + user_name +
                "&password=" + password +
                "&display_name=" + display_name +
                "&email=" + email +
                "&role_id=" + role_id +
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
                register_state = buffer.toString();
                register_state = URLDecoder.decode(register_state, "UTF-8");
            }
            System.out.println("result:" + register_state);
            if (register_state.equals("register failed")) {
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