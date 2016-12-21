package nyc.c4q.akashaarcher.flowershow;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FlowerFragment homeFragment = new FlowerFragment();
        FragmentTransaction mainFragTransaction = getSupportFragmentManager().beginTransaction();
        mainFragTransaction.add(R.id.main_container, homeFragment);
        mainFragTransaction.commit();

    }
}
