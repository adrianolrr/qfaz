package br.com.qfaz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;
import br.com.qfaz.domain.model.Visita;

public class VisitaAdapter extends RecyclerView.Adapter<VisitaAdapter.VisitaViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the visitas in a list
    private List<Visita> visitaList;

    private ArrayList<Visita> arraylist;

    //getting the context and visita list with constructor
    public VisitaAdapter(Context mCtx, List<Visita> visitaList) {
        this.mCtx = mCtx;
        this.visitaList = visitaList;
        this.arraylist = new ArrayList<Visita>();
        this.arraylist.addAll(visitaList);
    }

    @Override
    public VisitaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_visitas, null);
        return new VisitaViewHolder(view);
    }



    @Override
    public void onBindViewHolder(VisitaViewHolder holder, int position) {
        //getting the visita of the specified position
        Visita visita = visitaList.get(position);

        //binding the data with the viewholder views
        //holder.textViewLocal.setText(visita.getLocal().getNome());
        holder.textViewLocal.setText("Teste");
        holder.textViewHorario.setText(visita.getHorario());
        holder.textViewData.setText(visita.getData());
    }

    @Override
    public int getItemCount() {
        return visitaList.size();
    }


    static class VisitaViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLocal, textViewData, textViewHorario;

        public VisitaViewHolder(View itemView) {
            super(itemView);

            textViewLocal = itemView.findViewById(R.id.textViewLocal);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewHorario = itemView.findViewById(R.id.textViewHorario);
        }
    }

    public void filter(String text) {
        text = text.toUpperCase(Locale.getDefault());
        visitaList.clear();
        if (text.length() == 0) {
            visitaList.addAll(arraylist);
        } else {
            for (Visita wp : arraylist) {
                if (wp.getLocal().getNome().toUpperCase(Locale.getDefault()).contains(text)) {
                    visitaList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
