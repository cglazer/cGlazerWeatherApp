package cglazer.myweathercglazer;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Chaya Glazer on 1/11/2016.
 */
public class ForecastViewHolder extends RecyclerView.ViewHolder{
    private TextView day;
    private ImageView imageIcon;
    private TextView maxTemp;
    private TextView minTemp;

    public ForecastViewHolder(View itemView){
        super(itemView);
        this.day=(TextView) itemView.findViewById(R.id.day);
        this.imageIcon=(ImageView) itemView.findViewById(R.id.tempIcon);
        this.maxTemp= (TextView)itemView.findViewById(R.id.maxtemp);
        this.minTemp=(TextView)itemView.findViewById(R.id.mintemp);
    }

    public void bind(ForecastViewHolder viewHolder, List list, int position) {
        DateFormat formatter = new SimpleDateFormat("EEEE");
        this.day.setText(formatter.format(list.getDt()*1000));
        day.setTextColor(Color.WHITE);
        Context context= viewHolder.imageIcon.getContext();
        Picasso.with(context).load("http://openweathermap.org/img/w/" + list.getWeather()[0].getIcon()
                + ".png").into(imageIcon);
        this.maxTemp.setText(String.valueOf((int) list.getTemp().getMax()) + "\u00B0");
        maxTemp.setTextColor(Color.WHITE);
        this.minTemp.setText(String.valueOf((int) list.getTemp().getMin()) + "\u00B0");
        minTemp.setTextColor(Color.BLUE);
    }
}
