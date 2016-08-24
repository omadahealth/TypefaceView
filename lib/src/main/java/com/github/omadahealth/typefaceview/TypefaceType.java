package com.github.omadahealth.typefaceview;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by stoyan on 4/8/15.
 */
public enum TypefaceType {
    ROBOTO_REGULAR(0, "Roboto-Regular.ttf", "roboto_regular"),
    ROBOTO_MEDIUM(1, "Roboto-Medium.ttf", "roboto_medium"),
    ROBOTO_LIGHT(2, "Roboto-Light.ttf", "roboto_light"),
    ROBOTO_BOLD(3, "Roboto-Bold.ttf", "roboto_bold"),
    ROBOTO_THIN(4, "Roboto-Thin.ttf", "roboto_thin"),
    AVENIR_REGULAR(5, "AvenirNextLTPro-Regular.otf", "avenir_regular"),
    AVENIR_MEDIUM(6, "AvenirNext-Medium.otf", "avenir_medium"),
    AVENIR_DEMI(7, "AvenirNextLTPro-Demi.otf", "avenir_demi"),
    AVENIR_BOLD(8, "AvenirNextLTPro-Bold.otf", "avenir_bold");

    /**
     * Used to store the default {@link TypefaceType} in {@link SharedPreferences}
     */
    private static final String SHARED_PREFERENCES_DEFAULT_TYPEFACE = "SHARED_PREFERENCES_DEFAULT_TYPEFACE";

    /**
     * Used to store the default {@link TypefaceType} to avoid too many calls to {@link SharedPreferences}
     */
    private static TypefaceType mDefaultTypeface;

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
     *
     * @param value     The value
     * @param assetName The file name
     */
    TypefaceType(int value, String assetName, String attrName) {
        this.mValue = value;
        this.mAssetName = assetName;
        this.mAttrName = attrName;
    }

    /**
     * Get the {@link TypefaceType} for the given enum value that matches the
     * {@link #mValue} of the {@link TypefaceType}
     *
     * @param val The enum value
     * @return
     */
    public static TypefaceType getTypeface(int val) {
        switch (val) {
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
            case 5:
                return AVENIR_REGULAR;
            case 6:
                return AVENIR_MEDIUM;
            case 7:
                return AVENIR_DEMI;
            case 8:
                return AVENIR_BOLD;
            default:
                Log.w("TypefaceType", "Typeface  not supported, defaulting to roboto_regular");
                return AVENIR_REGULAR;
        }
    }

    public static TypefaceType getTypeface(String name) {
        switch (name) {
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
            case "avenir_regular":
                return AVENIR_REGULAR;
            case "avenir_medium":
                return AVENIR_MEDIUM;
            case "avenir_demi":
                return AVENIR_DEMI;
            case "avenir_bold":
                return AVENIR_BOLD;
            default:
                Log.w("TypefaceType", "Typeface " + name + " not supported, defaulting to roboto_regular");
                return AVENIR_REGULAR;

        }
    }

    /**
     * Returns the default typeface.
     * Would be {@link #ROBOTO_REGULAR} if it was never set before, otherwise the pref in {@link SharedPreferences}
     *
     * @param context The current context of the view
     * @return The default typeface
     */
    public static int getDefaultTypeface(Context context) {
        if (context == null) {
            return ROBOTO_REGULAR.getValue();
        }
        if (mDefaultTypeface != null) {
            return mDefaultTypeface.getValue();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mDefaultTypeface = TypefaceType.getTypeface(sharedPreferences.getInt(SHARED_PREFERENCES_DEFAULT_TYPEFACE, ROBOTO_REGULAR.getValue()));
        return mDefaultTypeface.getValue();
    }

    /**
     * Sets the default typeface.
     * Set the pref in {@link SharedPreferences}
     *
     * @param context      The current context of the view
     * @param typefaceType The {@link TypefaceType} we want to set as default.
     */
    public static void setDefaultTypeface(Context context, TypefaceType typefaceType) {
        if (context == null || typefaceType == null) {
            return;
        }

        mDefaultTypeface = null;
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(SHARED_PREFERENCES_DEFAULT_TYPEFACE, typefaceType.getValue()).apply();
    }

    /**
     * Gets the enum value of this typeface as defined in attr.xml
     *
     * @return
     */
    public int getValue() {
        return mValue;
    }

    /**
     * Gets the resource name in assets of the typeface file
     *
     * @return
     */
    public String getAssetFileName() {
        return mAssetName;
    }
}
