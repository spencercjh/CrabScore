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
    private String jsonstr;
    private String user_name;
    private String display_name;
    private String email;
    private String update_user;
    private int choice;

    public UpdateUserProperty(String url, String user_name, String display_name, String email, int choice, String update_user) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
        this.display_name = display_name;
        this.email = email;
        this.choice = choice;
        this.update_user = update_user;
    }

    private void doGet() throws IOException {
        display_name = URLEncoder.encode(display_name, "utf-8");
        display_name = URLEncoder.encode(display_name, "utf-8");
        url = url + "?user_name=" + user_name + "&display_name=" + display_name + "&email=" + email + "&role_id=" + choice + "&update_user=" + update_user;
        /*将username和password传给Tomcat服务器*/
        try {
            URL httpUrl = new URL(url);
//            URLEncoder.encode(url);
            /*获取网络连接*/
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            /*设置请求方法为GET方法*/
            conn.setRequestMethod("GET");
            /*设置访问超时时间*/
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
                jsonstr = buffer.toString();
                jsonstr = URLDecoder.decode(jsonstr, "UTF-8");
            }
            //把服务端返回的数据打印出来
            System.out.println("result:" + jsonstr);
            if (jsonstr.equals("get student subject list failed")) {
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

    public String getJsonstr() {
        return jsonstr;
    }

    /*在run中调用doGet*/
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