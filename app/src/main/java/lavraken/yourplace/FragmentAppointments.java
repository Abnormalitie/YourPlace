package lavraken.yourplace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Palle on 24/11/2017.
 */

public class FragmentAppointments extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_appointments, container, false);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return inflater.inflate(R.layout.fragment_appointments, container, false);
    }
}