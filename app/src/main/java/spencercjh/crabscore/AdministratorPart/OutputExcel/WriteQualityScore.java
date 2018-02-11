package spencercjh.crabscore.AdministratorPart.OutputExcel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;

/**
 * Created by spencercjh on 2018/1/2.
 * iClass
 */

@SuppressWarnings("deprecation")
class WriteQualityScore {

    static boolean writeExcel(ArrayList<GroupOBJ> AllGroup, ArrayList<QualityScoreOBJ> AllQualityScore, WritableWorkbook wwb, WritableSheet sheet) throws Exception {
        Collections.sort(AllGroup, new Comparator<GroupOBJ>() {
            @Override
            public int compare(GroupOBJ groupOBJ1, GroupOBJ groupOBJ2) {
                if (groupOBJ1.getGroup_id() < groupOBJ2.getGroup_id()) {
                    return 1;
                } else if (groupOBJ1.getGroup_id() == groupOBJ2.getGroup_id()) {
                    return 0;
                } else if (groupOBJ1.getGroup_id() > groupOBJ2.getGroup_id()) {
                    return -1;
                }
                return 0;
            }
        });
        ArrayList<ArrayList<QualityScoreOBJ>> Group_QualityScore_List = new ArrayList<>(AllGroup.size());
        for (int i = 0; i < AllGroup.size(); i++) {
            ArrayList<QualityScoreOBJ> one_group_quality_score = new ArrayList<>();
            GroupOBJ groupOBJ = AllGroup.get(i);
            Iterator<QualityScoreOBJ> iterator = AllQualityScore.iterator();
            while (iterator.hasNext()) {
                QualityScoreOBJ qualityScoreOBJ = iterator.next();
                if (qualityScoreOBJ.getGroup_id() == groupOBJ.getGroup_id()) {
                    one_group_quality_score.add(qualityScoreOBJ);
                    iterator.remove();
                }
            }
            Collections.sort(one_group_quality_score, new Comparator<QualityScoreOBJ>() {
                @Override
                public int compare(QualityScoreOBJ q1, QualityScoreOBJ q2) {
                    if (q1.getCrab_sex() < q2.getCrab_sex()) {
                        return 1;
                    } else if (q1.getCrab_sex() == q2.getCrab_sex()) {
                        return 0;
                    } else if (q1.getCrab_sex() > q2.getCrab_sex()) {
                        return -1;
                    }
                    return 0;
                }
            });
            Group_QualityScore_List.add(one_group_quality_score);
        }
        Collections.sort(Group_QualityScore_List, new Comparator<ArrayList<QualityScoreOBJ>>() {
            @Override
            public int compare(ArrayList<QualityScoreOBJ> list1, ArrayList<QualityScoreOBJ> list2) {
                if (list1.get(0).getGroup_id() < list2.get(0).getGroup_id()) {
                    return 1;
                } else if (list1.get(0).getGroup_id() == list2.get(0).getGroup_id()) {
                    return 0;
                } else if (list1.get(0).getGroup_id() > list2.get(0).getGroup_id()) {
                    return -1;
                }
                return 0;
            }
        });
        String[] title = {"专家/性别", "体色(背)", "体色(腹)", "额齿", "第4侧齿", "背部疣状突", "得分"};
        int row = 0;    //行数游标
        for (int i = 0; i < Group_QualityScore_List.size(); i++) {
            ArrayList<QualityScoreOBJ> one_group_quality_score = Group_QualityScore_List.get(i);
            QualityScoreOBJ qualityScoreOBJ = one_group_quality_score.get(0);
            Label label = new Label(0, row, "第" + String.valueOf(qualityScoreOBJ.getGroup_id()) + "组", setBackGround_LightGreen()); //一个组的信息的第一行：组信息栏
            sheet.addCell(label);
            row++;  //游标向下移动1行 组别栏占用一行
            label = new Label(0, row, title[0], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(1, row, title[1], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(2, row, title[2], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(3, row, title[3], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(4, row, title[4], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(5, row, title[5], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(6, row, title[6], setBackGround_VeryLightYellow());
            sheet.addCell(label);
            row++;  //游标向下移动1行 标题栏占用一行
            for (int j = 0; j < one_group_quality_score.size(); j++) {
                QualityScoreOBJ one_quality_score = one_group_quality_score.get(j);
                String sex = "";
                if (one_quality_score.getCrab_sex() == 0) {
                    sex = "雌";
                } else if (one_quality_score.getCrab_sex() == 1) {
                    sex = "雄";
                }
                label = new Label(0, row + j, "专家" + j + "/" + sex);
                sheet.addCell(label);
                label = new Label(1, row + j, String.valueOf(one_quality_score.getScore_bts()));
                sheet.addCell(label);
                label = new Label(2, row + j, String.valueOf(one_quality_score.getScore_fts()));
                sheet.addCell(label);
                label = new Label(3, row + j, String.valueOf(one_quality_score.getScore_ec()));
                sheet.addCell(label);
                label = new Label(4, row + j, String.valueOf(one_quality_score.getScore_dscc()));
                sheet.addCell(label);
                label = new Label(5, row + j, String.valueOf(one_quality_score.getScore_bbyzt()));
                sheet.addCell(label);
                label = new Label(6, row + j, String.valueOf(one_quality_score.getScore_fin()));
                sheet.addCell(label);
            }
            row += one_group_quality_score.size();    //游标向下移动n行 （n为这个组的种质分数数量）
            GroupOBJ this_group = new GroupOBJ();
            for (int j = 0; j < AllGroup.size(); j++) {
                GroupOBJ groupOBJ = AllGroup.get(j);
                if (groupOBJ.getGroup_id() == qualityScoreOBJ.getGroup_id()) {
                    this_group = groupOBJ;
                }
            }
            label = new Label(0, row, "雌蟹平均种质分");
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(this_group.getQuality_score_f()));
            sheet.addCell(label);
            row++;  //游标向下移动1行 雌蟹平均种质分栏
            label = new Label(0, row, "雄蟹平均种质分");
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(this_group.getQuality_score_m()));
            sheet.addCell(label);
            row++;  //游标向下移动1行 雄蟹平均种质分栏
            label = new Label(0, row, "雌雄平均分", setBackGround_LightOrange());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf((this_group.getQuality_score_f() + this_group.getQuality_score_m()) / (float) 2.0), setBackGround_LightOrange());
            sheet.addCell(label);
            row++;  //游标向下移动1行 雌雄平均分栏
        }
        wwb.write();
        wwb.close();
        return true;
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

    private static WritableCellFormat setBackGround_VeryLightYellow() {
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
            format.setBackground(Colour.VERY_LIGHT_YELLOW);// 非常浅黄色背景
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

}