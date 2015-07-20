package fsail.jp.otanews.api;

/**
 * Created by kakizaki_shoichi on 2015/07/19.
 */
public class ApiManager {


    /*コンテンツURL*/
    public static String getContentUrl(String category){
        return Const.API_SERVER_NAME + category + "/" + Const.parameter();
    }

//    /*利用規約*/
//    public static String getRuleUrl(){
//        return Const.API_DOMAIN_NAME + APP + Const.APP_ID + "/" + RULE;
//    }
//
//    /*プライバシーポリシー*/
//    public static  String getPolicyUrl(){
//        return Const.API_DOMAIN_NAME + APP  + Const.APP_ID + "/" + POLICY;
//    }

}
