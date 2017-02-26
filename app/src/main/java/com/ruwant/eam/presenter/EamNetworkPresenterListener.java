package com.ruwant.eam.presenter;

/**
 * Created by 00265372 on 2017/2/11.
 */

public interface EamNetworkPresenterListener<T> {

    void onSuccess(T result);

    void onError();

    void onfinish();
}
