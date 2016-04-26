package bgl.common.com.sqliteexmp;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

public class MyDbHandler extends SQLiteOpenHelper  {

    private  final String TAG="MyDbHandler";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="products.db";
    private static final String TABLE_PRODUCT="products";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_PRODUCTNAME="productname";

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG," onCreate table....");
        String query="CREATE TABLE "+TABLE_PRODUCT+"("
                +COLUMN_ID+" INTEGER PRIMARY  KEY AUTOINCREMENT ,"+
                COLUMN_PRODUCTNAME +" TEXT );";
        Log.i(TAG," onCreate table query." +query );
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG," onUpgrade table....");
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_PRODUCT);
        onCreate(db);

    }
    // add new row into table

    public void addProduct(Products products){
       Log.i(TAG," addProduct method --- 1 " +products.get_productName());
       ContentValues values=new ContentValues();
       values.put(COLUMN_PRODUCTNAME,products.get_productName());
       SQLiteDatabase db=getWritableDatabase();
       long id= db.insert(TABLE_PRODUCT,null,values);
       Log.i(TAG," addProduct method ---2 id ::  "+ id);
       db.close();
    }

    // delete new row from  table
    public void deleteProduct(String productName){
        Log.i(TAG," deleteProduct method --- 1 " +productName );

        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_PRODUCT +" WHERE "+COLUMN_PRODUCTNAME+"=\""+productName +"\";");

    }
    // Print out daatabase as string

    public String databaseToString(){
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM  "+TABLE_PRODUCT+" WHERE 1 ";
        // Cursor point to a location of results
        Cursor c= db.rawQuery(query,null);
         if(c!=null){
             Log.i(TAG," databaseToString method Cursor 1   c ::::::::::: "+c.getCount());
              while(c.moveToNext()){
                dbString=dbString+c.getString( c.getColumnIndex(COLUMN_PRODUCTNAME));
                dbString=dbString+"\n";

            }
         }

        Log.i(TAG," databaseToString final dbString ::::::::::: " +dbString);

        // move 1st row in results
        /*
        c.moveToFirst();
        while(!c.isAfterLast()){
            Log.i(TAG," databaseToString method 3  c ::::::::::: "+c.getString(c.getColumnIndex("productname")) );

            if(c.getString(c.getColumnIndex("productname"))!=null){
                dbString+=c.getString(c.getColumnIndex("productname"));
                dbString+="\n";
                Log.i(TAG," databaseToString method 4  c ::::::::::: "+dbString  );

            }

        }
        */

        db.close();
        return  dbString;
    }


}
