package com.mackenzie.documentalia03.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mackenzie.documentalia03.Adapters.DocAdapter;
import com.mackenzie.documentalia03.Interfaces.DocClickListener;
import com.mackenzie.documentalia03.Models.Documental;
import com.mackenzie.documentalia03.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnDemandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnDemandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Documental> documentalList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DocClickListener docClickListener;
    private int counter = 0;
    private Context context;
    private Toast toast;

    public OnDemandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnDemandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnDemandFragment newInstance(String param1, String param2) {
        OnDemandFragment fragment = new OnDemandFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channels, container, false);
        // Intanciamos la lista y la rellenamos
        documentalList = this.getAllDocumentals();
        // traemos el recycler y el layout de la interfaz con un castin
        mRecyclerView = view.findViewById(R.id.recyclerView);
        // creamos e inicializamos un objeto de tipo LLManager y lo guardamos
        mLayoutManager = new LinearLayoutManager(getContext());

        mAdapter = new DocAdapter(documentalList, R.layout.recycler_view_item, docClickListener);
        // TODO averiguar qu hace esto
        /*mAdapter = new DocAdapter(documentalList, R.layout.recycler_view_item, new OnItemClickListener() {
            @Override
            public void onItemClick(Documental documental1, int position) {
                toast = Toast.makeText(getActivity().getBaseContext(), "Pulsaste " + documental1 + " - " + position, Toast.LENGTH_SHORT);
                toast.show();
            }
        });*/
        // utilizamos el metodo para sethasfixed para decir que tendra un tamanyo fijo. NO USAR CON EL SGLManager
        mRecyclerView.setHasFixedSize(true);
        // utilizamos el metodo setitemanimator para nuestro recyler
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // Insertar el Layout en el recycler
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Insertar lo que hay en el adapter en el recycler
        mRecyclerView.setAdapter(mAdapter);


        // Inflate the layout for this fragment
        return view;
    }

    private List<Documental> getAllDocumentals() {
        return new ArrayList<Documental>() {{
            add(new Documental("Animales", R.raw.animales));
            add(new Documental("Arte y Cine", R.raw.cine));
            add(new Documental("Biografias y personajes", R.raw.biografias));
            add(new Documental("Biologia humana", R.raw.biologia));
            add(new Documental("Ciencia y Tecnologia", R.raw.tecno));
            add(new Documental("Ciudades y Viajes", R.raw.viajes));
            add(new Documental("Deportes", R.raw.deporte));
            add(new Documental("Dinosaurios", R.raw.dino));
            add(new Documental("Drogas y Sustancias", R.raw.drogas));
            add(new Documental("Extraterrestres", R.raw.extra));
            add(new Documental("Fenomenos Climáticos", R.raw.rayos));
            add(new Documental("Geografia Ibérica", R.raw.iberia));
            add(new Documental("Historia", R.raw.historia));
            add(new Documental("Informática y computación", R.raw.computacion));
            add(new Documental("Matematicas y Economia", R.raw.math));
            add(new Documental("Música", R.raw.music));
            add(new Documental("Naturaleza", R.raw.naturaleza));
            add(new Documental("Política", R.raw.politica));
            add(new Documental("Religión", R.raw.religion));
            add(new Documental("Social", R.raw.social));
            add(new Documental("Universo", R.raw.universo));
            add(new Documental("Temática Sexual", R.raw.adult));
        }};
    }

}