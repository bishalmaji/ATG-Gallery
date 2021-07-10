package com.bishal.atggallery.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bishal.atggallery.forapi.APIService;
import com.bishal.atggallery.forapi.RetrofitInstance;
import com.bishal.atggallery.model.ImageModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageViewModel extends ViewModel {
    private MutableLiveData<List<ImageModel>> imagelist;
    private String title="";
    private String url_s="";
    private int width_s=0;
    private int height_s=0;
    public ImageViewModel(){
        imagelist =new MutableLiveData<>();
    }
    public MutableLiveData<List<ImageModel>> getImagemodel(){
        return imagelist;
    }
    public void makeApiCall() throws IOException {
        APIService apiService= RetrofitInstance.getRetrofitClient()
                .create(APIService.class);
        Call<JsonObject> call=apiService.getimgelist();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

           List<ImageModel> imageModel =new ArrayList<>();
          JsonArray list =response.body().getAsJsonObject("photos").getAsJsonArray("photo");
         for (int i=0;i<list.size();i++){
             title = list.get(i).getAsJsonObject().get("title").getAsString();
          url_s = list.get(i).getAsJsonObject().get("url_s").getAsString();
            width_s = list.get(i).getAsJsonObject().get("width_s").getAsInt();
             height_s = list.get(i).getAsJsonObject().get("height_s").getAsInt();

                 imageModel.add(i,new ImageModel(title,url_s,width_s
                        ,height_s ));

         }imagelist.setValue(imageModel);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("thisisbishal", "onFailure: "+t.getMessage());
            }
        });

    }
}
