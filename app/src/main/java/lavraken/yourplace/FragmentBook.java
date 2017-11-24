package lavraken.yourplace;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Palle on 18/11/2017.
 */

public class FragmentBook extends Fragment {


List<Procedures> proceduresList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_book,container,false);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Procedures procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);

        procedures = new Procedures("Mad Max: Fury Road", "Action & Adventure", "2015");
        proceduresList.add(procedures);



        ProcedureAdapter procedureAdapter = new ProcedureAdapter(proceduresList);
        recyclerView.setAdapter(procedureAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        // Inflate the layout for this fragment
        return rootView ;
    }




}
