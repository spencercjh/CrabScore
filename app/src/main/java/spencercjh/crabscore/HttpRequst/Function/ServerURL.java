package spencercjh.crabscore.HttpRequst.Function;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class ServerURL {
    public static String sign_success = "100";
    public static String sign_fail = "200";
    public static String servlet_Login = "Login";
    public static String servlet_Register = "Register";
    public static String servlet_QueryBestGermplasmScore = "QueryBestGermplasmScore";
    public static String servlet_QueryHighQualityScore = "QueryHighQualityScore";
    public static String servlet_QueryTasteScore = "QueryTasteScore";
    public static String servlet_InsertUnitInfo = "InsertUnitInfo";
    public static String servlet_QueryUserProperty = "QueryUserProperty";
    public static String servlet_QueryAllCompetition = "QueryAllCompetition";
    public static String servlet_QueryAllUncheckedUser = "QueryAllUncheckedUser";
    public static String servlet_QueryAllUnit = "QueryAllUnit";
    public static String servlet_QueryAllUser = "QueryAllUser";
    public static String servlet_QueryCompetitionProperty = "QueryCompetitionProperty";
    public static String servlet_UpdateUserProperty = "UpdateUserProperty";
    public static String servlet_UpdateUserStatus = "UpdateUserStatus";
    private static String sever_ip = "192.168.31.189";
    private static String port = "8080";
    public static String sever_url = "http://" + sever_ip + ":'" + port + "/";
}
