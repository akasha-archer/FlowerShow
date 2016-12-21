package nyc.c4q.akashaarcher.flowershow;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.akashaarcher.flowershow.backend.FlowerApi;
import nyc.c4q.akashaarcher.flowershow.backend.FlowerResponse;
import nyc.c4q.akashaarcher.flowershow.model.Flower;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akashaarcher on 12/21/16.
 */

public class FlowerFragment extends Fragment {

    private static final String TAG = "FlowerFragment";
    public static final String BASE_URL = "http://services.hanselandpetal.com/";


    private List<Flower> flowerList;
    private FlowerAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isOnline()) {
            Toast.makeText(getActivity(), "You are connected to the internet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flower, container, false);

        flowerList = new ArrayList<>();

        adapter = new FlowerAdapter(getContext());
        RecyclerView postRecyclerView = (RecyclerView) view.findViewById(R.id.flower_rv);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postRecyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FlowerApi api = retrofit.create(FlowerApi.class);
        Call<List<FlowerResponse>> flowerCall = api.listFlowers();

        flowerCall.enqueue(new Callback<List<FlowerResponse>>() {

            @Override
            public void onResponse(Call<List<FlowerResponse>>  call, Response<List<FlowerResponse> response) {
                //FlowerResponse flowerResp = response.body();
                flowerList = response.body().getFlowers();
                adapter.setData(flowerList);
                Log.i("LIST:", flowerList.size() + "");
                Log.d(TAG, "There was a success" + response);
            }

            @Override
            public void onFailure(Call<List<FlowerResponse>>  call, Throwable t) {
                Log.d(TAG, "There was a failure" + t);
                Toast.makeText(getActivity(), "Connection to Retrofit failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
            public boolean isOnline() {
                ConnectivityManager cm = (ConnectivityManager)
                        getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();
                if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                    return true;
                }
                return false;
            }



    }


//            @Override
//            public void onResponse(Call<FlowerResponse> call, Response<Flower> response) {
//                FlowerResponse flowerResp = response.body();
//                flowerList = flowerResp.getFlowers();
//                adapter.setData(flowerList);
//                Log.i("LIST:", flowerList.size() + "");
//                Log.d(TAG, "There was a success" + response);
//            }
//
//            @Override
//            public void onFailure(Call<Flower> call, Throwable t) {
//                Log.d(TAG, "There was a failure" + t);
//                Toast.makeText(getActivity(), "Connection to Retrofit failed", Toast.LENGTH_SHORT).show();
//
//            }
