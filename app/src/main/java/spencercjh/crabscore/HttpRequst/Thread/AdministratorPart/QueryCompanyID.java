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

public class QueryCompanyID extends Thread {
    private boolean flag;
    private String url;
    private String competition_id;
    private String company_name;

    public QueryCompanyID(String url, String company_name) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.company_name = company_name;
    }

    private void doGet() throws IOException {
        company_name = URLEncoder.encode(company_name, "utf-8");
        company_name = URLEncoder.encode(company_name, "utf-8");
        url=url+"?company_name="+company_name;
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
                competition_id = buffer.toString();
                competition_id = URLDecoder.decode(competition_id, "UTF-8");
            }
            //把服务端返回的数据打印出来
            System.out.println("result:" + competition_id);
            if (competition_id.equals("get student subject list failed")) {
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

    public String getCompetition_id() {
        return competition_id;
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