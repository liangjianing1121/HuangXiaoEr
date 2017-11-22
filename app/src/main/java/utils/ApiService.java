package utils;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by DELL on 2017/11/20.
 */

public interface ApiService {
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("member/register")
    Observable<ResponseBody> update(@Body RequestBody requestBody);

}
