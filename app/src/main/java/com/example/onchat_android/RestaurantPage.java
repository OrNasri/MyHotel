package com.example.onchat_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RestaurantPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_page);

        Button buttonBack = findViewById(R.id.backToMenuRestaurant);
        buttonBack.setOnClickListener(v -> {
            Intent i = new Intent(this, MenuPage.class);
            startActivity(i);
        });
        FloatingActionButton Card = findViewById(R.id.bag);
        Card.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingBag.class);
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
        restaurantMenu.add("Crispy");
        restaurantMenu.add("Pad C");
        restaurantMenu.add("Pad Thai");

        ListView lvProgram = findViewById(R.id.lvProgramRestaurant);

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

        RestaurantAdapter adapter = new RestaurantAdapter(this, dishes,
                description, images);

        lvProgram.setAdapter(adapter);

        Button addToCard = findViewById(R.id.addCard);
        addToCard.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingBag.class);
            startActivity(i);
        });

    }
}
