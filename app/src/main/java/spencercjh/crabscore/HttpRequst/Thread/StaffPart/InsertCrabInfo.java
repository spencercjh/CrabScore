package spencercjh.crabscore.HttpRequst.Thread.StaffPart;

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

public class InsertCrabInfo extends Thread {
    private boolean flag;
    private String url;
    private int crab_sex;
    private int group_id;
    private int competition_id;
    private String create_user;
    private String insert_status;

    public InsertCrabInfo(String url, String create_user, int crab_sex, int group_id, int competition_id) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.create_user = create_user;
        this.crab_sex = crab_sex;
        this.group_id = group_id;
        this.competition_id = competition_id;
    }

    private void doGet() throws IOException {
        url = url + "?create_user=" + create_user + "&crab_sex=" + crab_sex + "&group_id=" + group_id + "&competition_id=" + competition_id;
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
            if (insert_status.equals("insert crab failed")) {
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
