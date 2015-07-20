package fsail.jp.otanews.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import fsail.jp.otanews.activity.MainActivity;
import fsail.jp.otanews.R;
import fsail.jp.otanews.async.AsyncContent;
import fsail.jp.otanews.api.CommunicationManager;

public class ContentFragment extends Fragment {

    private final EventBus eventBus = EventBus.getDefault();
    private ArrayList<String> mTitles = new ArrayList<String>();
    private ArrayList<String> mUris = new ArrayList<String>();

    private View v;
    private MainActivity mainActivity;

    public static ContentFragment getInstance(int position) {
        ContentFragment f = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_content, container, false);
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // コンテンツ(本文記事)を取得する
        CommunicationManager content = new CommunicationManager();
        content.getContent(mainActivity, "life");
        Log.d("通信いくよ", "びびびびび");
    }

    // eventbusの通知を受け取り、必要なデータをインスタント変数に格納する
    public void onEvent(AsyncContent event) {
        mTitles = event.title();
        mUris = event.uri();
        showList();
    }

    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);

        // 戻った際にリストを再描画する
        showList();
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
        super.onPause();
    }

    private void showList(){
        // リストビューに表示するためのデータを設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_list_item_1);
        for (String title: mTitles) {
            adapter.add(title);
        }
        ListView qiitaList = (ListView)v.findViewById(R.id.ContentView);
        qiitaList.setAdapter(adapter);

        /* webViewに遷移する */
        qiitaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mainActivity.webViewTransition(mUris.get(position));
            }
        });
    }

}
