package com.bishal.atggallery.forapi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
//   ?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s
    public static String BASE_URL=" https://api.flickr.com/services/rest/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient(){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.
                            create())
                    .build();

        }
        return retrofit;
    }
}
