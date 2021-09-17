package com.mackenzie.documentalia03.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Toast;

import com.mackenzie.documentalia03.Activities.MainActivity;
import com.mackenzie.documentalia03.Adapters.ChannelsAdapter;
import com.mackenzie.documentalia03.Adapters.ChannelsAdapter2;
import com.mackenzie.documentalia03.Interfaces.ChannelClickListener;
import com.mackenzie.documentalia03.Models.ChannelList;
import com.mackenzie.documentalia03.Models.Channels;
import com.mackenzie.documentalia03.Models.Urls;
import com.mackenzie.documentalia03.R;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChannelsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Channels> channels;
    private List<Urls> urlsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ChannelClickListener channelClickListener;

    public ChannelsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelsFragment newInstance(String param1, String param2) {
        ChannelsFragment fragment = new ChannelsFragment();
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
        View view = inflater.inflate(R.layout.fragment_channels, container, false);

        // Intanciamos la lista y la rellenamos
        channels = MainActivity.getAllChannels();

        // Rellenamos la lista de urls aqui
        // urlsList = MainActivity.getAllUrls();

        // traemos el recycler y el layout de la interfaz con un castin
        mRecyclerView = view.findViewById(R.id.recyclerView);
        // creamos e inicializamos un objeto de tipo GLManager y los guardamos
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        // TODO averiguar que hace esto y por que no funciona el onitemclick
        // TODO ARREGLAR O ELIMINAR ESTO
        mAdapter = new ChannelsAdapter(channels, R.layout.recycler_view_item, channelClickListener);
        // channelAdapter = new ChannelsAdapter2(getContext(), listener);

        /*mAdapter = new ChannelsAdapter(channels, urlsList, R.layout.recycler_view_item, new OnChannelsClickListener() {
            @Override
            public void onItemClick(Channels channels, Urls urls , int position) {
                Log.w("PELIGRO", "Mensaje de testttt");
                // TODO averiguar como usar esto
            }
        });*/
        // TODO Aqui se pueden hacer los cambios requeridos
        /*mAdapter = new ChannelsAdapter(channels, R.layout.recycler_view_item, new OnChannelsClickListener() {
            @Override
            public void onItemClick(Channels channels, int position) {
                toast = Toast.makeText(getActivity().getBaseContext(), "Pulsaste " + channels + " - " + position, Toast.LENGTH_SHORT);
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

}