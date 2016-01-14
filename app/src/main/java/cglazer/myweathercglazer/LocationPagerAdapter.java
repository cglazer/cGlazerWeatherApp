package cglazer.myweathercglazer;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Chaya Glazer on 1/13/2016.
 */
public class LocationPagerAdapter extends PagerAdapter {
    private RecyclerView recyclerView;
    private ArrayList<String> zips;
    private TextView textView;
    private TextView time;

    public LocationPagerAdapter(ArrayList<String> zips) {
        this.zips = zips;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(container.getContext());
        View view = inflater.inflate(R.layout.location_page, null);
        this.textView = (TextView) view.findViewById(R.id.locationName);
        textView.setTextColor(Color.WHITE);
        this.time = (TextView) view.findViewById(R.id.time);

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        String currentDateandTime = formatter.format(new Date());
        time.setText(currentDateandTime);
        time.setTextColor(Color.WHITE);
        this.recyclerView = (RecyclerView) view.findViewById(R.id.forecast_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.recyclerView.setLayoutManager(layoutManager);
        ForecastAsyncTask task = new ForecastAsyncTask(this.recyclerView, container.getContext(), zips.get(position), this.textView);
        task.execute();

        container.addView(view);
        return view;
    }

    public void setTextView(String location) {

    }

    @Override
    public int getCount() {
        return zips.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
