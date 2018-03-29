package ke.co.ipandasoft.mmaraumobileinfo.config;

/**
 * Created by Kevin Gitonga on 2/11/2018.
 */

public class Config {

    public static final String PREF_NAME = "mmarau.prefs";
    public static final String COMMON_KEY = "student_id";
    public static String REST_ROOT_URL="http://18.219.108.164/";
    public static String REST_ROOT_DATA="admin/index.php/api/";
    public static String REST_ROOT=REST_ROOT_URL+REST_ROOT_DATA;
    public static String NOTICE_IMAGE_ROOT_URL=REST_ROOT_URL+"admin/uploads/noticephoto/";
    public static String EVENT_IMAGE_ROOT_URL=REST_ROOT_URL+"admin/uploads/eventphoto/";

    //New api version
    public static final String NEWS_BASE_URL = "https://newsapi.org/v2/";

    //API Key
    public static final String API_KEY = "8b22d8c07562496d883a67cc1b5137da";

    public static final String TITLE_WEBVIEW_KEY = "save_text_webView";

    public static final String INTENT_HEADLINE = "key_HeadLine";
    public static final String INTENT_DESCRIPTION = "key_description";
    public static final String INTENT_DATE = "key_date";
    public static final String INTENT_IMG_URL = "key_imgURL";
    public static final String INTENT_ARTICLE_URL = "key_URL";

    public static final String INTENT_CONTENT_TITLE = "key_Title";
    public static final String INTENT_CONTENT_DESCRIPTION = "key_Description";
    public static final String INTENT_CONTENT_IMAGE_URL = "key_Image_Url";
    public static final String INTENT_CONTENT_START_DATE = "key_Start_Date";
    public static final String INTENT_CONTENT_END_DATE = "key_End_Date";

    public static final String INTENT_URL = "url";

    public static final String RECYCLER_STATE_KEY = "recycler_list_state";

    public static final String TITLE_STATE_KEY = "title_state";
    public static String developerWebsite="http://ipandasoft.co.ke";
    public static String webRootUrl="http://ipandasoft.co.ke";
}
