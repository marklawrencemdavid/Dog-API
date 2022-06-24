package com.rensuuuuuuuu.dogapi.network.sqlite;

import android.provider.BaseColumns;

public class DogContract {
    public DogContract() {}

    /* Inner class that defines the table contents */
    public static class DogEntry implements BaseColumns {
        public static final String TABLE_NAME = "dogs";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_USER_EMAIL = "userEmail";
        public static final String COLUMN_DOG_ID = "dogID";
        public static final String COLUMN_DOG_NAME = "dogName";
        public static final String COLUMN_DOG_BRED_FOR = "dogBredFor";
        public static final String COLUMN_DOG_BREED_GROUP = "dogBreedGroup";
        public static final String COLUMN_DOG_LIFE_SPAN = "dogLifeSpan";
        public static final String COLUMN_DOG_TEMPERAMENT = "dogTemperament";
        public static final String COLUMN_DOG_ORIGIN = "dogOrigin";
        public static final String COLUMN_DOG_REFERENCE_IMAGE_ID = "dogReferenceImageId";
        public static final String COLUMN_TIME_CREATED = "timeCreated";
    }
}
