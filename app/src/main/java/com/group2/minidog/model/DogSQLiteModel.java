package com.group2.minidog.model;

public class DogSQLiteModel extends DogAPIModel{

    private final int sqliteId;
    private final String userEmail;
    private final String timeCreated;

    private DogSQLiteModel(Builder builder) {
        super(builder.dogAPIModel.getId(), builder.dogAPIModel.getName(), builder.dogAPIModel.getBredFor(), builder.dogAPIModel.getBreedGroup(),
            builder.dogAPIModel.getLifeSpan(), builder.dogAPIModel.getTemperament(), builder.dogAPIModel.getOrigin(), builder.dogAPIModel.getReferenceImageId());
        this.sqliteId = builder.sqliteId;
        this.userEmail = builder.userEmail;
        this.timeCreated = builder.timeCreated;
    }

    public int getSqliteId() {
        return sqliteId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public static class Builder{
        private int sqliteId;
        private String userEmail;
        private String timeCreated;
        private DogAPIModel dogAPIModel;

        public Builder setSqliteId(final int sqliteId){
            this.sqliteId = sqliteId;
            return this;
        }

        public Builder setUserEmail(final String userEmail){
            this.userEmail = userEmail;
            return this;
        }

        public Builder setTimeCreated(final String timeCreated){
            this.timeCreated = timeCreated;
            return this;
        }

        public Builder setDogAPIModel(final DogAPIModel dogAPIModel){
            this.dogAPIModel = dogAPIModel;
            return this;
        }

        public DogSQLiteModel build(){
            return new DogSQLiteModel(this);
        }
    }
}
