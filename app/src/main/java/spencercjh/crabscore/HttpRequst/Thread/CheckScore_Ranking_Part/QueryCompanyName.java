package spencercjh.crabscore.HttpRequst.Thread.CheckScore_Ranking_Part;

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

public class QueryCompanyName extends Thread {
    private boolean flag;
    private String url;
    private int company_id;
    private String company_name;

    public QueryCompanyName(String url, int company_id) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.company_id = company_id;
    }

    private void doGet() throws IOException {
        url = url + "?company_id=" + company_id;
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
                company_name = buffer.toString();
                company_name = URLDecoder.decode(company_name, "UTF-8");
            }
            System.out.println("result:" + company_name);
            if (company_name.equals("query company name failed")) {
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

    public String getCompanyName() {
        return company_name;
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

