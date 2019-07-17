package com.example.abdullah.getdone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOffersAdapter extends RecyclerView.Adapter<MyOffersAdapter.ProductViewHolder> {


    private Context mCtx;
    private  String status=null, gig_id=null;
    AlertDialog.Builder builder;
    private List<Get_offer> productList;

    private String URL_REGIST = UserDetails.Url+ "assign_task.php";


    public MyOffersAdapter(Context mCtx, List<Get_offer> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.offer_layout_abd, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final Get_offer product = productList.get(position);

        //loading the image
        builder = new AlertDialog.Builder(mCtx);

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
        holder.btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status = "2";
                gig_id = String.valueOf(product.getId());
                status_update(status,gig_id);



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
        public static Button btn_status;





        public ProductViewHolder(View itemView) {
            super(itemView);

            txtname = itemView.findViewById(R.id.Name);
            txtamount = itemView.findViewById(R.id.prc);
            btna=itemView.findViewById(R.id.cht);
            btn_status=itemView.findViewById(R.id.status);



        }
    }
    private void status_update(final String status,final String gig_id) {
        int value=1;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Details");
                        builder.setMessage("Assigned Successfully");
                        builder.setPositiveButton("User SignUp Successfully", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mCtx, "Updation Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("status",status);
                params.put("gig_id",gig_id);


                return params;
            }
        };
        MySingleton.getInstance(mCtx).addTorequestquee(stringRequest);
    }
}