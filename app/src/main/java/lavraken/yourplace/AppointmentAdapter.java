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

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {

    private List<Appointment> appointmentList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView rName, time, date, procedure;

        public MyViewHolder(View view){
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            date = (TextView) view.findViewById(R.id.date);
            procedure = (TextView) view.findViewById(R.id.procedure);
        }
    }

    public AppointmentAdapter(List<Appointment> appointmentList){
        this.appointmentList = appointmentList;
    }
    @Override
    public AppointmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AppointmentAdapter.MyViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.time.setText(appointment.getTime());
        holder.procedure.setText(appointment.getProcedure());
        holder.date.setText(appointment.getDate());
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }
}
