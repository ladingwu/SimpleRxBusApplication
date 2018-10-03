package com.example.laddingwu.rxbusapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.laddingwu.rxbusapplication.model.ActivityToFragmentsBean;
import com.example.simplerxbus.bus.RxBusReceiver;
import com.example.simplerxbus.bus.RxBusUtils;

public class MainActivity extends AppCompatActivity{
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.input);
        textView = findViewById(R.id.result);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityToFragmentsBean bean = new ActivityToFragmentsBean();
                bean.data1 = "data1 from MainActivity";
                bean.data2 = "data2 also from MainActivity";
                RxBusUtils.postSticky(Tag.BUS_CLICK_FRIST, bean);
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
        RxBusUtils.receive(this,Tag.BUS_CLICK_FRIST_RESULT, new RxBusReceiver<Object>() {
            @Override
            public void receive(Object data) {
                if (data instanceof String) {
                    textView.setText((String) data);

                }
            }
        });
    }
}
