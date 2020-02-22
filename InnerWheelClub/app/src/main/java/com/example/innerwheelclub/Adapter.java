package com.example.innerwheelclub;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.my_viewholder> {

    Context ncontext;
    List<item> ndata;


    public Adapter(Context ncontext, List<item> ndata) {
        this.ncontext = ncontext;
        this.ndata = ndata;
    }

    @NonNull
    @Override
    public my_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater= LayoutInflater.from(ncontext);
        View v =inflater.inflate(R.layout.cards_layout,parent,false);
        return new my_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull my_viewholder holder, int position)
    {
        holder.background_img.setImageResource(ndata.get(position).getBackground());
        holder.title_text.setText(ndata.get(position).getTitle());
        final String title=ndata.get(position).getTitle();
        holder.det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ncontext, cards.class);
                intent.putExtra("title",title);
                Log.e("Clicked",title);
                ncontext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return ndata.size();
    }

    public class my_viewholder extends RecyclerView.ViewHolder
    {

        ImageView background_img;
        TextView title_text;
        Button det;

        public my_viewholder(@NonNull View itemView) {
            super(itemView);

            background_img=itemView.findViewById(R.id.card_background);
            title_text=itemView.findViewById(R.id.card_title);
            det=itemView.findViewById(R.id.Detail_btn);
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
