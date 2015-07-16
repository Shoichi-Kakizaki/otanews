package fsail.jp.otanews.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import fsail.jp.otanews.R;
import fsail.jp.otanews.activity.ConfigActivity;
import fsail.jp.otanews.activity.LaterActivity;
import fsail.jp.otanews.activity.MainActivity;
import fsail.jp.otanews.db.ReadLaterModel;
import fsail.jp.otanews.model.ReadLater;

/**
 * Created by kakizaki_shoichi on 2015/07/14.
 */

public class LaterContentFragment extends Fragment {

    private ReadLater readLater = new ReadLater();
    private LaterActivity laterActivity;
    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_later_content, container, false);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        laterActivity = (LaterActivity)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        showList();
    }

    @Override
    public void onResume() {
        super.onResume();

        // 戻った際にリストを再描画する
        showList();
    }
//
    private void showList(){

        // あと読み記事を全件取得する
        List<ReadLaterModel> values = readLater.selectAllValue();


        // リストビューに表示するためのデータを設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(laterActivity, android.R.layout.simple_list_item_1);
        for (ReadLaterModel value: values) {
            adapter.add(value.title);
        }
        final ListView laterList = (ListView)v.findViewById(R.id.LaterContentView);
        laterList.setAdapter(adapter);

        // 画面遷移の処理はActivityに任せる
        laterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ReadLaterModel value = readLater.selectValue(position);
                laterActivity.laterTransition(value.uri);
            }
        });
    }
}
