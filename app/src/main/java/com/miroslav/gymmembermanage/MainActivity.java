package com.miroslav.gymmembermanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ClientReaderDbHelper clientReaderDbHelper;

    @Override
    protected void onDestroy() {
        clientReaderDbHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // reading from dataBase
        clientReaderDbHelper = new ClientReaderDbHelper(getApplicationContext());





//        insertData(clientReaderDbHelper,"choco");
        readData(clientReaderDbHelper);
//        deleteData(clientReaderDbHelper);
//        dataRowUpdate(clientReaderDbHelper);
    }

    private void dataRowUpdate(ClientReaderDbHelper clientReaderDbHelper) {
        SQLiteDatabase db = clientReaderDbHelper.getWritableDatabase();

        //new value to be updated
        String newName="Alex";
        ContentValues values = new ContentValues();
        values.put(ClientContract.ClientEntry.COLUMN_NAME_CLIENT_NAME,newName);


        String selection = ClientContract.ClientEntry.COLUMN_NAME_CLIENT_NAME+" = ?";
        String [] selectionArg={"Ivan"};

        int countUpdates = db.update(
                ClientContract.ClientEntry.TABLE_NAME,
                values,
                selection ,
                selectionArg
        );
        Log.d("DataBase","CountUpdates: "+ countUpdates);

        db.close();
    }

    private void deleteData(ClientReaderDbHelper clientReaderDbHelper) {
        SQLiteDatabase db = clientReaderDbHelper.getWritableDatabase();
        String selection = ClientContract.ClientEntry.COLUMN_NAME_CLIENT_ID+ " LIKE ?";
        String [] selectionArg = {"2"};

        int deletedRows =db.delete(ClientContract.ClientEntry.TABLE_NAME,selection , selectionArg);
        db.close();
        Log.d("DataBase","Deleted "+ deletedRows);
    }

    private void readData(ClientReaderDbHelper crdbh) {
        SQLiteDatabase db = crdbh.getReadableDatabase();

        //define a projection that specifies which column from database
        // you will actually select use after this query
        String [] projection={

                ClientContract.ClientEntry.COLUMN_NAME_CLIENT_ID,//BaseColumns._ID,
                ClientContract.ClientEntry.COLUMN_NAME_CLIENT_NAME,

        };

        //Filter results where "id" = 1
        String selection = ClientContract.ClientEntry.COLUMN_NAME_CLIENT_ID+ "= ? or client_id = ?";
        String [] selectionArgs = {"1","2"};

        //how do you want the result to be sorted
        String sortOrder = ClientContract.ClientEntry.COLUMN_NAME_CLIENT_ID+" DESC";

//        Cursor cursor = db.query(
//                ClientContract.ClientEntry.TABLE_NAME,
//                projection,
//                selection,
//                selectionArgs,
//                null,null,
//                sortOrder
//                );
        Cursor cursor = db.rawQuery("select * from client;",new String[]{});

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()){
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(ClientContract.ClientEntry.COLUMN_NAME_CLIENT_ID)
            );
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(ClientContract.ClientEntry.COLUMN_NAME_CLIENT_NAME)
            );

            itemIds.add(name);
            itemIds.add(itemId);

        }
        Integer cursorPosition = cursor.getPosition();
        if(cursorPosition==0){
            Log.d("DataBase","Cursor position is: "+cursorPosition+" mean no items!!!!");
        }else{
            Log.d("DataBase","Cursor position is: "+cursorPosition);
        }
        cursor.close();
        db.close();

        for(int i=0;i<itemIds.size();i++){
            Log.d("DataBaseItems",itemIds.get(i).toString());
        }


    }

    private void insertData(ClientReaderDbHelper crdbh,String clientName) {
        // inserting data in dataBase
        //get the data repository in write mode
        SQLiteDatabase db = crdbh.getWritableDatabase();

        // create a new map of values , where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ClientContract.ClientEntry.COLUMN_NAME_CLIENT_NAME,clientName);


        long newRowId = db.insert(ClientContract.ClientEntry.TABLE_NAME,null,values);
        if(newRowId!=-1){
            Log.d("DataBase","inserted rowId= "+ newRowId);
        }else {
            Log.d("DataBase","insertion error = " +newRowId);
        }
        db.close();
    }

}

















































