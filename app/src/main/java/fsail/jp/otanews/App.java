package fsail.jp.otanews;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.activeandroid.app.Application;

/**
 * Created by kakizaki_shoichi on 2015/07/19.
 */
public class App extends Application {

    /*シングルトンの設定*/
    private static App instance = new App();
    public App(){
        instance = this;
    }
    public static App getInstance() {
        return instance;
    }

    //=======================================================
    /// アプリの設定取得
    //=======================================================
    public SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }


    /*バージョン情報*/
    public String getVersionName() {
        PackageManager pm = getPackageManager();
        String versionName = "";
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_META_DATA);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("Otanews","バージョン情報の取得に失敗しました");
        }
        return versionName;
    }





}
