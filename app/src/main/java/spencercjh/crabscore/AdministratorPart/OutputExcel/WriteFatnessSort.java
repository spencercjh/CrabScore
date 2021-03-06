package spencercjh.crabscore.AdministratorPart.OutputExcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import spencercjh.crabscore.HttpRequst.Function.CheckScore_Ranking_Part.Fun_QueryCompanyName;
import spencercjh.crabscore.OBJ.GroupOBJ;

/**
 * Created by spencercjh on 2018/1/2.
 * iClass
 */

@SuppressWarnings({"deprecation", "ConstantConditions", "ResultOfMethodCallIgnored"})
class WriteFatnessSort {

    static boolean writeExcel(ArrayList<GroupOBJ> AllGroup, WritableWorkbook wwb, WritableSheet sheet) throws Exception {
        Collections.sort(AllGroup, new Comparator<GroupOBJ>() {
            @Override
            public int compare(GroupOBJ g1, GroupOBJ g2) {
                float fatness_score_g1 = ((g1.getFatness_score_m() + g1.getFatness_score_f()) / (float) 2.0);
                float fatness_score_g2 = (g2.getFatness_score_m() + g2.getFatness_score_m() / (float) 2.0);
                if (fatness_score_g1 > fatness_score_g2) {
                    return 1;
                } else if (fatness_score_g1 == fatness_score_g2) {
                    return 0;
                } else if (fatness_score_g1 < fatness_score_g2) {
                    return -1;
                }
                return 0;
            }
        });
        String[] title = {"组号", "单位名称", "肥满度分", "排名"};
        Label label = new Label(0, 0, title[0], setBackGround_DarkPurple());
        sheet.addCell(label);
        label = new Label(0, 1, title[1], setBackGround_DarkPurple());
        sheet.addCell(label);
        label = new Label(0, 2, title[2], setBackGround_DarkPurple());
        sheet.addCell(label);
        label = new Label(0, 3, title[3], setBackGround_LightGreen());
        sheet.addCell(label);
        for (int i = 0; i < AllGroup.size(); i++) {
            GroupOBJ groupOBJ = AllGroup.get(i);
            label = new Label(0, i + 1, "第" + groupOBJ.getGroup_id() + "组", setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(1, i + 1, Fun_QueryCompanyName.http_QueryCompanyName(groupOBJ.getCompany_id()));
            sheet.addCell(label);
            label = new Label(2, i + 1, String.valueOf((groupOBJ.getFatness_score_f() + groupOBJ.getFatness_score_m()) / (float) 2.0));
            sheet.addCell(label);
            label = new Label(3, i + 1, String.valueOf(i + 1),setBackGround_LightOrange());
            sheet.addCell(label);
        }
        wwb.write();
        wwb.close();
        return true;
    }

    private static WritableCellFormat setBackGround_DarkPurple() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 15,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLACK);// 黑色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBackground(Colour.DARK_PURPLE);// 深紫色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    private static WritableCellFormat setBackGround_LightOrange() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 15,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLACK);// 黑色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBackground(Colour.LIGHT_ORANGE);// 浅橙色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    private static WritableCellFormat setBackGround_LightGreen() {
        WritableFont font = new WritableFont(WritableFont.TIMES, 15,
                WritableFont.BOLD);// 定义字体
        try {
            font.setColour(Colour.BLACK);// 黑色字体
        } catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
            format.setBackground(Colour.LIGHT_GREEN);// 浅绿色背景
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

}
