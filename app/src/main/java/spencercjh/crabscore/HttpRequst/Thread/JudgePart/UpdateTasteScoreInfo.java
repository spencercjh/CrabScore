package spencercjh.crabscore.HttpRequst.Thread.JudgePart;

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

public class UpdateTasteScoreInfo extends Thread {
    private boolean flag;
    private String url;
    private int group_id;
    private int competition_id;
    private int crab_sex;
    private String user_name;
    private String update_status;

    public UpdateTasteScoreInfo(String url, int group_id, int competition_id, int crab_sex, String user_name) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.user_name = user_name;
        this.group_id = group_id;
        this.competition_id = competition_id;
        this.crab_sex = crab_sex;
    }

    private void doGet() throws IOException {
        url = url + "?group_id=" + group_id + "&competition_id=" + competition_id + "&crab_sex=" + crab_sex + "&user_name=" + user_name;
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
                update_status = buffer.toString();
                update_status = URLDecoder.decode(update_status, "UTF-8");
            }
            //把服务端返回的数据打印出来
            System.out.println("result:" + update_status);
            if (update_status.equals("get student subject list failed")) {
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

    public String getUpdate_status() {
        return update_status;
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

