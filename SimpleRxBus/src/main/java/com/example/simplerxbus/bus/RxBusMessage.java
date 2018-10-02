package com.example.simplerxbus.bus;

import android.support.annotation.NonNull;

/**
 * Created by 拉丁吴 on 2018
 */

 class RxBusMessage {
    private String type;
    private Object msg;

    public RxBusMessage(@NonNull String type, @NonNull Object msg) {
        this.type = type;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }
}
