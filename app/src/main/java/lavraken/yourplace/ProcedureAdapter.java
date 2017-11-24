package lavraken.yourplace;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wishe on 24-Nov-17.
 */

public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.MyViewHolder> {

    private List<Procedures> proceduresList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, duration, price;

        public MyViewHolder(View view){
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            duration = (TextView) view.findViewById(R.id.duration);
            price = (TextView) view.findViewById(R.id.price);
        }
    }

    public ProcedureAdapter(List<Procedures> proceduresList){
        this.proceduresList = proceduresList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.procedure_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Procedures procedures = proceduresList.get(position);
        holder.name.setText(procedures.getName());
        holder.duration.setText(procedures.getDuration());
        holder.price.setText(procedures.getPrice());
    }

    @Override
    public int getItemCount() {
        return proceduresList.size();
    }
}
