package br.com.qfaz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Perfil;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the perfils in a list
    private List<Perfil> perfilList;

    //getting the context and perfil list with constructor
    public PerfilAdapter(Context mCtx, List<Perfil> perfilList) {
        this.mCtx = mCtx;
        this.perfilList = perfilList;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_perfis, null);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerfilViewHolder holder, int position) {
        //getting the perfil of the specified position
        Perfil perfil = perfilList.get(position);

        //binding the data with the viewholder views
        //holder.textViewLocal.setText(perfil.getLocal());
        //holder.textViewHorario.setText(perfil.getHorario());
        //holder.textViewData.setText(perfil.getData());


    }


    @Override
    public int getItemCount() {
        return perfilList.size();
    }


    class PerfilViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLocal, textViewHorario, textViewData;

        public PerfilViewHolder(View itemView) {
            super(itemView);

            textViewLocal = itemView.findViewById(R.id.textViewLocal);
            textViewHorario = itemView.findViewById(R.id.textViewHorario);
            textViewData = itemView.findViewById(R.id.textViewData);
        }
    }
}