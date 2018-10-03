package com.example.laddingwu.rxbusapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laddingwu.rxbusapplication.R;
import com.example.laddingwu.rxbusapplication.Tag;
import com.example.laddingwu.rxbusapplication.model.ActivityToFragmentsBean;
import com.example.simplerxbus.bus.RxBusReceiver;
import com.example.simplerxbus.bus.RxBusUtils;

/**
 * A fragment with a Google +1 button.
 * Use the {@link FristFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FristFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // The request code must be 0 or greater.
    private static final int PLUS_ONE_REQUEST_CODE = 0;
    // The URL to +1.  Must be a valid URL.
    private final String PLUS_ONE_URL = "http://developer.android.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FristFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FristFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FristFragment newInstance(String param1, String param2) {
        FristFragment fragment = new FristFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        textView = view.findViewById(R.id.text_id);
        TextView tv = view.findViewById(R.id.tv1);
        tv.setText("FristFragment getData: ");
        //Find the +1 button

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        RxBusUtils.receiveSticky(this, Tag.BUS_CLICK_FRIST, new RxBusReceiver<Object>() {
            @Override
            public void receive(Object data) {
                if (data instanceof ActivityToFragmentsBean) {
                    Log.w("fragment===> ", ((ActivityToFragmentsBean) data).data1);
                    textView.setText(((ActivityToFragmentsBean) data).data1);
                }
            }
        });
        // Refresh the state of the +1 button each time the activity receives focus.
    }


}
