package spencercjh.crabscore.AdministratorPart.OutputExcel;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import spencercjh.crabscore.OBJ.CrabOBJ;
import spencercjh.crabscore.OBJ.GroupOBJ;
import spencercjh.crabscore.OBJ.QualityScoreOBJ;
import spencercjh.crabscore.OBJ.TasteScoreOBJ;

/**
 * Created by spencercjh on 2018/1/2.
 * iClass
 */

@SuppressWarnings({"deprecation", "ConstantConditions", "ResultOfMethodCallIgnored"})
class CreateExcelFile {
    private static String root = Environment.getExternalStorageDirectory().getPath();

    static boolean createExcelFile(Context context, ArrayList<GroupOBJ> AllGroup, ArrayList<CrabOBJ> AllCrab,
                                   ArrayList<QualityScoreOBJ> AllQualityScore, ArrayList<TasteScoreOBJ> AllTasteScore, String competition_year) throws Exception {
        String fileName = "王宝和杯" + competition_year;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) && getAvailableStorage() > 1000000) {
            Toast.makeText(context, "SD卡不可用", Toast.LENGTH_LONG).show();
            return false;
        }
        File file;
        File dir = new File(context.getExternalFilesDir(null).getPath());
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        WritableWorkbook wwb;
        OutputStream os = new FileOutputStream(file);
        wwb = Workbook.createWorkbook(os);
        WritableSheet sheet0 = wwb.createSheet("金蟹奖、优质奖-肥满度", 0);
        WritableSheet sheet1 = wwb.createSheet("金蟹奖、优质奖-排序", 1);
        WritableSheet sheet2 = wwb.createSheet("最佳种质奖-专家", 2);
        WritableSheet sheet3 = wwb.createSheet("最佳种植奖-排序", 3);
        WritableSheet sheet4 = wwb.createSheet("口感奖-专家", 4);
        WritableSheet sheet5 = wwb.createSheet("口感奖-排序", 5);
        WritableSheet sheet6 = wwb.createSheet("参赛单位", 6);
        boolean flag0 = WriteFatnessScore.writeExcel(AllGroup, AllCrab, wwb, sheet0);
        boolean flag1 = WriteFatnessSort.writeExcel(AllGroup, wwb, sheet1);
        boolean flag2 = WriteQualityScore.writeExcel(AllGroup, AllQualityScore, wwb, sheet2);
        boolean flag3 = WriteQualitySort.writeExcel(AllGroup, wwb, sheet3);
        boolean flag4 = WriteTasteScore.writeExcel(AllGroup, AllTasteScore, wwb, sheet4);
        boolean flag5 = WriteTasteSort.writeExcel(AllGroup, wwb, sheet5);
        boolean flag6 = WriteCompany.writeExcel(AllGroup, wwb, sheet6);
        wwb.write();
        wwb.close();
        return flag0 && flag1 && flag2 && flag3 && flag4 && flag5 && flag6;
    }

    private static long getAvailableStorage() {
        StatFs statFs = new StatFs(root);
        long blockSize = statFs.getBlockSize();
        long availableBlocks = statFs.getAvailableBlocks();
        return blockSize * availableBlocks;
    }

}
