package makanbu.com.makanbu.fragmentHome;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import makanbu.com.makanbu.R;
import makanbu.com.makanbu.adapter.MakananAdapter;
import makanbu.com.makanbu.model.Makanan;


public class Berkuah extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<Makanan> listPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_berkuah, container, false);

        recyclerView = view.findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);

        listPosts = new ArrayList<>();

        listPosts.add(new Makanan(R.drawable.sayurasem,R.drawable.bu_esi,"Sayur Asem","12","10000",4));
        listPosts.add(new Makanan(R.drawable.ayamgoreng,R.drawable.ibu,"Ayam Goreng","23","20000",2));
        listPosts.add(new Makanan(R.drawable.sayurasem,R.drawable.bu_esi,"Sayur Asem","12","10000",4));
        listPosts.add(new Makanan(R.drawable.ayamgoreng,R.drawable.ibu,"Ayam Goreng","23","20000",5));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MakananAdapter postList = new MakananAdapter(getContext(), listPosts);

        recyclerView.setAdapter(postList);
        return view;
    }
}