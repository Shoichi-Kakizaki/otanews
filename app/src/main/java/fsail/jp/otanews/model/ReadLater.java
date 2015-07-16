package fsail.jp.otanews.model;

import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import java.util.List;
import fsail.jp.otanews.db.ReadLaterModel;

/**
 * Created by kakizaki_shoichi on 2015/07/14.
 */

public class ReadLater {

    // 値を追加する
    public boolean insertValue(String title, String uri){
        ReadLaterModel dbManager  = new ReadLaterModel();
        try {
            dbManager.title = title;
            dbManager.uri = uri;
            dbManager.save();
            return true;
        } catch(Exception e) {
            Log.d("Otanews", "ReadLaterModelへのインサートに失敗しました。");
            return false;
        }
    }

    // 全件取得する
    public List selectAllValue(){
        return new Select().from(ReadLaterModel.class).execute();
    }

    // 指定の値を1件取得する(要素番号は1から指定する)
    public ReadLaterModel selectValue(int element){
       List<ReadLaterModel> list = new Select().from(ReadLaterModel.class).execute();
       return list.get(element);
    }

    // 全件削除する
    public boolean deleteAllValue(){
        List<ReadLaterModel> deleteValues =  new Select().from(ReadLaterModel.class).execute();
        // トランザクション開始
        ActiveAndroid.beginTransaction();
        try {
            for(ReadLaterModel value : deleteValues) {
                value.delete();
            }
            // トランザクション完了
            ActiveAndroid.setTransactionSuccessful();
            ActiveAndroid.endTransaction();
            return true;
        } catch(Exception e) {
            ActiveAndroid.endTransaction();
            Log.d("Otanews", "ReadLaterModelの削除に失敗しました。");
            return false;
        }
    }

    // 指定の値を1件削除する(要素番号は1から指定する)
    public boolean deleteValue(int element){
        try {
            List<ReadLaterModel> list = new Select().from(ReadLaterModel.class).execute();
            list.get(element).delete();
            return true;
        } catch(Exception e) {
            Log.d("Otanews", "ReadLaterModelの削除に失敗しました。");
            return false;
        }
    }

}
