package spencercjh.crabscore.HttpRequst.Thread.CompanyPart;

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

public class QueryQualityScoreInfo extends Thread {
    private boolean flag;
    private String url;
    private int group_id;
    private int competition_id;
    private int crab_sex;
    private String jsonstr;

    public QueryQualityScoreInfo(String url, int group_id, int competition_id, int crab_sex) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.group_id = group_id;
        this.competition_id = competition_id;
        this.crab_sex = crab_sex;
    }

    private void doGet() throws IOException {
        url = url + "?group_id=" + group_id + "&competition_id=" + competition_id + "&crab_sex=" + crab_sex;
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
                jsonstr = buffer.toString();
                jsonstr = URLDecoder.decode(jsonstr, "UTF-8");
            }
            System.out.println("result:" + jsonstr);
            if (jsonstr.equals("query quality score failed")) {
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

