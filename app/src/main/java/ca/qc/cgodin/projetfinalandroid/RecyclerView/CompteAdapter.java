package ca.qc.cgodin.projetfinalandroid.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.cgodin.projetfinalandroid.Compte;
import ca.qc.cgodin.projetfinalandroid.R;

public class CompteAdapter extends RecyclerView.Adapter<CompteAdapter.RestoViewHolder> {

    public ArrayList<Compte> compteList = new ArrayList<>();
    int selected_position = 0;
    boolean binAfficherHighLight;
    static public String userSelected = "";
    public CompteAdapter(ArrayList<Compte> compteList, Context context,boolean binAfficherHighLight) {
        this.compteList = compteList;
        this.binAfficherHighLight = binAfficherHighLight;
    }

    @Override
    public RestoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        RestoViewHolder viewHolder=new RestoViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestoViewHolder holder, int position) {
        Compte compte = compteList.get(position);

        byte[] decodedString1 = Base64.decode(compte.getAvatar(), Base64.DEFAULT);
        Bitmap decodedByte1 = BitmapFactory.decodeByteArray(decodedString1, 0,decodedString1.length);
        holder.image.setImageBitmap(decodedByte1);
        holder.userID.setText(compte.getName());

        Compte item = compteList.get(position);

        // Here I am just highlighting the background
        if(selected_position == position)
            userSelected = compte.getName();
        if(binAfficherHighLight)
            holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);

    }

    @Override
    public int getItemCount() {
        return compteList.size();
    }

    public class RestoViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        protected ImageView image;
        protected TextView userID;


        public RestoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            image= (ImageView) itemView.findViewById(R.id.avatar);
            userID= (TextView) itemView.findViewById(R.id.userID);
        }
        @Override
        public void onClick(View v) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if(binAfficherHighLight){
                if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
                Compte compte = compteList.get(getAdapterPosition());
                userSelected = compte.getName();
                // Updating old as well as new positions
                notifyItemChanged(selected_position);
                selected_position = getAdapterPosition();
                notifyItemChanged(selected_position);
            }


            // Do your another stuff for your onClick
        }
    }







}