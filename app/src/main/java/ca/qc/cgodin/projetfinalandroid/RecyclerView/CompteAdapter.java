package ca.qc.cgodin.projetfinalandroid.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public CompteAdapter(ArrayList<Compte> compteList, Context context) {
        this.compteList = compteList;
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


    }

    @Override
    public int getItemCount() {
        return compteList.size();
    }

    public static class RestoViewHolder extends RecyclerView.ViewHolder{

        protected ImageView image;
        protected TextView userID;


        public RestoViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.avatar);
            userID= (TextView) itemView.findViewById(R.id.userID);
        }
    }

}