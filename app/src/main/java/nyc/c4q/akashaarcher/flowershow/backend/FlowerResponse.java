package nyc.c4q.akashaarcher.flowershow.backend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.akashaarcher.flowershow.model.Flower;

/**
 * Created by akashaarcher on 12/21/16.
 */

public class FlowerResponse {

    private static final String JSON_FLOWERS = "flowers";
    private static final String JSON_CATEGORY = "category";
    private static final String JSON_PRICE = "price";
    private static final String JSON_INSTRUCTIONS = "instructions";
    private static final String JSON_NAME = "name";
    private static final String JSON_ID = "productId";

    List<Flower> flowers;


    public List<Flower> getFlowers(){
        return flowers;
    }

    public static FlowerResponse parse(String response) {
        try {
            JSONObject json = new JSONObject(response);
            JSONArray flowerList = json.getJSONArray(JSON_FLOWERS);

            FlowerResponse result = new FlowerResponse();
            result.flowers = parseFlowerFeed(flowerList);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static List<Flower> parseFlowerFeed(JSONArray flowerList) {
        List<Flower> result = new ArrayList<>();
        for (int i = 0; i < flowerList.length(); i++){
            try {
                JSONObject obj = flowerList.getJSONObject(i);

                String category = obj.getString(JSON_CATEGORY);
                double price = obj.getDouble(JSON_PRICE);
                String instructions = obj.getString(JSON_INSTRUCTIONS);
                String name = obj.getString(JSON_NAME);
                int id = obj.getInt(JSON_ID);
                Flower newFlower = new Flower(category, price, instructions, name, id);
                result.add(newFlower);
            }
               catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
