package fsail.jp.otanews.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fsail.jp.otanews.R;
import fsail.jp.otanews.activity.MainActivity;

/**
 * Created by kakizaki_shoichi on 2015/07/14.
 */

public class TopHeaderFragment extends HeaderFragment {

    private MainActivity mainActivity;
    private View v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_top_header, container, false);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }

    @Override
    public void onStart(){
        super.onStart();

        // 設定ボタン
        TextView confbtn = (TextView)v.findViewById(R.id.confbtn);
        confbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.settingTransition();
            }
        });

        // 戻るボタン
        TextView laterbtn = (TextView)v.findViewById(R.id.laterbtn);
        laterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.laterTransition();
            }
        });

    }
}
