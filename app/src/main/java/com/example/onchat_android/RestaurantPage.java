package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPage extends AppCompatActivity {
    private List<String> bag;
    private RestaurantAdapter adapter;
    private List<Product> lst;

    public void addToShoppingList() {
        adapter = new RestaurantAdapter(this, lst);

        ListView lvProgramRestaurant = findViewById(R.id.lvProgramRestaurant);
        lvProgramRestaurant.setAdapter(adapter);
        lvProgramRestaurant.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(RestaurantPage.this, ShoppingBag.class);
                bag.add(lst.get(position).title_price);
//                i.putStringArrayListExtra("bag", (ArrayList<String>) bag);
                //  Intent chat = new Intent(ContactPage.this, chatScreen.class);
                //chat.putExtra("contactInfo", contactsIdList.get(position));
                // chat.putExtra("userName", current);
                // getMessages(current, contactsIdList.get(position), chat);
//                startActivity(i);
            }
        });

        lvProgramRestaurant.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_resturant_page);

        FloatingActionButton buttonBack = findViewById(R.id.backToMenuRestaurant);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            startActivity(i);
        });



        List<String> restaurantMenu = new ArrayList<>();
        restaurantMenu.add("Redness");
        restaurantMenu.add("springRoll");
        restaurantMenu.add("Chicken");
        restaurantMenu.add("pickles");
        restaurantMenu.add("salad");
        restaurantMenu.add("Ban");
        restaurantMenu.add("Futomaki");
        restaurantMenu.add("sandwich");
        restaurantMenu.add("Sunset");
        restaurantMenu.add("Crispy");
        restaurantMenu.add("Pad C");
        restaurantMenu.add("Pad Thai");
        restaurantMenu.add(" ");

        ListView lvProgramRestaurant = findViewById(R.id.lvProgramRestaurant);

        List<String> dishes = new ArrayList<>();
        dishes.add("Redness - 8$");
        dishes.add("Vegetable spring roll - 10$");
        dishes.add("Chicken gyoza - 10$");
        dishes.add("Japanese pickles - 6$");
        dishes.add("Chicken salad - 7$");
        dishes.add("Ban Schnitzel - 12$");
        dishes.add("Futomaki I/O - 12$");
        dishes.add("Vegetable sandwich - 10$");
        dishes.add("Sunset - 12$");
        dishes.add("Crispy roll - 12$");
        dishes.add("Pad C U Vegetables - 15$");
        dishes.add("Chicken Pad Thai - 15$");
        dishes.add(" ");

        List<String> description = new ArrayList<>();
        description.add("Green soybeans with sea salt and a lemon wedge");
        description.add("2 units filled with cabbage, sprouts, bean noodles and green onion");
        description.add("Five pouches filled with chicken and vegetables. Served with soy sauce and ginger on the side");
        description.add("Cabbage, carrot, cucumber, peppers and radish, garnished with toasted sesame");
        description.add("Chicken and cucumber salad in peanut butter and coconut milk sauce");
        description.add("Schnitzel in a bun, barbecue sauce, spicy mayonnaise, pickle and iceberg lettuce");
        description.add("A thick roll rolled with celery inside and rice outside, coated with tempura chips");
        description.add("4 I/O triangles coated with Tempura chips. Choice of three vegetables");
        description.add("Salmon in tempura, avocado, cucumber and chives topped with sweet potato");
        description.add("Spicy salmon/salmon tempura, avocado and sweet potato, panko and green onion");
        description.add("Rice noodles stir-fried in chili and dark Thai soy with broccoli,green onions and coriander garnish");
        description.add("Rice noodles stir-fried in chili and sweet soy with egg, cabbage and chicken");
        description.add(" ");

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

        lst = new ArrayList<>();
        for (int i=0; i< images.size(); i++){
            Product p = new Product(restaurantMenu.get(i), description.get(i), dishes.get(i),
                    images.get(i));
            lst.add(p);
        }


       adapter = new RestaurantAdapter(this, lst);

        bag = new ArrayList<>();


        lvProgramRestaurant.setAdapter(adapter);

        FloatingActionButton Card = findViewById(R.id.bagShop);
        Card.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingBag.class);
            i.putStringArrayListExtra("bag", (ArrayList<String>) bag);

            startActivity(i);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        addToShoppingList();
    }

}
