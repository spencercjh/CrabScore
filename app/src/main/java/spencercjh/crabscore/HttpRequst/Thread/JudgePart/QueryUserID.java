package spencercjh.crabscore.HttpRequst.Thread.JudgePart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2018/2/12.
 * iClass
 */

public class QueryUserID extends Thread{
    private boolean flag;
    private String url;
    private String user_name;
    private String user_id;

    public QueryUserID(String url, String user_name) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
    }

    private void doGet() throws IOException {
        url = url + "?user_name=" + user_name;
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
                user_id = buffer.toString();
                user_id = URLDecoder.decode(user_id, "UTF-8");
            }
            System.out.println("result:" + user_id);
            if (user_id.equals("query user id failed")) {
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

    public String getUser_id() {
        return user_id;
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
