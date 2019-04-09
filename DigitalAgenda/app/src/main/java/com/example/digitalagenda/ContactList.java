package com.example.digitalagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    private ListView LV;
    private ArrayList<Contact> Contacts;
    private ArrayList<String> ContactNames;
    private Intent In;
    private TextView TxtNoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);

        LV = (ListView)findViewById(R.id.LVContacts);
        TxtNoResults = (TextView)findViewById(R.id.TxtNoResults);
        Contacts = Data.Get();
        ContactNames = new ArrayList<String>();
        TxtNoResults.setVisibility(View.VISIBLE);
        LV.setVisibility(View.INVISIBLE);
        if(Contacts.size() > 0){
            TxtNoResults.setVisibility(View.INVISIBLE);
            LV.setVisibility(View.VISIBLE);
            for (int i = 0; i < Contacts.size(); i++) {
                ContactNames.add(Contacts.get(i).getName() + " " + Contacts.get(i).getLastName());
            }
        }
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ContactNames);
        LV.setAdapter(Adapter);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                In = new Intent(ContactList.this,ContactDetail.class);
                In.putExtra("position",position);
                startActivity(In);
            }
        });
    }
}
