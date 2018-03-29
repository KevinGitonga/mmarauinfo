package ke.co.ipandasoft.mmaraumobileinfo.rest;


import ke.co.ipandasoft.mmaraumobileinfo.models.EventsCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.FcmCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.InquiriesCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.LoginCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.NewsCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.NoticesCallback;
import ke.co.ipandasoft.mmaraumobileinfo.models.RegisterCallback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit2.Call;
import retrofit2.http.Query;


/**
 * Created by Kevin Gitonga on 2/11/2018.
 */

public interface RestInterface {



    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("student_email") String student_email,
               @Field("student_password") String student_password, retrofit.Callback<LoginCallback> callbackLogin);


    @FormUrlEncoded
    @POST("/register_fcm")
    void RegisterFcm(
                     @Field("email") String userEmail,
                     @Field("regId") String fcmId, retrofit.Callback<FcmCallback> fcmCallback);

    @FormUrlEncoded
    @POST("/send_inquiry")
    void SendInquiry(@Field("subject") String subject,
               @Field("message") String message,@Field("student_id") String student_id,
                     retrofit.Callback<LoginCallback> callbackLogin);

    @FormUrlEncoded
    @POST("/signup")
    void SignUp(@Field("student_adm_no") String student_adm_no, @Field("student_email") String email,
               @Field("student_password") String student_password, retrofit.Callback<RegisterCallback> callbackRegister);

    @GET("/get_all_notices/")
    void GetNotices(retrofit.Callback<NoticesCallback> noticesCallback);

    @GET("/get_all_events/")
    void GetEvents(retrofit.Callback<EventsCallback> eventsCallback);

    @FormUrlEncoded
    @POST("/get_inquiry")
    void GetInquiries(@Field("student_id") String student_id, retrofit.Callback<InquiriesCallback> inquiriesCallback);

    @retrofit2.http.GET("top-headlines")
    Call<NewsCallback> getHeadlines(@Query("sources") String sources,
                                    @Query("apiKey") String apiKey);


    @FormUrlEncoded
    @POST("/register_device")
    void RegisterDevice(@Field("device_name") String deviceName,
                     @Field("os_version") String osVersion,
                     @Field("device_serial") String deviceSerial, retrofit.Callback<FcmCallback> fcmCallback);
}
