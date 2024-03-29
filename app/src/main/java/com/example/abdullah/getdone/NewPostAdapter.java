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

public class NewPostAdapter extends RecyclerView.Adapter<NewPostAdapter.ProductViewHolder> {

    private String status = null;
    private Context mCtx;
    private List<Get_post> productList;

    public NewPostAdapter(Context mCtx, List<Get_post> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listview_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Get_post product = productList.get(position);

        //loading the image
String value1 = "1";

        holder.textViewTitle.setText(product.getName());
        holder.textViewlocation.setText(product.getLocation());

        if(product.getStatus().equals("1"))
        {
            status="Open";
        }
        else  if(product.getStatus().equals("2"))
        {
            status="Assigned";
        }

        holder.textViewbudget.setText("Rs." +String.valueOf(product.getBudget()));

       holder.btn_status.setText(status);
       holder.btn_status.setOnClickListener(new View.OnClickListener(){//Calling on click listener for Add Button

                                                public void onClick( View v)//calling the onClick function
                                                {
                                                    Intent intent = new Intent(v.getContext(),activity_dtl_task_offer.class);

                                                    intent.putExtra("id",String.valueOf(product.getId()));
                                                    intent.putExtra("Name",String.valueOf(product.getName()));
                                                    intent.putExtra("Desc",String.valueOf(product.getDescription()));

                                                    intent.putExtra("Last Date",String.valueOf(product.getLastDate()));
                                                    intent.putExtra("Date",String.valueOf(product.getDate()));
                                                    intent.putExtra("Status",String.valueOf(status));
                                                    intent.putExtra("Location",String.valueOf(product.getLocation()));
                                                    intent.putExtra("Type",String.valueOf(product.getType()));
                                                    intent.putExtra("Type of Task",String.valueOf(product.getType_of_task()));
                                                    intent.putExtra("Budget",String.valueOf(product.getBudget()));
                                                    intent.putExtra("No Of Persons",String.valueOf(product.getNo_of_persons()));

                                                    v.getContext().startActivity(intent);




                                                }

                                            }
       );






    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewlocation,  textViewbudget;
        Button btn_status;


        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.gig_title);
            textViewlocation = itemView.findViewById(R.id.gig_location);
            textViewbudget = itemView.findViewById(R.id.prc);

            btn_status = itemView.findViewById(R.id.btn_status);
        }
    }
}