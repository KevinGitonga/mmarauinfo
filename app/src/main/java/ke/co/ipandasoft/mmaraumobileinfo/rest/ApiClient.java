package ke.co.ipandasoft.mmaraumobileinfo.rest;

import ke.co.ipandasoft.mmaraumobileinfo.config.Config;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin Gitonga on 2/19/2018.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient(OkHttpClient.Builder httpClient) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.NEWS_BASE_URL)                                  //<-- Base URL is loaded
                    .addConverterFactory(GsonConverterFactory.create()) //<-- Uses GSON convertor to convert the JSON to JAVA objects
                    .client(httpClient.build())                         //<-- Builds the request with OkHttp
                    .build();
        }
        return retrofit;
    }
}
