package com.group2.minidog.network.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.group2.minidog.model.DogModel;

import java.util.ArrayList;

public class DogDatabase extends SQLiteOpenHelper implements DogDatabaseI {

    private static final String DATABASE_NAME = "minidog.db";
    private static final int DATABASE_VERSION = 1;

    public DogDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_QUERY = "CREATE TABLE " +
                DogContract.DogEntry.TABLE_NAME + " (" +
                DogContract.DogEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DogContract.DogEntry.COLUMN_NAME + " TEXT NOT NULL," +
                DogContract.DogEntry.COLUMN_BRED_FOR + " TEXT," +
                DogContract.DogEntry.COLUMN_BREED_GROUP + " TEXT," +
                DogContract.DogEntry.COLUMN_LIFE_SPAN + " TEXT," +
                DogContract.DogEntry.COLUMN_TEMPERAMENT + " TEXT," +
                DogContract.DogEntry.COLUMN_ORIGIN + " TEXT," +
                DogContract.DogEntry.COLUMN_IMAGE_ID + " TEXT," +
                DogContract.DogEntry.COLUMN_TIME_CREATED + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

        sqLiteDatabase.execSQL(SQL_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DogContract.DogEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public ArrayList<DogModel> getAllDog() {
        ArrayList<DogModel> arrayList_carModel = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        final Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DogContract.DogEntry.TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String bredFor = cursor.getString(2);
                String breedGroup = cursor.getString(3);
                String lifeSpan = cursor.getString(4);
                String temperament = cursor.getString(5);
                String origin = cursor.getString(6);
                String imageId = cursor.getString(7);

                DogModel dogModel = new DogModel(id, name, bredFor, breedGroup, lifeSpan, temperament, origin, imageId);
                arrayList_carModel.add(dogModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return arrayList_carModel;
    }

    @Override
    public boolean addDog(DogModel dogModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        setContentValues(contentValues, dogModel);

        long count = sqLiteDatabase.insert(DogContract.DogEntry.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
        return count != -1;
    }

    private void setContentValues(ContentValues contentValues, DogModel dogModel){
        contentValues.put(DogContract.DogEntry.COLUMN_NAME, dogModel.getName());
        contentValues.put(DogContract.DogEntry.COLUMN_BRED_FOR, dogModel.getBredFor());
        contentValues.put(DogContract.DogEntry.COLUMN_BREED_GROUP, dogModel.getBreedGroup());
        contentValues.put(DogContract.DogEntry.COLUMN_LIFE_SPAN, dogModel.getLifeSpan());
        contentValues.put(DogContract.DogEntry.COLUMN_TEMPERAMENT, dogModel.getTemperament());
        contentValues.put(DogContract.DogEntry.COLUMN_ORIGIN, dogModel.getOrigin());
        contentValues.put(DogContract.DogEntry.COLUMN_IMAGE_ID, dogModel.getReferenceImageId());
    }
}
