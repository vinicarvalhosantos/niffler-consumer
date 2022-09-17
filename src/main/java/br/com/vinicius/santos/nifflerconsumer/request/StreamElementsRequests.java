package br.com.vinicius.santos.nifflerconsumer.request;

import br.com.vinicius.santos.nifflerconsumer.model.StreamElementsModel;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StreamElementsRequests {

    @PUT("v2/points/{channelId}/{username}/{points}")
    Call<StreamElementsModel> addPointsToUser(@Path("channelId") String channelId, @Path("username") String username,
                                              @Path("points") int pointsToAdd, @Header("Authorization") String authHeader);

}
