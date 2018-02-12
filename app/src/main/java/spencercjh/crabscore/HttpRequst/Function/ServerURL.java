package spencercjh.crabscore.HttpRequst.Function;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class ServerURL {
    public static String sign_success = "100";
    public static String sign_fail = "200";
    private static String sever_ip = "192.168.31.189";
    private static String port = "80";
    private static String server_name = "CrabScore_Sever";
    public static String sever_url = "http://" + sever_ip + ":" + port + "/" + server_name + "/";
}
