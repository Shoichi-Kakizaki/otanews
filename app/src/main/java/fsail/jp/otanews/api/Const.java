package fsail.jp.otanews.api;

import fsail.jp.otanews.App;

/**
 * Created by kakizaki_shoichi on 2015/07/19.
 */
public class Const {

    /*API関係の定数*/
    public static final String APP_ID ="kurumil";

    //private static String API_DOMAIN_NAME = "http://akashic-stg.apptama.mobi/"  // テスト環境
    public static final String API_DOMAIN_NAME = "http://pasti.apptama.mobi/"; // 本番環境
    public static final String API_SERVER_NAME = API_DOMAIN_NAME + "api/v1/apps/" + APP_ID + "/";
    public static final String USER_AGENT = "jp.fsail.pasti Android Mobile AppleWebKit Device";
    public static final String URL_DEVICE_STATUS = "?device=android";


    /*パラメータを作成する*/
    public static String parameter() {
        return URL_DEVICE_STATUS + "&app_id=" + APP_ID + "&idfa=" + DataManager.getInstance().getAdvertisingId() + "&uuid=" + DataManager.getInstance().getUuid() + "&version=" + App.getInstance().getVersionName();
    }
}
