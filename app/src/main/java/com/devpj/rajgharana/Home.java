package com.devpj.rajgharana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.devpj.rajgharana.adapter.productsAdapter;
import com.devpj.rajgharana.module.AddToBillResponse;
import com.devpj.rajgharana.module.Bill;
import com.devpj.rajgharana.module.Products;
import com.devpj.rajgharana.retrofit.APIClient;
import com.devpj.rajgharana.retrofit.APIInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    APIInterface apiInterface;
    Spinner spinner;
    ArrayAdapter<String> billsadapter;
    RecyclerView productsrecycler;
    RecyclerView.LayoutManager layoutManager;
    com.devpj.rajgharana.adapter.productsAdapter productsAdapter;
    List<Products> allproducts;
    EditText searchbar;


    HashMap hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        spinner=findViewById(R.id.spinner);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Bill>> getallbills=apiInterface.getallongoingbills();
        productsrecycler=findViewById(R.id.productsarea);
        searchbar=findViewById(R.id.txtsearch);
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Products> myList = new ArrayList<>();
                for(Products object: allproducts){
                    if (object.getName().toLowerCase().contains(s.toString())){

                        myList.add(object);
                    }
                }

                productsAdapter adapterM = new productsAdapter(myList,Home.this::onsaveClick,Home.this);

                productsrecycler.setAdapter(adapterM);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        hashMap=new HashMap();
        getallbills.enqueue(new Callback<List<Bill>>() {
            @Override
            public void onResponse(Call<List<Bill>> call, Response<List<Bill>> response) {
                if(response.isSuccessful()){

                    List<Bill> cats=response.body();
                    List<String> names=new ArrayList<>();
                    for(int i=0;i<cats.size();i++){
                        hashMap.put(cats.get(i).getGuestName(),cats.get(i).getId());
                        names.add(cats.get(i).getGuestName().toString());

                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_spinner_item, names);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);



                }

            }

            @Override
            public void onFailure(Call<List<Bill>> call, Throwable t) {
                Toast.makeText(Home.this,"Something went wrong. Cant connect",Toast.LENGTH_LONG).show();

            }
        });


        Call<List<Products>> getallproducts=apiInterface.getmyproducts();
        getallproducts.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if(response.isSuccessful()){

                    layoutManager=new GridLayoutManager(Home.this,1);
                    productsrecycler.setLayoutManager(layoutManager);
                    //save for on click listener
                    allproducts=response.body();
                    productsAdapter=new productsAdapter(allproducts,Home.this::onsaveClick,Home.this);
                    productsrecycler.setAdapter(productsAdapter);
                    productsrecycler.setHasFixedSize(true);



                }

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(Home.this,"Something went wrong. Try again",Toast.LENGTH_LONG).show();

            }
        });



    }

    private void onsaveClick(int i,int quantity) {
        Products p=allproducts.get(i);
        String productid=p.getId().toString();
        String selected=spinner.getSelectedItem().toString();

       String billid= hashMap.get(selected).toString();


        JSONObject json = new JSONObject();
        try {
            json.put("billid", billid);
            json.put("productid", productid);
            json.put("quantity",quantity+"");
            json.put("rate", p.getPrice()+"");
           // JsonObject jsonObject = (JsonObject) new JsonParser().parse(json);
            JsonParser jsonParser = new JsonParser();
            Call<AddToBillResponse> addtobill=apiInterface.addtobill((JsonObject) jsonParser.parse(json.toString()));
            addtobill.enqueue(new Callback<AddToBillResponse>() {
                @Override
                public void onResponse(Call<AddToBillResponse> call, Response<AddToBillResponse> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(Home.this, "Order Created", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<AddToBillResponse> call, Throwable t) {

                    Toast.makeText(Home.this, "Error saving. Try again", Toast.LENGTH_SHORT).show();
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }




    }
}