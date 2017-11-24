package lavraken.yourplace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Palle on 24/11/2017.
 */

public class FragmentAppointments extends Fragment {

    private List<Appointment> appointmentList = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_appointments, container, false);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Appointment appointment = new Appointment("12.02", "15:00", "Wax");
        appointmentList.add(appointment);

        appointment = new Appointment("12.02", "15:00", "Wax");
        appointmentList.add(appointment);

        appointment = new Appointment("12.02", "15:00", "Wax");
        appointmentList.add(appointment);

        appointment = new Appointment("12.02", "15:00", "Wax");
        appointmentList.add(appointment);







        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(appointmentList);
        recyclerView.setAdapter(appointmentAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootView;
    }

}