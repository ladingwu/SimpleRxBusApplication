package com.example.laddingwu.rxbusapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.laddingwu.rxbusapplication.fragments.FristFragment;
import com.example.laddingwu.rxbusapplication.fragments.SecondFragment;
import com.example.simplerxbus.bus.RxBusUtils;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBusUtils.post(Tag.BUS_CLICK_FRIST_RESULT, "data from secondActivity");
                finish();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_1, new FristFragment()).commitAllowingStateLoss();
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_2, new SecondFragment()).commitAllowingStateLoss();
    };

}
