package spencercjh.crabscore.HttpRequst.Thread.CommonPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2018/2/2.
 * iClass
 */

public class Login extends Thread{
    private boolean flag;
    private String url;
    private String user_name;
    private String password;
    private int choice;
    private String login_state;

    public Login(String url, String user_name, String password,int choice) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
        this.password = password;
        this.choice=choice;
    }

    private void doGet() throws IOException {
        /*将username和password传给Tomcat服务器*/
        url = url + "?user_name=" + user_name + "&password=" + password+"&role_id="+choice;
        try {
            URL httpUrl = new URL(url);
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
                login_state = buffer.toString();
                login_state = URLDecoder.decode(login_state, "UTF-8");
            }
            //把服务端返回的数据打印出来
            System.out.println("result:" + login_state);
            if (login_state.equals("student login failed")) {
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

    public String getLogin_state() {
        return login_state;
    }

    private void setLogin_state(String login_state) {
        this.login_state = login_state;
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