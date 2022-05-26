package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanksModal;
import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyTanksRVAdapter extends ListAdapter<MyTanksModal, MyTanksRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    MyTanksRVAdapter() {
        super(DIFF_CALLBACK);
    }

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<MyTanksModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<MyTanksModal>() {
        @Override
        public boolean areItemsTheSame(MyTanksModal oldItem, MyTanksModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(MyTanksModal oldItem, MyTanksModal newItem) {
            return oldItem.getLitersFuel() == newItem.getLitersFuel() &&
                    oldItem.getCostPerLiter() == newItem.getCostPerLiter() &&
                    oldItem.getCostSum() == newItem.getCostPerLiter() &&
                    oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getGasStation().equals(newItem.getGasStation()) &&
                    oldItem.getMileage() == newItem.getMileage();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is use to inflate our layout
        // file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tank_rv_item, parent, false);
        return new ViewHolder(item);
    }

    // below line of code is use to set data to each item of our recycler view.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MyTanksModal model = getTankAt(position);
        holder.tankDateTextView.setText(model.getDate());
        holder.tankMileageTextView.setText(model.getMileage());
        holder.tankLiterCostTextView.setText(String.valueOf(model.getCostPerLiter()));
        holder.tankSumLiterTextView.setText(String.valueOf(model.getCostSum()));
        holder.tankStationTextView.setText(model.getGasStation());
    }

    // creating a method to get course modal for a specific position.
    public MyTanksModal getTankAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView tankDateTextView, tankMileageTextView, tankLiterCostTextView, tankSumLiterTextView, tankStationTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing each view of our recycler view.
            tankDateTextView = itemView.findViewById(R.id.textView_tank_date);
            tankMileageTextView = itemView.findViewById(R.id.textView_tank_mileage);
            tankLiterCostTextView = itemView.findViewById(R.id.textView_tank_liter_cost);
            tankSumLiterTextView = itemView.findViewById(R.id.textView_tank_cost_sum);
            tankStationTextView = itemView.findViewById(R.id.textView_tank_station);

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
        void onItemClick(MyTanksModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
