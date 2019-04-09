package com.example.digitalagenda;


import java.util.ArrayList;

public class Data {


    private static ArrayList<Contact> Contacts = new ArrayList<>();

    public static void ClearCotacts(){Contacts.clear();}

    public static void Save(Contact c){ Contacts.add(c);}

    public static ArrayList<Contact> Get(){return Contacts;}



}
