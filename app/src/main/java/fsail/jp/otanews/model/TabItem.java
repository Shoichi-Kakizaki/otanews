package fsail.jp.otanews.model;

/**
 * Created by kakizaki_shoichi on 2015/07/09.
 */
public class TabItem {

    // タブの項目
    private final String[] sTabs = { "日曜日", "月曜日", "火曜日", "水曜日",
                                     "木曜日", "金曜日", "土曜日" };

    public String[] getTabList(){
        return sTabs;
    }
}
