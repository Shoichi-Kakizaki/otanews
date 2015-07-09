package fsail.jp.otanews.model;

/**
 * Created by kakizaki_shoichi on 2015/07/09.
 */
public class TabItem {

    private final int[] tabList = {50, 9, 10, 11, 14, 5, 13, 1, 7, 8, 12, 6, 2, 3, 4, 60, 70};

    // タブの詳細。一つ目がタイトルで、後ろが各種項目のマジックナンバー
    private final String[][] tabNameAndNumber = {
            {"人気の飲食業","1"},
            {"教育ビジネス","2"},
            {"買取ビジネス","3"},
            {"理美容・健康","4"},
            {"無店舗型","5"},
            {"法人向け","6"},
            {"商品力で勝負","7"},
            {"安心の大手","8"},
            {"未経験OK","9"},
            {"一人で開業","10"},
            {"副業するなら","11"},
            {"サポート充実","12"},
            {"高収入","13"},
            {"100万円開業","14"},
            {"トピック","50"},
            {"お気に入り","60"},
            {"設定","70"}};

    public String getTabName(int tabId){
        String result = "non data";
        for(int i = 0; i < tabNameAndNumber.length; i++){
            if(Integer.parseInt(tabNameAndNumber[i][1]) == tabList[tabId]){
                result = tabNameAndNumber[i][0];
            }
        }
        return result;
    }

    public int getTabLength(){
        return tabList.length;
    }

    public int getTabMagicNum(int tabId){ return tabList[tabId];}

    public String getWhatKind(int tabId){
        switch (getTabMagicNum(tabId)) {
            case 50:
                return "news";
            case 60:
                return "favorite";
            case 70:
                return "setting";
            default:
                return "search";
        }
    }
}
