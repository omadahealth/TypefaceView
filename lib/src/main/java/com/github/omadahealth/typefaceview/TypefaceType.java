package com.github.omadahealth.typefaceview;

import android.util.Log;

/**
 * Created by stoyan on 4/8/15.
 */
public enum TypefaceType {
    ROBOTO_REGULAR (0, "Roboto-Regular.ttf", "roboto_regular"),
    ROBOTO_MEDIUM (1, "Roboto-Medium.ttf", "roboto_medium"),
    ROBOTO_LIGHT (2, "Roboto-Light.ttf", "roboto_light"),
    ROBOTO_BOLD (3, "Roboto-Bold.ttf", "roboto_bold"),
    ROBOTO_THIN (4, "Roboto-Thin.ttf", "roboto_thin");

    /**
     * The int value of this enum, defined in attr.xml
     */
    private int mValue;

    /**
     * The resource name in assets of the typeface file
     */
    private String mAssetName;

    /**
     * The attribute name of the typeface from xml
     */
    private String mAttrName;

    /**
     * Constructor to set the enum int value and corresponding asset
     * file name
     * @param value The value
     * @param assetName The file name
     */
    TypefaceType(int value, String assetName, String attrName){
        this.mValue = value;
        this.mAssetName = assetName;
        this.mAttrName = attrName;
    }

    /**
     * Get the {@link TypefaceType} for the given enum value that matches the
     * {@link #mValue} of the {@link TypefaceType}
     * @param val The enum value
     * @return
     */
    public static TypefaceType getTypeface(int val){
        switch (val){
            case 1:
                return ROBOTO_MEDIUM;
            case 2:
                return ROBOTO_LIGHT;
            case 3:
                return ROBOTO_BOLD;
            case 4:
                return ROBOTO_THIN;
            case 0:
                return ROBOTO_REGULAR;
            default:
                Log.w("TypefaceType", "Typeface  not supported, defaulting to roboto_regular");
                return ROBOTO_REGULAR;
        }
    }

    public static TypefaceType getTypeface(String name){
        switch (name){
            case "roboto_medium":
                return ROBOTO_MEDIUM;
            case "roboto_light":
                return ROBOTO_LIGHT;
            case "roboto_bold":
                return ROBOTO_BOLD;
            case "roboto_thin":
                return ROBOTO_THIN;
            case "roboto_regular":
                return ROBOTO_REGULAR;
            default:
                Log.w("TypefaceType", "Typeface " + name + " not supported, defaulting to roboto_regular");
                return ROBOTO_REGULAR;

        }
    }

    /**
     * Gets the enum value of this typeface as defined in attr.xml
     * @return
     */
    public int getValue() {
        return mValue;
    }

    /**
     * Gets the resource name in assets of the typeface file
     * @return
     */
    public String getAssetFileName() {
        return mAssetName;
    }
}
