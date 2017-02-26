package com.ruwant.eam.presenter;

import java.util.List;

import jxl.Sheet;

/**
 * Created by 00265372 on 2016/8/6.
 */
public interface EamNetworkPresenter {

    void login(String userName, String password, EamNetworkPresenterListener listener);

    void updateVersion();
}
