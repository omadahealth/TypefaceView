package com.github.omadahealth.typefaceview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Created by stoyand & oliviergoutay on 4/7/14.
 */
public class TypefaceTextView extends TextView {
    /**
     * A hash we use to hold multiple typefaces for views
     */
    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    /**
     * The default typeface
     */
    public static final int DEFAULT_TYPEFACE = TypefaceType.ROBOTO_REGULAR.getValue();

    /**
     * Default html false
     */
    public static final boolean DEFAULT_HTML_ENABLED = false;

    /**
     * The current typeface that the font is set to
     */
    private TypefaceType mCurrentTypeface = TypefaceType.ROBOTO_REGULAR;

    /**
     * True if the supplied text should be displayed as html
     */
    private boolean mHtmlEnabled;

    public TypefaceTextView(Context context) {
        super(context);
        loadAttributes(context, null);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadAttributes(context, attrs);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadAttributes(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        loadAttributes(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(mHtmlEnabled){
            super.setText(Html.fromHtml(text.toString()), type);
        }else{
            super.setText(text, type);
        }
    }

    /**
     * Sets the typeface for the view
     * @param context
     * @param attrs
     */
    private void loadAttributes(Context context, AttributeSet attrs) {
        //Typeface.createFromAsset doesn't work in the layout editor. Skipping...
        if (isInEditMode() || attrs == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefaceView);
        Integer fontInt = styledAttrs.getInt(R.styleable.TypefaceView_tv_typeface, DEFAULT_TYPEFACE);
        mHtmlEnabled = styledAttrs.getBoolean(R.styleable.TypefaceView_tv_html, DEFAULT_HTML_ENABLED);
        if(mHtmlEnabled){
            setText(getText());
        }
        styledAttrs.recycle();

        mCurrentTypeface = TypefaceType.getTypeface(fontInt);
        Typeface typeface = getFont(context, mCurrentTypeface.getAssetFileName());
        setTypeface(typeface);
    }

    /**
     * Avoid reloading assets every time
     */
    public static Typeface getFont(Context context, String fontName) {
        synchronized (cache) {
            if (!cache.containsKey(fontName)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), fontName);
                    cache.put(fontName, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return cache.get(fontName);
        }
    }

    /**
     * Returns the currently set typeface of this view
     */
    public TypefaceType getCurrentTypeface(){
        return mCurrentTypeface;
    }
}
