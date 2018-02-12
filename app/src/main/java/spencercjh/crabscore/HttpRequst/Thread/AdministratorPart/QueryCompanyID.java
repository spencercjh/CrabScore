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
    private int competition_id;
    private String company_id;
    private String company_name;

    public QueryCompanyID(String url, String company_name, int competition_id) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.company_name = company_name;
        this.competition_id = competition_id;
    }

    private void doGet() throws IOException {
        company_name = URLEncoder.encode(company_name, "utf-8");
        company_name = URLEncoder.encode(company_name, "utf-8");
        url = url + "?company_name=" + company_name + "&competition_id=" + competition_id;
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
                company_id = buffer.toString();
                company_id = URLDecoder.decode(company_id, "UTF-8");
            }
            System.out.println("result:" + company_id);
            if (company_id.equals("query company id failed")) {
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

    public String getCompany_id() {
        return company_id;
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
