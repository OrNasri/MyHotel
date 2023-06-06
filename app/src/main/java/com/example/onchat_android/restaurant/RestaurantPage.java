package com.example.onchat_android.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.onchat_android.MenuPage;
import com.example.onchat_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPage extends AppCompatActivity {
    private List<String> bag;
    private RestaurantAdapter adapter;
    private List<Product> lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_resturant_page);
        bag = getIntent().getStringArrayListExtra("bagFromShopBag");
        if (bag == null){
            bag = new ArrayList<>();
        }

        FloatingActionButton Card = findViewById(R.id.bagShop);
        Card.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingBag.class);
            i.putStringArrayListExtra("bag", (ArrayList<String>) bag);
            startActivity(i);
        });

        FloatingActionButton buttonBack = findViewById(R.id.backToMenuRestaurant);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            i.putStringArrayListExtra("bagFromShopBag", (ArrayList<String>) bag);
            startActivity(i);
        });

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.a);
        images.add(R.drawable.b);
        images.add(R.drawable.c);
        images.add(R.drawable.d);
        images.add(R.drawable.e);
        images.add(R.drawable.f);
        images.add(R.drawable.g);
        images.add(R.drawable.h);
        images.add(R.drawable.i);
        images.add(R.drawable.j);
        images.add(R.drawable.k);
        images.add(R.drawable.l);
        images.add(0);

        fetchDataFromFirebase(new FirebaseDataCallback() {
            @Override
            public void onDataReceived(List<String> data) {
                List<String> dbInfo = new ArrayList<>();
                List<String> restaurantMenu = new ArrayList<>();
                List<String> dishes = new ArrayList<>();
                List<String> description = new ArrayList<>();

                // Handle the retrieved data here
                for (String value : data) {
                    dbInfo.add(value);
                }
//                separate data from db
                restaurantMenu.clear();
                dishes.clear();
                description.clear();
                for (String input : dbInfo) {
                    String[] parts = input.split("#",5);
                    restaurantMenu.add(parts[0]);
                    dishes.add(parts[1]);
                    description.add(parts[2]);
                }
                restaurantMenu.add(" ");
                dishes.add(" ");
                description.add(" ");
                ListView lvProgramRestaurant = findViewById(R.id.lvProgramRestaurant);
                lst = new ArrayList<>();
                for (int i=0; i< images.size(); i++){
                    Product p = new Product(restaurantMenu.get(i), description.get(i), dishes.get(i),
                            images.get(i));
                    lst.add(p);
                }
                adapter = new RestaurantAdapter(RestaurantPage.this, lst);
                lvProgramRestaurant.setAdapter(adapter);
                // Set up the item click listener for the ListView
                addToShoppingList();
            }
        });
    }

    public void addToShoppingList() {
        ListView lvProgramRestaurant = findViewById(R.id.lvProgramRestaurant);
        lvProgramRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(RestaurantPage.this, ShoppingBag.class);
                bag.add(lst.get(position).title_price);
            }
        });
        lvProgramRestaurant.setAdapter(adapter);
    }

    public interface FirebaseDataCallback {
        void onDataReceived(List<String> data);
    }

    public void fetchDataFromFirebase(FirebaseDataCallback callback) {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("description/0");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> dataList = new ArrayList<>();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String value = snapshot.getValue(String.class);
                        dataList.add(value);
                    }
                }
                callback.onDataReceived(dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}
