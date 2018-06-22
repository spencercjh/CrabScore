package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/2.
 * CrabScore
 */

/**
 * 用户组1 管理员 administrator
 * 用户组2 工作人员 staff
 * 用户组3 评委 judge
 * 用户组4 参选单位 company
 */
public class UserOBJ implements Serializable {
    private int user_id;
    private String user_name;   //真正的用户名
    private String password;    //密码
    private String display_name;    //姓名
    private int role_id;    //用户组
    private int status; //账号可用状态
    private String email;   //邮箱
    private int competition_id; //参与的比赛id

    public UserOBJ() {

    }

    //    管理员参赛单位列表
    public UserOBJ(String user_name) {
        this.user_name = user_name;
    }

    //    管理员注册审核列表1
    public UserOBJ(String user_name, String display_name, int role_id, int status) {
        this.user_name = user_name;
        this.display_name = display_name;
        this.role_id = role_id;
        this.status = status;
    }

    //    管理员用户列表1
    public UserOBJ(String user_name, String display_name, int role_id, int status, String email, int competition_id) {
        this.user_name = user_name;
        this.display_name = display_name;
        this.role_id = role_id;
        this.status = status;
        this.email = email;
        this.competition_id = competition_id;
    }

    //    管理员用户列表2
    public UserOBJ(String user_name, String display_name, String password, int role_id, int status, String email, int competition_id) {
        this.user_name = user_name;
        this.display_name = display_name;
        this.password = password;
        this.role_id = role_id;
        this.status = status;
        this.email = email;
        this.competition_id = competition_id;
    }

    //    注册用
    public UserOBJ(String user_name, String password, String display_name, int role_id, String email) {
        this.user_name = user_name;
        this.password = password;
        this.display_name = display_name;
        this.role_id = role_id;
        this.email = email;
    }

    //    登录用
    public UserOBJ(String user_name, String password, int role_id) {
        this.user_name = user_name;
        this.password = password;
        this.role_id = role_id;
    }

    //    找回密码用
    public UserOBJ(String user_name, String display_name, String email, int role_id) {
        this.user_name = user_name;
        this.display_name = display_name;
        this.email = email;
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }
}
