package fsail.jp.otanews.model;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.UUID;
import fsail.jp.otanews.api.DataManager;

/**
 * Created by kakizaki_shoichi on 2015/07/19.
 */
public class Login {

   /*AdvertisingID・UUIDを取得・保存する*/
    public static void getAdId(final Activity activity) {
        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {
                try {
                    AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(activity);
                    if (info.isLimitAdTrackingEnabled()) {
                        return null;
                    } else {
                        return info.getId();
                    }
                } catch (IOException e) {
                    // GooglePlayServicesへの接続が失敗
                    return null;
                } catch (IllegalStateException e) {
                    // メインスレッドで処理が呼ばれた
                    return null;
                } catch (GooglePlayServicesNotAvailableException e) {
                    // GooglePlayがデバイスにインストールされていなかった
                    return null;
                } catch (GooglePlayServicesRepairableException e) {
                    // GooglePlayServicesを使ううえでエラーが起きた
                    return null;
                } catch (NoClassDefFoundError e) {
                    // com.google.android.gmsがない
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String advertisingID) {
                //TODO: NULLの場合ぬるぽになる可能性あり

                DataManager.getInstance().setAdvertisingId(advertisingID);

                //advertisingIDが取得出来なかった場合適当な数値からUUIDを発行する
                if (advertisingID == null) {
                    advertisingID = UUID.randomUUID().toString();
                }

                DataManager.getInstance().setUserId(advertisingID);
            }
        }.execute(null, null, null);
    }

}
