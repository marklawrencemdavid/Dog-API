package com.group2.minidog.model;

import com.google.gson.annotations.SerializedName;

public class DogAPIModel {

    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("bred_for")
    private final String bredFor;
    @SerializedName("breed_group")
    private final String breedGroup;
    @SerializedName("life_span")
    private final String lifeSpan;
    @SerializedName("temperament")
    private final String temperament;
    @SerializedName("origin")
    private final String origin;
    @SerializedName("reference_image_id")
    private final String referenceImageId;

    //No args constructor for use in serialization
    //public DogAPIModel() { }

    public DogAPIModel(int id, String name, String bredFor, String breedGroup, String lifeSpan, String temperament, String origin, String referenceImageId) {
        //super();
        this.id = id;
        this.name = name;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.lifeSpan = lifeSpan;
        this.temperament = temperament;
        this.origin = origin;
        this.referenceImageId = referenceImageId;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public String getBredFor() {
        return bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {return origin;}

    public String getReferenceImageId() {return referenceImageId;}

    public String getImageURL() { return "https://cdn2.thedogapi.com/images/" + referenceImageId + ".jpg"; }
}
