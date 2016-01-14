package cglazer.myweathercglazer;


import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private EditText edit;
    private ImageButton button;
    private ArrayList<String> zips;
    private LocationPagerAdapter adapter;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.zips = new ArrayList<String>();
        zips.add("11367");
        this.edit = (EditText) findViewById(R.id.edit);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        this.button = (ImageButton) findViewById(R.id.plus_button);
        Picasso.with(this).load("http://iconshow.me/media/images/ui/ios7-icons/png/512/plus-empty.png").into(button);
        this.preferences = this.getSharedPreferences("DEFAULT", MODE_PRIVATE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String zip = edit.getText().toString();
                if (zip.length() == 5 && !zips.contains(zip)) {
                    zips.add(zip);
                    adapter.notifyDataSetChanged();
                    edit.setText("");
                    edit.setFocusable(false);
                }
            }
        });


        this.adapter = new LocationPagerAdapter(zips);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        //we will save info to shared preference
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("array_size", zips.size());
        for (int i = 0; i < zips.size(); i++)
            editor.putString("ZIP " + i, zips.get(i));
        editor.apply();
    }


    @Override
    public void onResume() {
        super.onResume();
        int size = preferences.getInt("array_size", 0);
        for (int i = 0; i < size; i++) {
            String zip = preferences.getString("ZIP " + i, "11367");
            if (!zips.contains(zip)) {
                this.zips.add(zip);
            }
        }
        adapter.notifyDataSetChanged();
    }

}
