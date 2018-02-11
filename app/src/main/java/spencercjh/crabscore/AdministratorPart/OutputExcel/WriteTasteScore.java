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
import spencercjh.crabscore.OBJ.TasteScoreOBJ;

/**
 * Created by spencercjh on 2018/1/2.
 * iClass
 */

@SuppressWarnings("deprecation")
class WriteTasteScore {

    static boolean writeExcel(ArrayList<GroupOBJ> AllGroup, ArrayList<TasteScoreOBJ> AllTasteScore, WritableWorkbook wwb, WritableSheet sheet) throws Exception {
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
        ArrayList<ArrayList<TasteScoreOBJ>> Group_TasteScore_List = new ArrayList<>(AllGroup.size());
        for (int i = 0; i < AllGroup.size(); i++) {
            ArrayList<TasteScoreOBJ> one_group_taste_score = new ArrayList<>();
            GroupOBJ groupOBJ = AllGroup.get(i);
            Iterator<TasteScoreOBJ> iterator = AllTasteScore.iterator();
            while (iterator.hasNext()) {
                TasteScoreOBJ tasteScoreOBJ = iterator.next();
                if (tasteScoreOBJ.getGroup_id() == groupOBJ.getGroup_id()) {
                    one_group_taste_score.add(tasteScoreOBJ);
                    iterator.remove();
                }
            }
            Collections.sort(one_group_taste_score, new Comparator<TasteScoreOBJ>() {
                @Override
                public int compare(TasteScoreOBJ q1, TasteScoreOBJ q2) {
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
            Group_TasteScore_List.add(one_group_taste_score);
        }
        Collections.sort(Group_TasteScore_List, new Comparator<ArrayList<TasteScoreOBJ>>() {
            @Override
            public int compare(ArrayList<TasteScoreOBJ> list1, ArrayList<TasteScoreOBJ> list2) {
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
        String[] title = {"专家/性别", "蟹盖颜色", "鳃颜色", "膏、黄颜色", "腥味、香味", "膏、黄", "腹部肌肉", "第二、三步足肌肉", "得分"};
        int row = 0;    //行数游标
        for (int i = 0; i < Group_TasteScore_List.size(); i++) {
            ArrayList<TasteScoreOBJ> one_group_taste_score = Group_TasteScore_List.get(i);
            TasteScoreOBJ tasteScoreOBJ = one_group_taste_score.get(0);
            Label label = new Label(0, row, "第" + String.valueOf(tasteScoreOBJ.getGroup_id()) + "组", setBackGround_LightGreen()); //一个组的信息的第一行：组信息栏
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
            label = new Label(6, row, title[6], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(7, row, title[7], setBackGround_LightGreen());
            sheet.addCell(label);
            label = new Label(8, row, title[8], setBackGround_VeryLightYellow());
            sheet.addCell(label);
            row++;  //游标向下移动1行 标题栏占用一行
            for (int j = 0; j < one_group_taste_score.size(); j++) {
                TasteScoreOBJ one_taste_score = one_group_taste_score.get(j);
                String sex = "";
                if (one_taste_score.getCrab_sex() == 0) {
                    sex = "雌";
                } else if (one_taste_score.getCrab_sex() == 1) {
                    sex = "雄";
                }
                label = new Label(0, row + j, "专家" + j + "/" + sex);
                sheet.addCell(label);
                label = new Label(1, row + j, String.valueOf(one_taste_score.getScore_ygys()));
                sheet.addCell(label);
                label = new Label(2, row + j, String.valueOf(one_taste_score.getScore_sys()));
                sheet.addCell(label);
                label = new Label(3, row + j, String.valueOf(one_taste_score.getScore_ghys()));
                sheet.addCell(label);
                label = new Label(4, row + j, String.valueOf(one_taste_score.getScore_xwxw()));
                sheet.addCell(label);
                label = new Label(5, row + j, String.valueOf(one_taste_score.getScore_gh()));
                sheet.addCell(label);
                label = new Label(6, row + j, String.valueOf(one_taste_score.getScore_fbjr()));
                sheet.addCell(label);
                label = new Label(7, row + j, String.valueOf(one_taste_score.getScore_bzjr()));
                sheet.addCell(label);
                label = new Label(8, row + j, String.valueOf(one_taste_score.getScore_fin()));
                sheet.addCell(label);
            }
            row += one_group_taste_score.size();    //游标向下移动n行 （n为这个组的种质分数数量）
            GroupOBJ this_group = new GroupOBJ();
            for (int j = 0; j < AllGroup.size(); j++) {
                GroupOBJ groupOBJ = AllGroup.get(j);
                if (groupOBJ.getGroup_id() == tasteScoreOBJ.getGroup_id()) {
                    this_group = groupOBJ;
                }
            }
            label = new Label(0, row, "雌蟹平均种质分");
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(this_group.getTaste_score_f()));
            sheet.addCell(label);
            row++;  //游标向下移动1行 雌蟹平均口感分栏
            label = new Label(0, row, "雄蟹平均种质分");
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf(this_group.getTaste_score_m()));
            sheet.addCell(label);
            row++;  //游标向下移动1行 雄蟹平均口感分栏
            label = new Label(0, row, "雌雄平均分", setBackGround_LightOrange());
            sheet.addCell(label);
            label = new Label(1, row, String.valueOf((this_group.getTaste_score_f() + this_group.getTaste_score_m()) / (float) 2.0), setBackGround_LightOrange());
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
