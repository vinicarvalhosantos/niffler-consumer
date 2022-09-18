package br.com.vinicius.santos.nifflerconsumer.service.impl;

import br.com.vinicius.santos.nifflerconsumer.service.StreamElementsService;
import br.com.vinicius.santos.nifflerconsumer.model.StreamElementsModel;
import br.com.vinicius.santos.nifflerconsumer.request.StreamElementsRequests;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Service
public class StreamElementsServiceImpl implements StreamElementsService {

    @Override
    public StreamElementsModel addPointsToUser(String username, int points) throws IOException {

        final String BASE_URL = System.getenv("STREAM_ELEMENTS_API_BASE");
        final String CHANNEL_ID = System.getenv("CHANNEL_ID");
        final String AUTH_HEADER = String.format("Bearer %s", System.getenv("SECRET_STREAM_ELEMENTS_TOKEN"));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StreamElementsRequests streamElementsRequests = retrofit.create(StreamElementsRequests.class);

        Call<StreamElementsModel> streamElementsModelCall = streamElementsRequests.addPointsToUser(CHANNEL_ID, username, points, AUTH_HEADER);

        return streamElementsModelCall.execute().body();
    }
}
