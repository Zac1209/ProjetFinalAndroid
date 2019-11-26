package ca.qc.cgodin.projetfinalandroid.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import ca.qc.cgodin.projetfinalandroid.Compte;
import ca.qc.cgodin.projetfinalandroid.Grades;
import ca.qc.cgodin.projetfinalandroid.MaClasseLogin;
import ca.qc.cgodin.projetfinalandroid.MainActivity;
import ca.qc.cgodin.projetfinalandroid.R;

public class CompteAdapterPassageGrade extends RecyclerView.Adapter<CompteAdapterPassageGrade.RestoViewHolder> {

    public ArrayList<Compte> compteList = new ArrayList<>();

    public CompteAdapterPassageGrade(ArrayList<Compte> compteList, Context context) {
        this.compteList = compteList;
    }

    @Override
    public RestoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_passage_grade,parent,false);
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

    public class RestoViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected TextView userID;
        protected Button btnPasser;
        protected Button btnEchec;

        public RestoViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.avatar);
            userID= (TextView) itemView.findViewById(R.id.userID);
            btnPasser = itemView.findViewById(R.id.btnPasser);
            btnEchec = itemView.findViewById(R.id.btnEchec);

            btnPasser.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Grades.passer(userID.getText().toString());
                }
            });

            btnEchec.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Grades.echec(userID.getText().toString());
                }
            });
        }

    }







}