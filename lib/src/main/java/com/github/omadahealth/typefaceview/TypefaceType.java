package com.github.omadahealth.typefaceview;

/**
 * Created by stoyan on 4/8/15.
 */
public enum TypefaceType {
    ROBOTO_REGULAR (0, "Roboto-Regular.ttf"),
    ROBOTO_MEDIUM (1, "Roboto-Medium.ttf"),
    ROBOTO_LIGHT (2, "Roboto-Light.ttf"),
    ROBOTO_BOLD (3, "Roboto-Bold.ttf"),
    ROBOTO_THIN (4, "Roboto-Thin.ttf");

    /**
     * The int value of this enum, defined in attr.xml
     */
    private int mValue;

    /**
     * The resource name in assets of the typeface file
     */
    private String mAssetName;

    /**
     * Constructor to set the enum int value and corresponding asset
     * file name
     * @param value The value
     * @param assetName The file name
     */
    TypefaceType(int value, String assetName){
        this.mValue = value;
        this.mAssetName = assetName;
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
            default:
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
