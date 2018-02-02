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
 * 用户组4 参选单位 unit
 */
public class UserOBJ implements Serializable {
    private String user_id; //***没用***
    private String user_name;   //真正的用户名
    private String password;    //密码
    private String display_name;    //姓名
    private int role_id;    //用户组
    private int status; //账号可用状态
    private String email;   //邮箱

    public UserOBJ() {

    }

    //注册用
    public UserOBJ(String user_id, String user_name, String password, String display_name, int role_id, String email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.display_name = display_name;
        this.role_id = role_id;
        this.email = email;
    }

    //登陆用
    public UserOBJ(String user_name, String password, int role_id) {
        this.user_name = user_name;
        this.password = password;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
