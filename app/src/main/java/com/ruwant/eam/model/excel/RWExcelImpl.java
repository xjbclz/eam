package com.ruwant.eam.model.excel;

import android.os.Environment;

import com.ruwant.eam.presenter.EamPresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import jxl.*;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by 00265372 on 2016/8/4.
 */
public class RWExcelImpl implements RWExcel{

    int Rows;
    int Cols;

    @Override
    public void readExcel(final EamPresenter eamPresenter, String fileName) {
        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS),fileName);
            InputStream is = new FileInputStream(file);

            Workbook book = Workbook.getWorkbook(is);

            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            Rows = sheet.getRows();
            Cols = sheet.getColumns();

            eamPresenter.setExcelContentToPresenter(Rows, Cols, sheet);

            book.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void createExcel() {
        try {
            // 创建或打开Excel文件
            File file1 = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS),"demo1.xls");

            WritableWorkbook book = Workbook.createWorkbook(file1);

            // 生成名为“第一页”的工作表,参数0表示这是第一页
            WritableSheet sheet1 = book.createSheet("第一页", 0);
            WritableSheet sheet2 = book.createSheet("第三页", 2);
            // 在Label对象的构造函数中,元格位置是第一列第一行(0,0)以及单元格内容为test
            Label label = new Label(0, 0, "test");
            // 将定义好的单元格添加到工作表中
            sheet1.addCell(label);
   /*
    * 生成一个保存数字的单元格.必须使用Number的完整包路径,否则有语法歧义
    */
            jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
            sheet2.addCell(number);
            // 写入数据并关闭文件
            book.write();
            book.close();
        } catch (Exception e) {
            //System.out.println(e);
            e.printStackTrace();
        }
    }

    public void updateExcel(String filePath) {
        try {

            Workbook rwb = Workbook.getWorkbook(new File(filePath));
            WritableWorkbook wwb = Workbook.createWorkbook(new File(
                    Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOCUMENTS).getPath()+ "/demo2.xls"), rwb);// copy
            WritableSheet ws = wwb.getSheet(0);
            WritableCell wc = ws.getWritableCell(0, 0);
            // 判断单元格的类型,做出相应的转换
            Label label = (Label) wc;
            label.setString("The value has been modified");
            wwb.write();
            wwb.close();
            rwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
