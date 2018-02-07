package spencercjh.crabscore.HttpRequst.Thread.PersonCenterPart;

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

public class UpdateUserDisplayName extends Thread {
    private boolean flag;
    private String url;
    private String user_name;
    private String password;
    private String display_name;
    private String update_state;
    private String update_user;

    public UpdateUserDisplayName(String url, String user_name, String password, String display_name, String update_user) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
        this.password = password;
        this.display_name = display_name;
        this.update_user = update_user;
    }

    private void doGet() throws IOException {
        url = url + "?user_name=" + user_name + "&password=" + password + "&display_name=" + display_name + "&update_user=" + update_user;
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
            if (update_state.equals("update user display name failed")) {
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