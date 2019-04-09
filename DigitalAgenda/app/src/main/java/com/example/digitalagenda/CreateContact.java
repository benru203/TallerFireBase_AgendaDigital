package com.example.digitalagenda;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.UUID;

public class CreateContact extends AppCompatActivity {

    private EditText Name, LastName, Phone, CellPhone;
    private Resources Resources;
    private ArrayList<Contact> Contacts;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contact);

        Name = (EditText)findViewById(R.id.TxtName);
        LastName = (EditText)findViewById(R.id.TxtLastName);
        Phone = (EditText)findViewById(R.id.TxtPhone);
        CellPhone = (EditText)findViewById(R.id.TxtCellPhone);
        Resources = this.getResources();
        Contacts = Data.Get();
        InitFB();
    }

    private void InitFB(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    public void Save(View view){
        String ID, NameV, LastNameV, PhoneV, CellPhoneV;
        if(Validate()){
            ID = UUID.randomUUID().toString();
            NameV = Name.getText().toString();
            LastNameV = LastName.getText().toString();
            PhoneV = Phone.getText().toString();
            CellPhoneV = CellPhone.getText().toString();
            Contact C = new Contact(ID, NameV, LastNameV, PhoneV, CellPhoneV);
            C.SaveContact();
            databaseReference.child("Contacts").child(ID).setValue(C);
            Toast.makeText(this, R.string.done, Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    private boolean Validate(){
        String NameV, LastNameV, PhoneV, CellPhoneV;
        NameV = Name.getText().toString();
        LastNameV = LastName.getText().toString();
        PhoneV = Phone.getText().toString();
        CellPhoneV = CellPhone.getText().toString();
        if(NameV.equals("")){
            Name.setError(Resources.getString(R.string.required));
            return false;
        }
        if(LastNameV.equals("")){
            LastName.setError(Resources.getString(R.string.required));
            return false;
        }
        if(PhoneV.equals("")){
            Phone.setError(Resources.getString(R.string.required));
            return false;
        }
        if(CellPhoneV.equals("")){
            CellPhone.setError(Resources.getString(R.string.required));
            return false;
        }
        return true;
    }

}
