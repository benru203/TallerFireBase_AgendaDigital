package com.example.digitalagenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactDetail extends AppCompatActivity {

    private TextView Name, CellPhone, Phone;
    private Intent In;
    private ArrayList<Contact> Contacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_detail);

        In = getIntent();
        Contacts = Data.Get();
        int Position = In.getIntExtra("position",0);

        Name = (TextView)findViewById(R.id.TxtContactName);
        CellPhone = (TextView)findViewById(R.id.TxtCellPhone);
        Phone = (TextView)findViewById(R.id.TxtPhone);

        LoadData(Contacts.get(Position));
    }

    private void LoadData(Contact Contact){
        Name.setText(Contact.getName()+ " " + Contact.getLastName());
        CellPhone.setText(Contact.getCellPhone());
        Phone.setText(Contact.getPhone());
    }
}
