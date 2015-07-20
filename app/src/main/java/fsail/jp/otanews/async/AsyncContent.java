package fsail.jp.otanews.async;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kakizaki_shoichi on 2015/07/10.
 */
public class AsyncContent {

    private boolean mSuccess;
    private ArrayList<String> mTitle = new ArrayList<String>();
    private ArrayList<String> mUri = new ArrayList<String>();

    // JSONArray用のコンストラクタ
    public AsyncContent(boolean success, JSONObject response) {
        mSuccess = success;
        createStatus(response);
    }

    public boolean isSuccess(){
        return mSuccess;
    }

    public ArrayList title(){
        return mTitle;
    }

    public ArrayList uri(){
        return mUri;
    }

    // 必要なデータを生成する
    private void createStatus(JSONObject response){
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject content = (JSONObject) response.get(String.valueOf(i));
                mTitle.add(content.getString("title"));
                mUri.add(content.getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
