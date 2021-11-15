package mx.com.TheThree.TlesStore.ui.ApiRest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorRetrofit {

    Retrofit retrofit;
    private static final String URL="http://192.168.0.65:8080/";


    public Retrofit getAdaptadorLocal()
    {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .callTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
