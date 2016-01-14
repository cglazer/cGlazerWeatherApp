package cglazer.myweathercglazer;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Chaya Glazer on 1/12/2016.
 */
public class CurrentTempViewHolder extends RecyclerView.ViewHolder {
    private TextView wind;
    private ImageView tempImage;
    private TextView maxTemp;
    private TextView minTemp;
    private TextView temp;
    private TextView description;
    private TextView location;
    private ImageView windImage;


    public CurrentTempViewHolder(View itemView, TextView location) {
        super(itemView);

        this.wind = (TextView) itemView.findViewById(R.id.wind);
        this.tempImage = (ImageView) itemView.findViewById(R.id.tempIcon);
        this.maxTemp = (TextView) itemView.findViewById(R.id.maxtemp);
        this.minTemp = (TextView) itemView.findViewById(R.id.mintemp);
        this.temp = (TextView) itemView.findViewById(R.id.temp);
        this.description = (TextView) itemView.findViewById(R.id.description);
        this.location = location;
        this.windImage=(ImageView)itemView.findViewById(R.id.windImage);
    }

    public void bind(CurrentTempViewHolder viewHolder, List list, String cityName) {
        location.setText(cityName);
        location.setTextColor(Color.WHITE);
        final Context context = viewHolder.tempImage.getContext();
        Picasso.with(context).load("http://openweathermap.org/img/w/" + list.getWeather()[0].getIcon()
                + ".png").into(tempImage);
        this.description.setText(String.valueOf(list.getWeather()[0].getMain()));
        description.setTextColor(Color.WHITE);
        this.maxTemp.setText( String.valueOf((int) list.getTemp().getMax()) + "\u00B0");
        maxTemp.setTextColor(Color.WHITE);
        this.minTemp.setText(String.valueOf((int)list.getTemp().getMin()) + "\u00B0");
        minTemp.setTextColor(Color.WHITE);
        this.temp.setText(String.valueOf((int)list.getTemp().getDay()) + "\u00B0");
        temp.setTextColor(Color.WHITE);
        this.wind.setText("Windchill  " + String.valueOf((int)list.getSpeed()) + "\u00B0");
        wind.setTextColor(Color.WHITE);
        Picasso.with(context).load("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcRHASBQ20CggXrTFBXdV604dCe-Y79sga7IY7tDiSdP8-akZsu6").into(windImage);
    }
}
