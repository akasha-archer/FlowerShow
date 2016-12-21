package nyc.c4q.akashaarcher.flowershow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.akashaarcher.flowershow.model.Flower;

/**
 * Created by akashaarcher on 12/21/16.
 */

public class FlowerViewHolder extends RecyclerView.ViewHolder {

    private final View view;
    private TextView tvCategory;
    private TextView tvPrice;
    private TextView tvInstructions;
    private TextView tvName;
    private TextView tvId;


    public FlowerViewHolder(ViewGroup parent) {
        super(inflateView(parent));
        view = itemView;
        tvCategory = (TextView) view.findViewById(R.id.tv_category);
        tvPrice = (TextView) view.findViewById(R.id.tv_price);
        tvInstructions = (TextView) view.findViewById(R.id.tv_instructions);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvId = (TextView) view.findViewById(R.id.tv_id);
    }


    private static View inflateView(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return inflater.inflate(R.layout.flower_item_layout, parent, false);
    }


    public void bind(Flower flower) {
        tvCategory.setText(flower.getCategory());
        tvPrice.setText("$" + flower.getPrice());
        tvInstructions.setText(flower.getInstructions());
        tvName.setText(flower.getName());
        tvId.setText(flower.getProductId());
    }

}
