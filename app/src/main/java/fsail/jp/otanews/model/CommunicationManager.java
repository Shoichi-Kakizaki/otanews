package fsail.jp.otanews.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.*;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;

import de.greenrobot.event.EventBus;

/**
 * Created by kakizaki_shoichi on 2015/07/10.
 */

public class CommunicationManager {

    private RequestQueue mQueue;
    private JSONArray mResponse;
    private final EventBus eventBus;

    public CommunicationManager(){
        eventBus = EventBus.getDefault();
    }

    public void getQiita(Context context) {
        // Volley でリクエスト
        mQueue = Volley.newRequestQueue(context);
        String url = "https://qiita.com/api/v2/items";
        mQueue.add(new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Qiita","responzse : " + response);
                        mResponse = response;
                        // 非同期でeventBusにpostする
                        eventBus.post(new AsyncContent(true, mResponse));
                    }
                }, null));

    }

}
