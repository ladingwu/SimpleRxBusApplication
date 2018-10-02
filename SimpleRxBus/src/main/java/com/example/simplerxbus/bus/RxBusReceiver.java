package com.example.simplerxbus.bus;

import android.support.annotation.CallSuper;
import android.util.Log;

import com.example.simplerxbus.observer.RxResourceObserver;

/**
 * Created by 拉丁吴 on 2018
 */

public abstract class RxBusReceiver<T> extends RxResourceObserver<T> {


    private String filter;
    @Override
    public void onNext(T t) {
        try {
            receive(t);

        } catch (Exception e) {

        }
    }

    @CallSuper
    @Override
    public void onError(Throwable e) {
        clearIfNeed();
    }

    @CallSuper
    @Override
    public void onComplete() {
        clearIfNeed();
    }
    protected void setCurFilter(String filter){
        this.filter = filter;
    }

    public abstract void receive(T data);
    private void clearIfNeed(){
        Log.w("rxbus","clearIfNeed ======");

        if (filter != null) {
            RxSimpleBus.getBus().clearCurMessage(filter);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        clearIfNeed();
    }
}
