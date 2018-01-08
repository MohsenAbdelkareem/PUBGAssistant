package course.leedev.cn.pubgassistant.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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

}
