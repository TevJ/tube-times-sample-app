package com.everton.tom.tubetimessampleapp.depencyinjection.modules;

import com.everton.tom.tubetimessampleapp.data.source.arrivals.remote.ArrivalsService;
import com.everton.tom.tubetimessampleapp.data.source.stoppoints.remote.StopPointsService;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tom on 30/11/2017.
 */

@Module
public class ServiceModule {

    private static final String APPLICATION_ID = "8153be6d";

    private static final String APPLICATION_KEY = "552aa5eb218717d53c4349eb2fdd3deb";

    private Retrofit mRetrofit;

    public ServiceModule() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("app_id", APPLICATION_ID)
                        .addQueryParameter("app_key", APPLICATION_KEY)
                        .build();

                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .url(url)
                        .addHeader("Content-Type", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor);

        mRetrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .baseUrl("https://api.tfl.gov.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Singleton
    @Provides
    StopPointsService provideStopPointsService() {
        return mRetrofit.create(StopPointsService.class);
    }

    @Singleton
    @Provides
    ArrivalsService provideArrivalsService() {
        return mRetrofit.create(ArrivalsService.class);
    }
}
