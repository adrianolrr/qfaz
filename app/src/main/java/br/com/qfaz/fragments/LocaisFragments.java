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
import br.com.qfaz.adapters.ListLocalAdapter;
import br.com.qfaz.adapters.LocalAdapter;
import br.com.qfaz.domain.model.Local;



public class LocaisFragments extends Fragment implements SearchView.OnQueryTextListener {

    int position = 0;
    TextView txtValor, txtData, txtApelido, txtRamo;
    String local;
    private List<Local> localList;

    ArrayAdapter<Local> listaLocalAdapter;

    String[] arrayFecha;

    ListView listViewLocals;
    ListFragment listFragmentLocals;

    ListLocalAdapter adaptervisitas;

    String locaisJson;
    LocalAdapter localAdapter;

    private RecyclerView mRecyclerView;
    private List<Local> mModels;
    List<Local> listLocal;
    ListView listviewlocals;
    private SearchView editsearch;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                local = getArguments().getString("locais");
                locaisJson = getArguments().getString("locaisJson");
            }
        }


        ObjectMapper mapper = new ObjectMapper();
        //List<Local> listLocal = null;
        try {
            listLocal = mapper.readValue(locaisJson, new TypeReference<List<Local>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        //adapterlocals = new ArrayAdapter<String>(getContext(),  android.R.layout.simple_list_item_1, listLocal);
        localAdapter = new LocalAdapter(getContext(), listLocal);
        adaptervisitas = new ListLocalAdapter(getContext(), listLocal);
        updateView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_locais, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listviewlocals = view.findViewById(R.id.lvItemsLocal);
        listviewlocals.setAdapter(adaptervisitas);
        editsearch = view.findViewById(R.id.editsearch);
        editsearch.setOnQueryTextListener(this);

        listviewlocals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String item = listviewlocals.getItemAtPosition(position).toString();

                Local local = listLocal.get(position);
                Toast.makeText(getContext(), local.toString(), Toast.LENGTH_LONG).show();


                AgendamentoVisitaFragments agendamentoFragments = new AgendamentoVisitaFragments();
                Bundle args = new Bundle();
                args.putString("nome", local.getNome());
                agendamentoFragments.setArguments(args);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayoutLocal, agendamentoFragments) // replace flContainer
                        .addToBackStack(null)
                        .commit();

            }
        });
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
        localAdapter.filter(text);
        return false;
    }


    private ArrayList<Local> populateList(){

        ArrayList<Local> list = new ArrayList<>();

        for(int i = 0; i < listLocal.size(); i++){
            Local imageModel = new Local();
            imageModel.setNome(listLocal.get(i).getNome());
            list.add(imageModel);
        }

        return list;
    }


}
