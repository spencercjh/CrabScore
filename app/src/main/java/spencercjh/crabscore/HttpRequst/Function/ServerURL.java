package spencercjh.crabscore.HttpRequst.Function;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class ServerURL {
    public static String sign_success = "100";
    public static String sign_fail = "200";
    private static String sever_ip = "127.0.0.1";
    private static String port = "8080";
    //    private static String sever_ip = "www.spencercjh.top";
    private static String server_name = "crabscore";
    public static String sever_url = "http://" + sever_ip + ":" + port + "/" + server_name + "/";
//    public static String sever_url = "https://" + sever_ip + "/" + server_name + "/";
}
