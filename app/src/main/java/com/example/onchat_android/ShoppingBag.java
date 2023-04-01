package com.example.onchat_android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBag extends AppCompatActivity {
    ShoppingAdapter shoppingAdapter;
    ArrayList<String> bagList;
    int flag = 0;
    int sum = 0;
    public void deleteFromShoppingList() {
        shoppingAdapter = new ShoppingAdapter(this, bagList);

        ListView lvProgramShopping = findViewById(R.id.lvProgramInShopping);
        lvProgramShopping.setAdapter(shoppingAdapter);
        lvProgramShopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent i = new Intent(ShoppingBag.this, ShoppingBag.class);
                bagList.remove(position);
                getSum();
                //bag.add(lst.get(position).title_price);
//                i.putStringArrayListExtra("bag", (ArrayList<String>) bag);
                //  Intent chat = new Intent(ContactPage.this, chatScreen.class);
                //chat.putExtra("contactInfo", contactsIdList.get(position));
                // chat.putExtra("userName", current);
                // getMessages(current, contactsIdList.get(position), chat);
               // i.putStringArrayListExtra("bag", (ArrayList<String>) bagList);
                //startActivity(i);
                onResume();
            }
        });

        lvProgramShopping.setAdapter(shoppingAdapter);

    }


    public int getPrice(String str){
        StringBuffer num = new StringBuffer();
        for (int i=0; i<str.length(); i++)
        {
            if (Character.isDigit(str.charAt(i)))
                num.append(str.charAt(i));
        }
        String strPrice  = num.toString();
        int price = Integer.parseInt(strPrice);
        return price;
    }

    public void getSum(){
        sum = 0;
        for (int i=0; i<bagList.size(); i++){
            sum += getPrice(bagList.get(i));
        }
        String str = String.format("Order sum is - %d$", sum);
        TextView text = (TextView) findViewById(R.id.textView26);
        text.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        bagList = getIntent().getStringArrayListExtra("bag");

        setContentView(R.layout.activity_shopping_bag);

        FloatingActionButton btnMenu = findViewById(R.id.menu);
        btnMenu.setOnClickListener(v -> {
            Intent i = new Intent(this, RestaurantPage.class);
            startActivity(i);
        });

        getSum();

        Button btnFin = findViewById(R.id.fin);
        btnFin.setOnClickListener(v -> {
            if (bagList.size() > 0){
                bagList.clear();
                Intent i = new Intent(this, MenuPage.class);
                AlertDialog alertDialog = new AlertDialog.Builder(ShoppingBag.this).create();
                alertDialog.setTitle("Your order is complete.");
                alertDialog.setMessage("We start working on it!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(i);
                            }
                        });
                alertDialog.show();
            }
            else {
                Intent i = new Intent(this, RestaurantPage.class);
                AlertDialog alertDialogError = new AlertDialog.Builder(ShoppingBag.this).create();
                alertDialogError.setTitle("Your bag is empty");
                alertDialogError.setMessage("Add at last one product.");
                alertDialogError.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                startActivity(i);
                            }
                        });
                alertDialogError.show();
            }
        });


        shoppingAdapter = new ShoppingAdapter(this, bagList);
        ListView lvProgramShopping = findViewById(R.id.lvProgramInShopping);
//        lvProgramShopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Intent chat = new Intent(ContactPage.this, chatScreen.class);
//                chat.putExtra("contactInfo", contactsIdList.get(position));
//                chat.putExtra("userName", current);
//                getMessages(current, contactsIdList.get(position), chat);
//
//            }
//        });

        lvProgramShopping.setAdapter(shoppingAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        deleteFromShoppingList();
    }
}