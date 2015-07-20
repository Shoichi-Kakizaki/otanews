package fsail.jp.otanews.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import fsail.jp.otanews.async.AsyncContent;

/**
 * Created by kakizaki_shoichi on 2015/07/10.
 */

public class CommunicationManager {

    private RequestQueue mQueue;
    private JSONObject mResponse;
    private final EventBus eventBus;

    public CommunicationManager(){
        eventBus = EventBus.getDefault();
    }


    //=======================================================
    ///コンテンツの取得
    //=======================================================
    public void getContent(Context context, String category) {
        String url = ApiManager.getContentUrl(category);

        // Volley でリクエスト
        mQueue = Volley.newRequestQueue(context);
        mQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Qiita","responzse : " + response);
                        mResponse = response;
                        // 非同期でeventBusにpostする
                        eventBus.post(new AsyncContent(true, mResponse));
                    }
                }, null));

    }

//    //=======================================================
//    ///Qiitaの取得
//    //=======================================================
//    public void getQiita(Context context) {
//        // Volley でリクエスト
//        mQueue = Volley.newRequestQueue(context);
//        String url = "https://qiita.com/api/v2/items";
//        mQueue.add(new JsonArrayRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d("Qiita","responzse : " + response);
//                        mResponse = response;
//                        // 非同期でeventBusにpostする
//                        eventBus.post(new AsyncContent(true, mResponse));
//                    }
//                }, null));
//
//    }

}
