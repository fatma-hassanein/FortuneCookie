package com.example.android.fortunecookie;

import java.io.Serializable;

/**
 * Created by fatma on 01/11/16.
 */
public class Icon implements Serializable {

    int image;
    String name;

    public Icon(int image, String name) {
        this.image = image;
        this.name = name;
    }
}
