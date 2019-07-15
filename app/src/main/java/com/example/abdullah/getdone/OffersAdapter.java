package com.example.abdullah.getdone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



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
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Get_offer product = productList.get(position);

        //loading the image


        holder.txtname.setText(product.getbuyer_name());
        holder.txtamount.setText("Rs."+product.getamount());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView txtname, txtamount;


        public ProductViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.Name);
            txtamount = itemView.findViewById(R.id.prc);

        }
    }
}