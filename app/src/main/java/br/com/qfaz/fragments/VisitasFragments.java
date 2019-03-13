package br.com.qfaz.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.adapters.ListVisitaAdapter;
import br.com.qfaz.adapters.VisitaAdapter;
import br.com.qfaz.domain.model.Visita;



public class VisitasFragments extends Fragment implements SearchView.OnQueryTextListener {

    int position = 0;
    TextView txtValor, txtData, txtApelido, txtRamo;
    String local;
    private List<Visita> localList;

    ArrayAdapter<Visita> listaVisitaAdapter;

    String[] arrayFecha;

    ListView listViewVisitas;
    ListFragment listFragmentVisitas;

    ListVisitaAdapter adaptervisitas;

    String visitaJson;
    VisitaAdapter visitaAdapter;
    List<Visita> visitaList;

    private RecyclerView mRecyclerView;
    private List<Visita> mModels;
    List<Visita> listVisita;
    ListView listviewvisitas;
    private SearchView editsearch;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                //local = getArguments().getString("visitas");
                //visitaJson = getArguments().getString("visitasJson");
            }
        }


        //initializing the visitalist
        visitaList = new ArrayList<>();

        visitaList.add(
                new Visita(
                        "1",
                        null,
                        "05/02/2019",
                        "22:15",
                        ""));

        visitaList.add(
                new Visita(
                        "2",
                        null,
                        "05/02/2019",
                        "22:15",
                        ""));


        visitaList.add(
                new Visita(
                        "3",
                        null,
                        "05/02/2019",
                        "22:15",
                        ""));

        ObjectMapper mapper = new ObjectMapper();
        //List<Visita> listVisita = null;
       /* try {
            listVisita = mapper.readValue(visitaJson, new TypeReference<List<Visita>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //adaptervisitas = new ArrayAdapter<String>(getContext(),  android.R.layout.simple_list_item_1, listVisita);
        visitaAdapter = new VisitaAdapter(getContext(), visitaList);
        adaptervisitas = new ListVisitaAdapter(getContext(), visitaList);
        updateView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_visitas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listviewvisitas = view.findViewById(R.id.lvItemsVisita);
        listviewvisitas.setAdapter(adaptervisitas);
        editsearch = view.findViewById(R.id.editsearch);
        editsearch.setOnQueryTextListener(this);

    }

    // Activity is calling this to update view on Fragment
    public void updateView(){
        adaptervisitas.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        visitaAdapter.filter(text);
        return false;
    }


    private ArrayList<Visita> populateList(){

        ArrayList<Visita> list = new ArrayList<>();

        for(int i = 0; i < listVisita.size(); i++){
            Visita imageModel = new Visita();
            imageModel.setUid(listVisita.get(i).getUid());
            list.add(imageModel);
        }

        return list;
    }


}
