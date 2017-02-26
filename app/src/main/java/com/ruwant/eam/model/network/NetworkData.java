package com.ruwant.eam.model.network;

import android.database.SQLException;

import com.ruwant.eam.presenter.EamNetworkPresenterListener;
import com.ruwant.eam.presenter.EamPresenter;

import java.util.List;

/**
 * Created by 00265372 on 2016/8/6.
 */
public interface NetworkData {

    public void login(String userName, String password, EamNetworkPresenterListener listener);

    void updateVersion();
}
