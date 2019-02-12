package br.com.qfaz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Ticket;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Ticket> ticketList;

    //getting the context and product list with constructor
    public TicketAdapter(Context mCtx, List<Ticket> ticketList) {
        this.mCtx = mCtx;
        this.ticketList = ticketList;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_tickets, null);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        //getting the product of the specified position
        Ticket product = ticketList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getSegmento());
        holder.textViewShortDesc.setText(product.getDescricao());
        holder.textViewPrice.setText(String.valueOf(product.getValor()));
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getTicket()));

    }


    @Override
    public int getItemCount() {
        return ticketList.size();
    }


    class TicketViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public TicketViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
