package com.example.abdullah.getdone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<get_offer> productList;

    public OfferAdapter(Context mCtx, List<get_offer> productList) {
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
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final get_offer product = productList.get(position);

        //loading the image
String value1 = "1";

        holder.txtname.setText(product.getName());
        holder.txtprice.setText(product.getAmount());









    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtname, txtprice;

        public ProductViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.Name);
            txtprice = itemView.findViewById(R.id.prc);

        }
    }
}