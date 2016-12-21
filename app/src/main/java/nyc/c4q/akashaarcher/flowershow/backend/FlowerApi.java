package nyc.c4q.akashaarcher.flowershow.backend;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by akashaarcher on 12/21/16.
 */

public interface FlowerApi {

    @GET("feeds/flowers.json")
  //  Call<FlowerResponse> listFlowers();

    Call<List<FlowerResponse>> listFlowers();

    //void listFlowers(Callback<FlowerResponse> response);

}
