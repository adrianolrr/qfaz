package br.com.qfaz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Usuario;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the usuarios in a list
    private List<Usuario> usuarioList;

    //getting the context and usuario list with constructor
    public UsuarioAdapter(Context mCtx, List<Usuario> usuarioList) {
        this.mCtx = mCtx;
        this.usuarioList = usuarioList;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_perfis, null);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        //getting the usuario of the specified position
        Usuario usuario = usuarioList.get(position);

        //binding the data with the viewholder views
        //holder.textViewLocal.setText(usuario.getLocal());
        //holder.textViewHorario.setText(usuario.getHorario());
        //holder.textViewData.setText(usuario.getData());


    }


    @Override
    public int getItemCount() {
        return usuarioList.size();
    }


    class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLocal, textViewHorario, textViewData;

        public UsuarioViewHolder(View itemView) {
            super(itemView);

            textViewLocal = itemView.findViewById(R.id.textViewLocal);
            textViewHorario = itemView.findViewById(R.id.textViewHorario);
            textViewData = itemView.findViewById(R.id.textViewData);
        }
    }
}