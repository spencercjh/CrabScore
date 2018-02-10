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
    public static String servlet_QueryQualityScore = "QueryQualityScore";
    public static String servlet_QueryFatnessScore = "QueryFatnessScore";
    public static String servlet_QueryTasteScore = "QueryTasteScore";
    public static String servlet_InsertCompanyInfo = "InsertCompanyInfo";
    public static String servlet_QueryUserProperty_Login = "QueryUserProperty_";
    public static String servlet_QueryUserProperty_Common = "QueryUserProperty_Common";
    public static String servlet_QueryAllCompetition = "QueryAllCompetition";
    public static String servlet_QueryAllUncheckedUser = "QueryAllUncheckedUser";
    public static String servlet_QueryAllCompany = "QueryAllCompany";
    public static String servlet_QueryAllUser = "QueryAllUser";
    public static String servlet_QueryCompetitionProperty = "QueryCompetitionProperty";
    public static String servlet_UpdateUserProperty = "UpdateUserProperty";
    public static String servlet_UpdateUserStatus = "UpdateUserStatus";
    public static String servlet_QueryUserStatus = "QueryUserStatus";
    public static String servlet_UpdateUserCompetitionID = "UpdateUserCompetitionID";
    public static String servlet_QueryPresentCompetitionID = "QueryPresentCompetitionID";
    public static String servlet_DeleteCompanyInfo = "DeleteCompanyInfo";
    public static String servlet_QueryCompetitionYear = "QueryCompetitionYear";
    public static String servlet_UpdateCompetitionProperty = "UpdateCompetitionProperty";
    public static String servlet_DeleteUserInfo = "DeleteUserInfo";
    public static String servlet_QueryCompanyID = "QueryCompanyID";
    public static String servlet_QueryOneCompanyAllGroup = "QueryOneCompanyAllGroup";
    public static String servlet_UpdateCompanyName = "UpdateCompanyName";
    public static String servlet_QueryCompanyName = "QueryCompanyName";
    public static String servlet_UpdatePresentCompetition = "UpdatePresentCompetition";
    public static String servlet_UpdateCompetitionStatus = "UpdateCompetitionStatus";
    public static String servlet_UpdatePassword = "UpdatePassword";
    public static String servlet_QueryQualityScoreInfo = "QueryQualityScoreInfo";
    public static String servlet_QueryTasteScoreInfo = "QueryTasteScoreInfo";
    public static String servlet_UpdateQualityScoreInfo = "UpdateQualityScoreInfo";
    public static String servlet_UpdateTasteScoreInfo = "UpdateTasteScoreInfo";
    public static String servlet_QueryAllGroup = "QueryAllGroup";
    public static String servlet_UpdateUserPassword = "UpdateUserPassword";
    public static String servlet_UpdateUserDisplayName = "UpdateUserDisplayName";
    public static String servlet_UpdateUserEmail = "UpdateUserEmail";
    public static String servlet_QueryAllCrab = "QueryAllCrab";
    public static String servlet_InsertCrabInfo = "InsertCrabInfo";
    public static String servlet_UpdateCrabInfo = "Fun_UpdateCrabInfo";
    public static String servlet_QueryGroupID = "QueryGroupID";
    public static String servlet_QueryUserExist = "QueryUserExist";
    public static String servlet_QueryCrabLabelExist = "QueryCrabLabelExist";
    public static String servlet_GenerateCrabFatness = "GenerateCrabFatness";
    public static String servlet_GenerateCrabFatnessScore = "GenerateCrabFatnessScore";
    public static String servlet_GenerateCrabQualityScore = "GenerateCrabQualityScore";
    public static String servlet_GenerateCrabTasteScore = "GenerateCrabTasteScore";
    private static String sever_ip = "192.168.31.189";
    private static String port = "8080";
    public static String sever_url = "http://" + sever_ip + ":'" + port + "/";
}
