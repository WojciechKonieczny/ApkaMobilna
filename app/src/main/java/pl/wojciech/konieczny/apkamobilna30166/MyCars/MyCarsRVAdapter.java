package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyCarsRVAdapter extends ListAdapter<MyCarsModal, MyCarsRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    MyCarsRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<MyCarsModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<MyCarsModal>() {
        @Override
        public boolean areItemsTheSame(MyCarsModal oldItem, MyCarsModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(MyCarsModal oldItem, MyCarsModal newItem) {
            return oldItem.getMark().equals(newItem.getMark()) &&
                    oldItem.getModel().equals(newItem.getModel()) &&
                    oldItem.getEngine() == newItem.getEngine() &&
                    oldItem.getFuelType().equals(newItem.getFuelType()) &&
                    oldItem.getYear() == newItem.getYear();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_rv_item, parent, false);
        return new ViewHolder(item);
    }

    // below line of code is use to set data to each item of our recycler view.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyCarsModal model = getCarAt(position);
        holder.carMarkTextView.setText(model.getMark());
        holder.carModelTextView.setText(model.getModel());
        holder.carFuelTextView.setText(model.getFuelType());
        holder.carYearTextView.setText(model.getYear());
        holder.carEngineTextView.setText(model.getEngine());
    }

    // creating a method to get course modal for a specific position.
    public MyCarsModal getCarAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView carMarkTextView, carModelTextView, carFuelTextView, carYearTextView, carEngineTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            carMarkTextView = itemView.findViewById(R.id.textView_car_mark);
            carModelTextView = itemView.findViewById(R.id.textView_car_model);
            carFuelTextView = itemView.findViewById(R.id.textView_car_fuel);
            carYearTextView = itemView.findViewById(R.id.textView_car_year);
            carEngineTextView = itemView.findViewById(R.id.textView_car_engine);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MyCarsModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
