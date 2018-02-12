package spencercjh.crabscore.OBJ;

import java.io.Serializable;

/**
 * Created by spencercjh on 2018/2/4.
 * iClass
 */

public class CompanyOBJ implements Serializable {
    private int group_id;
    private int company_id;
    private String company_name;
    private int competition_id;
    public CompanyOBJ() {

    }

    //    管理员参选单位列表
    public CompanyOBJ(int company_id, String company_name) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    public CompanyOBJ(int company_id, String company_name, int competition_id) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.competition_id = competition_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }
}
