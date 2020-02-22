package com.example.innerwheelclub;



import android.content.Context;

import android.content.Intent;

import android.net.Uri;

import android.util.Log;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.Button;

import android.widget.TextView;



import java.util.List;



import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;



public class Adapter_member extends RecyclerView.Adapter<Adapter_member.my_viewholder> {



    Context ncontext;

    List<item_member> ndata;

    String number;



    public Adapter_member(Context ncontext, List<item_member> ndata) {

        this.ncontext = ncontext;

        this.ndata = ndata;

    }



    @NonNull

    @Override

    public my_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)

    {

        LayoutInflater inflater= LayoutInflater.from(ncontext);

        // Log.e(" mmm","layout");

        View v =inflater.inflate(R.layout.member_layout,parent,false);

        return new my_viewholder(v);

    }



    @Override

    public void onBindViewHolder(@NonNull my_viewholder holder, final int position)

    {

        //Log.e("error",ndata.get(position).getPhone());



        holder.n.setText(ndata.get(position).getName());

        holder.p.setText(ndata.get(position).getPhone());

//        Log.e(number,ndata.get(position).getName());

        number=ndata.get(position).getPhone();

        holder.b.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", ndata.get(position).getPhone(), null));

                ncontext.startActivity(intent);



            }

        });

    }



    @Override

    public int getItemCount() {

        // Log.e("size ", String.valueOf(ndata.size()));

        return ndata.size();

    }
    public class my_viewholder extends RecyclerView.ViewHolder

    {
        TextView n,p;
        Button b;
        public my_viewholder(@NonNull View itemView) {
            super(itemView);
            n=itemView.findViewById(R.id.Name_r);
            p=itemView.findViewById(R.id.Phone_r);
            b=itemView.findViewById(R.id.call_mem);
        }
    }



}

