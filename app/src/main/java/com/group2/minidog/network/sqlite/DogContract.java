package com.group2.minidog.network.sqlite;

import android.provider.BaseColumns;

public class DogContract {
    public DogContract() {}

    /* Inner class that defines the table contents */
    public static class DogEntry implements BaseColumns {
        public static final String TABLE_NAME = "dogs";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_BRED_FOR = "bredFor";
        public static final String COLUMN_BREED_GROUP = "breedGroup";
        public static final String COLUMN_LIFE_SPAN = "lifeSpan";
        public static final String COLUMN_TEMPERAMENT = "temperament";
        public static final String COLUMN_ORIGIN = "origin";
        public static final String COLUMN_IMAGE_ID = "imageId";
        public static final String COLUMN_TIME_CREATED = "timeCreated";
    }
}
