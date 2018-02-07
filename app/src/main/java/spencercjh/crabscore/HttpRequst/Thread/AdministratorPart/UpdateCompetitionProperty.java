package spencercjh.crabscore.HttpRequst.Thread.AdministratorPart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import spencercjh.crabscore.OBJ.CompetitionOBJ;

/**
 * Created by spencercjh on 2018/2/6.
 * iClass
 */

public class UpdateCompetitionProperty extends Thread {
    private boolean flag;
    private String url;
    private CompetitionOBJ competitionOBJ;
    private String update_state;
    private String update_user;

    public UpdateCompetitionProperty(String url, String update_user, CompetitionOBJ competitionOBJ) {
        // TODO Auto-generated constructor stub
        this.url = url;
        this.competitionOBJ = competitionOBJ;
        this.update_user = update_user;
    }

    private void doGet() throws IOException {
        url = url + "?update_user=" + update_user +
                "&competition_year=" + competitionOBJ.getCompetition_year() +
                "&var_fatness_m=" + competitionOBJ.getVar_fatness_m() +
                "&var_weight_m=" + competitionOBJ.getVar_weight_m() +
                "&var_mfatness_sd=" + competitionOBJ.getVar_mfatness_sd() +
                "&var_mweight_sd=" + competitionOBJ.getVar_mweight_sd() +
                "&var_fatness_f=" + competitionOBJ.getVar_fatness_f() +
                "&var_weight_f=" + competitionOBJ.getVar_weight_f() +
                "&var_ffatness_sd=" + competitionOBJ.getVar_ffatness_sd() +
                "&var_fweight_sd=" + competitionOBJ.getVar_fweight_sd() +
                "&result_fatness=" + competitionOBJ.getResult_fatness() +
                "&result_quality=" + competitionOBJ.getResult_quality() +
                "&result_taste=" + competitionOBJ.getResult_taste() +
                "&note=" + competitionOBJ.getNote() +
                "&competition_id=" + competitionOBJ.getCompetition_id();
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
                update_state = buffer.toString();
                update_state = URLDecoder.decode(update_state, "UTF-8");
            }
            System.out.println("result:" + update_state);
            if (update_state.equals("update competition property failed")) {
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

    public String getUpdate_state() {
        return update_state;
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
