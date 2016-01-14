package cglazer.myweathercglazer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Chaya Glazer on 1/11/2016.
 */
public class ForecastRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ForecastReport report;
    private List[] list;
    private ForecastViewHolder holder;
    private CurrentTempViewHolder currentTempHolder;
    private TextView location;

    public ForecastRecyclerViewAdapter(ForecastReport report, TextView location) {
        this.report = report;
        this.list = this.report.getList();
        this.location = location;
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0:
                View currentTempView = inflater.inflate(R.layout.current_temp, parent, false);
                viewHolder = new CurrentTempViewHolder(currentTempView, location);
                break;
            default:
                View itemView = inflater.inflate(R.layout.forecast_list, parent, false);
                viewHolder = new ForecastViewHolder(itemView);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return position;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            currentTempHolder = (CurrentTempViewHolder) holder;
            currentTempHolder.bind(currentTempHolder, list[0], report.getCity().getName());
        } else {
            this.holder = (ForecastViewHolder) holder;
            this.holder.bind(this.holder, list[position], position);
        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }
}
