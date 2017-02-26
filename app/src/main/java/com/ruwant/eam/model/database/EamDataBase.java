package com.ruwant.eam.model.database;

import android.database.Cursor;
import android.database.SQLException;

import com.ruwant.eam.presenter.EamPresenter;

import java.util.List;

/**
 * Created by 00265372 on 2016/8/6.
 */
public interface EamDataBase {

    void open() throws SQLException;
    long insertData(int cols, List<String> contentList);
    String[] getColsName();
    void queryDataAndDisplay(final EamPresenter eamPresenter, int colNum, String queryContent);
    void close();
}
