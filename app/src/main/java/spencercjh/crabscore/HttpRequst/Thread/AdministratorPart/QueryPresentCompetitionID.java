package spencercjh.crabscore.HttpRequst.Thread.AdministratorPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class QueryPresentCompetitionID extends Thread{
    private boolean flag;
    private String url;
    private String  competition_id;

    public QueryPresentCompetitionID(String url) {
        // TODO Auto-generated constructor stub
        this.url = url;
    }

    private void doGet() throws IOException {
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
                competition_id = buffer.toString();
                competition_id = URLDecoder.decode(competition_id, "UTF-8");
            }
            System.out.println("result:" + competition_id);
            if (competition_id.equals("query competition id failed")) {
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
