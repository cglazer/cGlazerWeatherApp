package cglazer.myweathercglazer;

/**
 * Created by Chaya Glazer on 1/11/2016.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Chaya Glazer on 1/11/2016.
 */
public class ForecastAsyncTask extends AsyncTask<Long, String, ForecastReport> {
    private RecyclerView forecastRecyclerView;
    private ForecastRecyclerViewAdapter adapter;
    private String zip;
    Context context;
    TextView loc;

    public ForecastAsyncTask(RecyclerView recyclerView, Context context, String zip, TextView locationName) {
        this.forecastRecyclerView = recyclerView;
        this.zip=zip;
        this.context=context;
        this.loc= locationName;
    }

    @Override
    protected ForecastReport doInBackground(Long... params) {
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + this.zip + "&units=imperial&cnt=16&appid=2de143494c0b295cca9337e1e96b00e0");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream in = null;
        try {
            in = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        Gson gson = new Gson();
        ForecastReport report = gson.fromJson(reader, ForecastReport.class);
        adapter = new ForecastRecyclerViewAdapter(report, loc);

        return null;
    }

    @Override
    protected void onPostExecute(ForecastReport result) {
        super.onPostExecute(result);
        forecastRecyclerView.setAdapter(adapter);
    }

}
