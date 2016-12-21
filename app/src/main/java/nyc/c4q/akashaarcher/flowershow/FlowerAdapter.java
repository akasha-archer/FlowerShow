package nyc.c4q.akashaarcher.flowershow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.akashaarcher.flowershow.model.Flower;

/**
 * Created by akashaarcher on 12/21/16.
 */

public class FlowerAdapter extends RecyclerView.Adapter {

    Context context;
    private List<Flower> flowers;

    public FlowerAdapter(Context context) {
        this.context = context;
        this.flowers = new ArrayList<>();
    }



    public void setData(List<Flower> flowers) {
        this.flowers = flowers;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FlowerViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FlowerViewHolder postViewHolder = (FlowerViewHolder) holder;
        Flower flowerList = flowers.get(position);
        postViewHolder.bind(flowerList);

    }

    @Override
    public int getItemCount() {
        return flowers.size();
    }
}
