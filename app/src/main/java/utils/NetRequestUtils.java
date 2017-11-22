package utils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 2017/11/20.
 */

public class NetRequestUtils {
     private ApiService apiService;
     public static NetRequestUtils mInstance;

    public NetRequestUtils(ApiService apiService) {
        this.apiService = apiService;
    }

    public  ApiService getApiService(){
        return apiService;
    }

    public static class Builder{


        private List<Converter.Factory> converFractories=new ArrayList<>();
        private List<CallAdapter.Factory> callAdapterFractories=new ArrayList<CallAdapter.Factory>();

        public Builder addConverterFractory(Converter.Factory factory){
            converFractories.add(factory);
            return this;
        }

        public Builder addcallAdapterFractroy(CallAdapter.Factory factory){
            callAdapterFractories.add(factory);
            return this;
        }


        public NetRequestUtils build(){
            OkHttpClient.Builder okBuilder=new OkHttpClient.Builder();
            okBuilder.addInterceptor(new ParamsInsterceptor());

            Retrofit.Builder builder=new Retrofit.Builder().baseUrl(BaseUrl.BASE_URL)
                    .client(okBuilder.build());
            if(converFractories.size()==0)
            {
                converFractories.add(GsonConverterFactory.create());
            }
            else
            {
                for (Converter.Factory converFractory : converFractories) {
                    builder.addConverterFactory(converFractory);
                }
            }

            if(callAdapterFractories.size()==0)
            {
                callAdapterFractories.add(RxJava2CallAdapterFactory.create());
            }
            else{

                for (CallAdapter.Factory callAdapterFractory : callAdapterFractories) {
                    callAdapterFractories.add(callAdapterFractory);
                }
            }
            //动态代理
            ApiService apiService = builder.build().create(ApiService.class);
            mInstance=new NetRequestUtils(apiService);
            return mInstance;
        }

    }
}


