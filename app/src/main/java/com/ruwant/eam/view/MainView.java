package com.ruwant.eam.view;

import java.util.List;

import jxl.Sheet;

/**
 * Created by 00265372 on 2016/8/6.
 */
public interface MainView {
    void showLoading();
    void hideLoading();
    void setExcelContentToView(int rows, int cols,  Sheet sheet);
    void setExcelColesNameToView(String[] colesName);
    void setDataBaseContentToView(int colCount, String[] rowContent);
}
