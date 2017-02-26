package com.ruwant.eam.model.excel;

import com.ruwant.eam.presenter.EamPresenter;

import jxl.Sheet;

/**
 * Created by 00265372 on 2016/8/5.
 */
public interface RWExcel {

    void readExcel(final EamPresenter eamPresenter, String fileName);
}
