package br.com.qfaz.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.LocaleList;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalViewHolder>  {
    private Context context;
    private List<Local> localList;

    private ArrayList<Local> arraylist;

    public LocalAdapter(Context context, List<Local> localList) {
        this.context = context;
        this.localList = localList;
        this.arraylist = new ArrayList<Local>();
        this.arraylist.addAll(localList);
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
        final Local local = localList.get(position);
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


    static class LocalViewHolder extends RecyclerView.ViewHolder {

        TextView txtapelido, txtrazao, txtcidade, txtendereco;


        public LocalViewHolder(final View itemView) {
            super(itemView);

            txtapelido =  itemView.findViewById(R.id.textViewApelido);
            txtrazao = itemView.findViewById(R.id.textViewRazao);
            txtcidade =  itemView.findViewById(R.id.textViewCidade);
            txtendereco =  itemView.findViewById(R.id.textViewEndereco);

        }

    }

    public void filter(String text) {
        text = text.toUpperCase(Locale.getDefault());
        localList.clear();
        if (text.length() == 0) {
            localList.addAll(arraylist);
        } else {
            for (Local wp : arraylist) {
                if (wp.getNome().toUpperCase(Locale.getDefault()).contains(text)) {
                    localList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
