package com.example.menu2interfazes.ui.gallery;

public class GalleryItem {
    private int imageResId; // Identificador del recurso de imagen
    private String text;

    public GalleryItem(int imageResId, String text) {
        this.imageResId = imageResId;
        this.text = text;
    }

    // Getters
    public int getImageResId() {
        return imageResId;
    }

    public String getText() {
        return text;
    }

    // Setters (si es necesario)
    // ...
}


