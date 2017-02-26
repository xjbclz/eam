package com.ruwant.eam.presenter;

import android.content.Context;

import com.ruwant.eam.model.database.EamDataBase;
import com.ruwant.eam.model.database.EamDataBaseImpl;
import com.ruwant.eam.model.excel.RWExcel;
import com.ruwant.eam.model.excel.RWExcelImpl;
import com.ruwant.eam.model.network.NetworkData;
import com.ruwant.eam.model.network.NetworkDataImpl;
import com.ruwant.eam.view.MainView;

import java.util.List;

import jxl.Sheet;

/**
 * Created by 00265372 on 2016/8/6.
 */
public class EamNetworkPresenterImpl implements EamNetworkPresenter {

    private NetworkData networkData;

    public EamNetworkPresenterImpl(MainView mainView, Context context) {

        this.networkData = new NetworkDataImpl();
    }

    public void login(String userName, String password, EamNetworkPresenterListener listener){
        networkData.login(userName, password, listener);
    }

    public void updateVersion(){

    }

}
