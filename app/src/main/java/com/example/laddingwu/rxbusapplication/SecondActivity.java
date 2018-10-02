package com.example.laddingwu.rxbusapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.simplerxbus.bus.RxBusReceiver;
import com.example.simplerxbus.bus.RxBusUtils;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv =findViewById(R.id.iiiiiiii);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBusUtils.post(Tag.BUS_CLICK_FRIST_RESULT,"from secondActivity");
                finish();
            }
        });
    };

    private int i =0;
    @Override
    protected void onResume() {
        super.onResume();
        RxBusUtils.receiveSticky(this,Tag.BUS_CLICK_FRIST, new RxBusReceiver<Object>() {
            @Override
            public void receive(Object data) {
                if (data instanceof String) {
                    i++;
                    String str = tv.getText().toString();
                    Log.w("TAG", (String) data+i+"==================================="+str);
                    tv.append( ""+data);
                    String str2 = tv.getText().toString();
                    String a = str2+"";

                }
            }
        });
    }
}
