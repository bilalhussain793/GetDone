package com.example.abdullah.getdone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Get_offer> productList;

    public OffersAdapter(Context mCtx, List<Get_offer> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.offer_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final Get_offer product = productList.get(position);

        //loading the image


        holder.txtname.setText(product.getbuyer_name());
        holder.txtamount.setText("Rs."+product.getamount());
        holder.btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mCtx, product.getbuyer_name(), Toast.LENGTH_SHORT).show();
                UserDetails.chatWith=product.getbuyer_name();
                mCtx.startActivity(new Intent(mCtx,Chat.class));

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtname, txtamount;
        public static Button btna;


        public ProductViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.Name);
            txtamount = itemView.findViewById(R.id.prc);
            btna=itemView.findViewById(R.id.cht);


        }
    }
}