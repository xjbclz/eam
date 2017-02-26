package com.ruwant.eam.presenter;

import java.util.List;

import jxl.Sheet;

/**
 * Created by 00265372 on 2016/8/6.
 */
public interface EamPresenter {

    void importData(String fileName);

    void setExcelContentToPresenter(int rows, int cols,  Sheet sheet);

    long insertDataToDataBase(int cols, List<String> contentList);

    void setExcelColesName();

    void queryData(int colNum, String queryContent);

    void setDataBaseContentToPresenter(int colCount, String[] rowContent);

    void exportData(String fileName);

    void onDestroy();
}
