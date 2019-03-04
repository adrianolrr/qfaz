package br.com.qfaz.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.LocaleList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalViewHolder> implements ListAdapter {
    private final Context context;
    private List<Local> localList;



    public LocalAdapter(Context context, List<Local> localList) {
        this.context = context;
        this.localList = localList;
    }

    @Override
    public LocalAdapter.LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_locais, null);
        return new LocalAdapter.LocalViewHolder(view);
    }




    @Override
    public void onBindViewHolder(LocalAdapter.LocalViewHolder holder, int position) {
        Local local = localList.get(position);
        //binding the data with the viewholder views
        holder.txtapelido.setText("Apelido:" + " " + local.getRazao());
        holder.txtrazao.setText("Razao:" + " " + local.getRazao());
        holder.txtendereco.setText("Endereco:" + " R$ " + local.getEndereco());
        holder.txtcidade.setText("Cidade:" + " " + local.getCidade());


    }

    @Override
    public int getItemCount() {
        return localList.size();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    static class LocalViewHolder extends RecyclerView.ViewHolder {

        TextView txtapelido, txtrazao, txtcidade, txtendereco;


        public LocalViewHolder(View itemView) {
            super(itemView);

            txtapelido =  itemView.findViewById(R.id.textViewApelido);
            txtrazao = itemView.findViewById(R.id.textViewRazao);
            txtcidade =  itemView.findViewById(R.id.textViewCidade);
            txtendereco =  itemView.findViewById(R.id.textViewEndereco);
        }
    }
}
