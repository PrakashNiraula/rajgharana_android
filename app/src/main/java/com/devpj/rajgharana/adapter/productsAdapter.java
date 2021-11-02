package com.devpj.rajgharana.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devpj.rajgharana.R;
import com.devpj.rajgharana.module.Products;

import java.util.List;

import retrofit2.Callback;

public class productsAdapter extends RecyclerView.Adapter<productsAdapter.MyviewHolder> {
    List<Products> allproducts;
    onaddTobillListener onaddTobillListener;
    Context context;


    public productsAdapter(List<Products> myproducts, onaddTobillListener onaddTobillListener, Context context) {
        allproducts=myproducts;
        this.onaddTobillListener=onaddTobillListener;
        this.context=context;
    }



    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcomponent, parent, false);
       // MyviewHolder myviewHolder = new MyviewHolder(view, productListener, onAddtocartListener);
         MyviewHolder myviewHolder = new MyviewHolder(view,onaddTobillListener);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        Products p = allproducts.get(position);
        holder.name.setText(p.getName());
        holder.rate.setText(p.getPrice()+"");
        holder.pid.setText(p.getId()+"");


    }

    @Override
    public int getItemCount() {
        return allproducts.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name,rate,pid;
        EditText quantity;
        ImageButton save;
        onaddTobillListener onaddTobillListener;

        public MyviewHolder(@NonNull View itemView,onaddTobillListener onaddTobillListener) {
            super(itemView);
            name=itemView.findViewById(R.id.txtname);
            quantity=itemView.findViewById(R.id.txtquantity);
            save=itemView.findViewById(R.id.savebutton);
            rate=itemView.findViewById(R.id.labelrate);
            pid=itemView.findViewById(R.id.pid);
           save.setOnClickListener(this);
           this.onaddTobillListener=onaddTobillListener;


        }

        @Override
        public void onClick(View v) {
            if(quantity.getText().toString().equals("")){
                Toast.makeText(context, "Quantity is required", Toast.LENGTH_SHORT).show();
                return;
            }
            onaddTobillListener.onAddtobillClick(getAdapterPosition(),Integer.parseInt(quantity.getText().toString()),pid.getText().toString(),rate.getText().toString());
        }
    }

    public interface onaddTobillListener {
         void onAddtobillClick(int position,int quantity,String pid,String rate);
    }

    public void filterList(List<Products> newlist){
        allproducts=newlist;
        notifyDataSetChanged();
    }

}
