package fsail.jp.otanews.api;

import android.content.SharedPreferences;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import fsail.jp.otanews.App;

/**
 * Created by kakizaki_shoichi on 2015/07/19.
 */
public class DataManager {

    /*シングルトンの設定*/
    private static DataManager instance = new DataManager();
    public DataManager(){
        instance = this;
    };
    public static DataManager getInstance() {
        return instance;
    }

    /*AdvertisingIDの確認・取得・保存*/
    public String getAdvertisingId(){
        SharedPreferences shared = App.getInstance().getSharedPreferences();
        return shared.getString("idfa", "");
    }

    public void setAdvertisingId(String adId){
        SharedPreferences shared = App.getInstance().getSharedPreferences();
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("idfa", adId);
        editor.commit();
    }

    /*UUIDの確認・取得・保存*/
    //確認
    public boolean isUuid(){
       return getUuid().length() !=0;
    }

    //取得
    public String getUuid(){
        SharedPreferences shared = App.getInstance().getSharedPreferences();
        return shared.getString("uuid", "");
    }

    //保存
    public void setUserId(String adId){
        String userId = adId;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(adId.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++){
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            userId = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        SharedPreferences shared = App.getInstance().getSharedPreferences();
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("uuid", userId);
        editor.commit();
    }

}
