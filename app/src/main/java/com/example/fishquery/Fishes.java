package com.example.fishquery;

public class Fishes {
    public String CommonName;
    public String BioName;
    public String Diet;
    public String Habitat;
    public String imageLink;
    public String MinLength;
    public String QtyAllowed;

    public Fishes() {
    }

    public Fishes(String imageLink){
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}