package com.ruwant.eam.presenter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ruwant.aspectj.annotation.DebugTrace;
import com.ruwant.eam.R;
import com.ruwant.eam.model.database.EamDataBase;
import com.ruwant.eam.model.database.EamDataBaseImpl;
import com.ruwant.eam.model.excel.RWExcel;
import com.ruwant.eam.model.excel.RWExcelImpl;
import com.ruwant.eam.view.MainView;

import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;

/**
 * Created by 00265372 on 2016/8/6.
 */
public class EamPresenterImpl implements EamPresenter {

    private EamDataBase eamDataBase;
    private RWExcel rwExcel;
    private MainView mainView;


    public EamPresenterImpl(MainView mainView, Context context) {

        this.mainView = mainView;

        this.rwExcel = new RWExcelImpl();

        this.eamDataBase = new EamDataBaseImpl(context);
        this.eamDataBase.open();
    }

    @Override
    public void importData(String fileName) {

        mainView.showLoading();

        rwExcel.readExcel(EamPresenterImpl.this, fileName);

        mainView.hideLoading();
    }

    @Override
    public void setExcelContentToPresenter(int rows, int cols,  Sheet sheet) {
        mainView.setExcelContentToView(rows, cols, sheet);
    }

    @Override
    public long insertDataToDataBase(int cols, List<String> contentList) {

        return eamDataBase.insertData(cols, contentList);
    }

    @Override
    public void setExcelColesName() {

        String[] colsName = eamDataBase.getColsName();
        mainView.setExcelColesNameToView(colsName);
    }

    @Override
    public void queryData(int colNum, String queryContent) {

        mainView.showLoading();

        eamDataBase.queryDataAndDisplay(EamPresenterImpl.this, colNum, queryContent);

        mainView.hideLoading();

    }

    @Override
    public void setDataBaseContentToPresenter(int colCount, String[] rowContent) {
        mainView.setDataBaseContentToView(colCount, rowContent);
    }

    @Override
    public void exportData(String fileName) {

    }

    @Override
    public void onDestroy() {
        eamDataBase.close();
    }
}
