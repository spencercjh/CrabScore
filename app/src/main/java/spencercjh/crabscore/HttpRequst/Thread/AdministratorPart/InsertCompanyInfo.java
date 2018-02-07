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

public class InsertCompanyInfo extends Thread {
    private boolean flag;
    private String url;
    private String company_name;
    private String user_name;
    private String insert_status;

    public InsertCompanyInfo(String url, String company_name, String user_name) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.company_name = company_name;
        this.user_name = user_name;
    }

    private void doGet() throws IOException {
        company_name = URLEncoder.encode(company_name, "utf-8");
        company_name = URLEncoder.encode(company_name, "utf-8");
        url = url + "?company_name=" + company_name + "&user_name=" + user_name;
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
                insert_status = buffer.toString();
                insert_status = URLDecoder.decode(insert_status, "UTF-8");
            }
            System.out.println("result:" + insert_status);
            if (insert_status.equals("insert company failed")) {
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