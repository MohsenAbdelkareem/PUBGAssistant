package course.leedev.cn.pubgassistant.service;

import course.leedev.cn.pubgassistant.model.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by thoma on 2018/1/8.
 */

public interface MyService {

    @FormUrlEncoded
    @POST("api/do.php")
    Observable<String> getToken(@Field("action") String action, @Field("name") String name,
                                  @Field("password") String password);

    @FormUrlEncoded
    @POST("api/do.php")
    Observable<String> getPhone(@Field("action") String action, @Field("sid") String sid,
                                @Field("token") String token, @Field("phoneType") String phoneType);

    @FormUrlEncoded
    @POST("api/do.php")
    Observable<String> getMessage(@Field("action") String action, @Field("sid") String sid, @Field("phone") String phone,
                                @Field("token") String token, @Field("author") String author);

    @FormUrlEncoded
    @POST("api/do.php")
    Observable<String> cancleRecv(@Field("action") String action, @Field("sid") String sid, @Field("phone") String phone,
                                  @Field("token") String token);

    /**
     * login
     */
    @Headers({
            "X-Bmob-Application-Id: 939489416823004166309b2569532b1c",
            "X-Bmob-REST-API-Key: 7ddb92f8b8e5cda3923472e7caa206b3",
            "Content-Type: application/json"
    })
    @GET("1/login")
    Observable<User> login(@Query("username") String username, @Query("password") String password);

}
