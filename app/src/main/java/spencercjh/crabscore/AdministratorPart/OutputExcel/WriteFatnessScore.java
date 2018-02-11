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
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;

/**
 * Created by spencercjh on 2018/1/2.
 * iClass
 */

@SuppressWarnings({"deprecation", "ConstantConditions", "ResultOfMethodCallIgnored"})
class WriteFatnessScore {

    static boolean writeExcel(ArrayList<GroupOBJ> AllGroup, ArrayList<CrabOBJ> AllCrab, WritableWorkbook wwb, WritableSheet sheet) throws Exception {
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
        ArrayList<ArrayList<CrabOBJ>> Group_Crab_List = new ArrayList<>(AllGroup.size());
        for (int i = 0; i < AllGroup.size(); i++) {
            ArrayList<CrabOBJ> one_group_crab = new ArrayList<>();
            GroupOBJ groupOBJ = AllGroup.get(i);
            Iterator<CrabOBJ> iterator = AllCrab.iterator();
            while (iterator.hasNext()) {
                CrabOBJ crabOBJ = iterator.next();
                if (crabOBJ.getGroup_id() == groupOBJ.getGroup_id()) {
                    one_group_crab.add(crabOBJ);
                    iterator.remove();
                }
            }
            Collections.sort(one_group_crab, new Comparator<CrabOBJ>() {
                @Override
                public int compare(CrabOBJ crabOBJ1, CrabOBJ crabOBJ2) {
                    if (crabOBJ1.getCrab_sex() < crabOBJ2.getCrab_sex()) {
                        return 1;
                    } else if (crabOBJ1.getCrab_sex() == crabOBJ2.getCrab_sex()) {
                        return 0;
                    } else if (crabOBJ1.getCrab_sex() > crabOBJ2.getCrab_sex()) {
                        return -1;
                    }
                    return 0;
                }
            });
            Group_Crab_List.add(one_group_crab);
        }
        Collections.sort(Group_Crab_List, new Comparator<ArrayList<CrabOBJ>>() {
            @Override
            public int compare(ArrayList<CrabOBJ> list1, ArrayList<CrabOBJ> list2) {
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
        String[] title = {"编号", "体重(g)", "壳长(cm)", "肥满度", "备注"};
        int row = 0;    //行数游标
        for (int i = 0; i < Group_Crab_List.size(); i++) {  //遍历所有组
            ArrayList<CrabOBJ> one_group_crab = Group_Crab_List.get(i);
            CrabOBJ crabOBJ = one_group_crab.get(0);
            Label label = new Label(0, row, "第" + String.valueOf(crabOBJ.getGroup_id()) + "组", setBackGround_LightGreen()); //一个组的信息的第一行：组信息栏
            sheet.addCell(label);
            row++;  //游标向下移动1行 组别栏占用一行
            label = new Label(0, row, title[0]);
            sheet.addCell(label);
            label = new Label(1, row, title[1]);
            sheet.addCell(label);
            label = new Label(2, row, title[2]);
            sheet.addCell(label);
            label = new Label(3, row, title[3], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(4, row, title[4], setBackGround_VeryLightYellow());
            sheet.addCell(label);
            row++;  //游标向下移动1行 标题栏占用一行
            float average_weight_m = 0, average_weight_f = 0, average_length_m = 0, average_length_f = 0, average_fatness_m = 0, average_fatness_f = 0, count_f = 0, count_m = 0;
            for (int j = 0; j < one_group_crab.size(); j++) {   //遍历这个组的所有螃蟹
                CrabOBJ one_crab = one_group_crab.get(j);
                String sex = "";
                if (one_crab.getCrab_sex() == 0) {
                    sex = "雌";
                    average_fatness_f += one_crab.getCrab_fatness();
                    average_length_f += one_crab.getCrab_length();
                    average_weight_f += one_crab.getCrab_weight();
                    count_f++;
                } else if (one_crab.getCrab_sex() == 1) {
                    sex = "雄";
                    average_fatness_m += one_crab.getCrab_fatness();
                    average_length_m += one_crab.getCrab_length();
                    average_weight_m += one_crab.getCrab_weight();
                    count_m++;
                }
                label = new Label(0, row + j, sex + String.valueOf(j + 1));     //第j+1只螃蟹的编号
                sheet.addCell(label);
                label = new Label(1, row + j, String.valueOf(one_crab.getCrab_weight()));   //第j+1只螃蟹的体重
                sheet.addCell(label);
                label = new Label(2, row + j, String.valueOf(one_crab.getCrab_length()));   //第j+1只螃蟹的壳长
                sheet.addCell(label);
                label = new Label(3, row + j, String.valueOf(one_crab.getCrab_fatness()), setBackGround_LightGreen()); //第j+1只螃蟹的肥满度
                sheet.addCell(label);
            }
            label = new Label(4, row, "雌个数" + count_f, setBackGround_VeryLightYellow());    //这个组的第一只螃蟹的备注属性
            sheet.addCell(label);
            label = new Label(4, row + 1, "雄个数" + count_m, setBackGround_VeryLightYellow());  //这个组的第二只螃蟹的备注属性
            sheet.addCell(label);
            row += one_group_crab.size();   //游标向下移动n行 （n为这个组的螃蟹数量）
            label = new Label(0, row, "雌蟹平均值", setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(average_weight_f), setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(2, row, String.valueOf(average_length_f), setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(3, row, String.valueOf(average_fatness_f), setBackGround_LightGreen());
            sheet.addCell(label);
            row++;  //游标向下移动1行 雌蟹平均数据栏
            label = new Label(0, row, "雄蟹平均值", setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(average_weight_m), setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(2, row, String.valueOf(average_length_m), setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(3, row, String.valueOf(average_fatness_m), setBackGround_LightGreen());
            sheet.addCell(label);
            row++;  //游标向下移动1行 雄蟹平均数据栏
            GroupOBJ this_group = new GroupOBJ();
            for (int j = 0; j < AllGroup.size(); j++) {
                GroupOBJ groupOBJ = AllGroup.get(j);
                if (groupOBJ.getGroup_id() == crabOBJ.getGroup_id()) {
                    this_group = groupOBJ;
                }
            }
            label = new Label(0, row, "雌蟹肥满度得分", setBackGround_VeryLightYellow());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(this_group.getFatness_score_f()), setBackGround_VeryLightYellow());
            sheet.addCell(label);
            row++;  //游标向下移动1行 雌蟹肥满度得分栏
            label = new Label(0, row, "雄蟹肥满度得分", setBackGround_VeryLightYellow());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(this_group.getFatness_score_m()), setBackGround_VeryLightYellow());
            sheet.addCell(label);
            row++;  //游标向下移动1行 雌蟹肥满度得分栏
            label = new Label(0, row, "雌雄平均分", setBackGround_LightOrange());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf((this_group.getFatness_score_f() + this_group.getFatness_score_m()) / (float) 2.0), setBackGround_LightOrange());
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