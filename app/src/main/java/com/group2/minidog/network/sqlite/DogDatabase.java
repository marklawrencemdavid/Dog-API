package com.group2.minidog.network.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;
import com.group2.minidog.model.DogAPIModel;
import com.group2.minidog.model.DogSQLiteModel;
import com.group2.minidog.network.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class DogDatabase extends SQLiteOpenHelper implements DogDatabaseI {

    private static final String DATABASE_NAME = "minidog.db";
    private static final int DATABASE_VERSION = 2;
    @Inject
    public FirebaseUser firebaseUser;

    public DogDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        App.getAppComponent().inject(this);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_QUERY = "CREATE TABLE " +
                DogContract.DogEntry.TABLE_NAME + " (" +
                DogContract.DogEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DogContract.DogEntry.COLUMN_USER_EMAIL + " TEXT NOT NULL," +
                DogContract.DogEntry.COLUMN_DOG_ID + " INTEGER NOT NULL," +
                DogContract.DogEntry.COLUMN_DOG_NAME + " TEXT NOT NULL," +
                DogContract.DogEntry.COLUMN_DOG_BRED_FOR + " TEXT," +
                DogContract.DogEntry.COLUMN_DOG_BREED_GROUP + " TEXT," +
                DogContract.DogEntry.COLUMN_DOG_LIFE_SPAN + " TEXT," +
                DogContract.DogEntry.COLUMN_DOG_TEMPERAMENT + " TEXT," +
                DogContract.DogEntry.COLUMN_DOG_ORIGIN + " TEXT," +
                DogContract.DogEntry.COLUMN_DOG_REFERENCE_IMAGE_ID + " TEXT," +
                DogContract.DogEntry.COLUMN_TIME_CREATED + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

        sqLiteDatabase.execSQL(SQL_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DogContract.DogEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<DogSQLiteModel> getAllDog() {
        ArrayList<DogSQLiteModel> dogSQLiteModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] selectionArgs = {firebaseUser.getEmail()};

        final Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM " + DogContract.DogEntry.TABLE_NAME + " WHERE " + DogContract.DogEntry.COLUMN_USER_EMAIL + " = ?",
                selectionArgs);
        if(cursor.moveToFirst()){
            do {
                int sqlId = cursor.getInt(0);
                String userEmail = cursor.getString(1);
                int dogId = cursor.getInt(2);
                String name = cursor.getString(3);
                String bredFor = cursor.getString(4);
                String breedGroup = cursor.getString(5);
                String lifeSpan = cursor.getString(6);
                String temperament = cursor.getString(7);
                String origin = cursor.getString(8);
                String referenceImageId = cursor.getString(9);
                String timeCreated = cursor.getString(10);

                DogSQLiteModel dogSQLiteModel = new DogSQLiteModel.Builder()
                        .setDogAPIModel(new DogAPIModel(dogId, name, bredFor, breedGroup, lifeSpan, temperament, origin, referenceImageId))
                        .setSqliteId(sqlId).setUserEmail(userEmail).setTimeCreated(timeCreated).build();
                dogSQLiteModels.add(dogSQLiteModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return dogSQLiteModels;
    }

    public boolean addDog(DogAPIModel dogAPIModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_ID, dogAPIModel.getId());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_NAME, dogAPIModel.getName());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_BRED_FOR, dogAPIModel.getBredFor());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_BREED_GROUP, dogAPIModel.getBreedGroup());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_LIFE_SPAN, dogAPIModel.getLifeSpan());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_TEMPERAMENT, dogAPIModel.getTemperament());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_ORIGIN, dogAPIModel.getOrigin());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_REFERENCE_IMAGE_ID, dogAPIModel.getImageURL());
        contentValues.put(DogContract.DogEntry.COLUMN_USER_EMAIL, firebaseUser.getEmail());

        long count = sqLiteDatabase.insert(DogContract.DogEntry.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return count != -1;
    }

    public boolean updateDog(DogSQLiteModel dogSQLiteModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DogContract.DogEntry.COLUMN_ID, dogSQLiteModel.getSqliteId());
        contentValues.put(DogContract.DogEntry.COLUMN_USER_EMAIL, dogSQLiteModel.getUserEmail());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_ID, dogSQLiteModel.getId());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_NAME, dogSQLiteModel.getName());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_BRED_FOR, dogSQLiteModel.getBredFor());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_BREED_GROUP, dogSQLiteModel.getBreedGroup());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_LIFE_SPAN, dogSQLiteModel.getLifeSpan());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_TEMPERAMENT, dogSQLiteModel.getTemperament());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_ORIGIN, dogSQLiteModel.getOrigin());
        contentValues.put(DogContract.DogEntry.COLUMN_DOG_REFERENCE_IMAGE_ID, dogSQLiteModel.getReferenceImageId());
        contentValues.put(DogContract.DogEntry.COLUMN_TIME_CREATED, dogSQLiteModel.getTimeCreated());

        String[] selectionArgs = {String.valueOf(dogSQLiteModel.getSqliteId()), dogSQLiteModel.getUserEmail()};

        try (Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DogContract.DogEntry.TABLE_NAME +
                " WHERE " + DogContract.DogEntry.COLUMN_ID + " = ?  AND " + DogContract.DogEntry.COLUMN_USER_EMAIL + " = ?",
                selectionArgs)){
            if (cursor.getCount() > 0) {
                long count = sqLiteDatabase.update(DogContract.DogEntry.TABLE_NAME,
                        contentValues,
                        DogContract.DogEntry.COLUMN_ID + " = ? AND " + DogContract.DogEntry.COLUMN_USER_EMAIL + " = ?",
                        selectionArgs);
                sqLiteDatabase.close();
                return count != -1;
            } else {
                sqLiteDatabase.close();
                return false;
            }
        }catch(Exception e){
            sqLiteDatabase.close();
            return false;
        }
    }

    public boolean deleteDog(DogSQLiteModel dogSQLiteModel) {
        int sqliteId = dogSQLiteModel.getSqliteId();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String[] selectionArgs = {String.valueOf(sqliteId)};
        try (Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DogContract.DogEntry.TABLE_NAME +
                " WHERE " + DogContract.DogEntry.COLUMN_ID + " = ?",
                selectionArgs)) {
            if (cursor.getCount() > 0) {
                long count = sqLiteDatabase.delete(DogContract.DogEntry.TABLE_NAME,
                        DogContract.DogEntry.COLUMN_ID + " = ?",
                        selectionArgs);
                sqLiteDatabase.close();
                return count != -1;
            } else {
                sqLiteDatabase.close();
                return false;
            }
        }catch (Exception e){
            sqLiteDatabase.close();
            return false;
        }
    }
}
