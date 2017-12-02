package com.github.omadahealth.typefaceview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.github.omadahealth.typefaceview.interfaces.EditTextOnKeyImeInterface;

import java.util.Hashtable;

/**
 * Created by stoyand & oliviergoutay on 4/7/14.
 */
public class TypefaceEditText extends AppCompatEditText {
    /**
     * A hash we use to hold multiple typefaces for views
     */
    private static final Hashtable<String, Typeface> cache = new Hashtable<>();

    /**
     * The default typeface
     */
    public static int DEFAULT_TYPEFACE;

    /**
     * The current typeface that the font is set to
     */
    private TypefaceType mCurrentTypeface = TypefaceType.ROBOTO_REGULAR;

    /**
     * The interface to call in {@link #onKeyPreIme(int, KeyEvent)}.
     * If you handled the event, return true. If you want to allow the
     * event to be handled by the next receiver, return false.
     */
    private EditTextOnKeyImeInterface mOnKeyCallback;


    public TypefaceEditText(Context context) {
        super(context);
        setCustomTypeface(context, null);
    }

    public TypefaceEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomTypeface(context, attrs);
    }

    public TypefaceEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomTypeface(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TypefaceEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleRes);
        setCustomTypeface(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        //If you handled the event, return true. If you want to allow
        //the event to be handled by the next receiver, return false.
        if(mOnKeyCallback != null){
            return mOnKeyCallback.onKeyPreIme(keyCode, event);
        }

        return super.onKeyPreIme(keyCode, event);
    }

    /**
     * Sets the typeface for the view
     * @param context
     * @param attrs
     */
    private void setCustomTypeface(Context context, AttributeSet attrs) {
        DEFAULT_TYPEFACE = TypefaceType.getDefaultTypeface(context);

        //Typeface.createFromAsset doesn't work in the layout editor. Skipping...
        if (isInEditMode() || attrs == null || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.TypefaceView);
        Integer fontInt = styledAttrs.getInt(R.styleable.TypefaceView_tv_typeface, DEFAULT_TYPEFACE);
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
     * Data-binding method for custom attribute bind:tv_typeface to be set
     * @param editText The instance of the object to set value on
     * @param type The string name of the typeface, same as in xml
     */
    @BindingAdapter("bind:tv_typeface")
    public static void setCustomTypeface(TypefaceEditText editText, String type) {
        editText.mCurrentTypeface = TypefaceType.getTypeface(type != null ? type : "");
        Typeface typeface = getFont(editText.getContext(), editText.mCurrentTypeface.getAssetFileName());
        editText.setTypeface(typeface);
    }

    /**
     * Returns the currently set typeface of this view
     */
    public TypefaceType getCurrentTypeface(){
        return mCurrentTypeface;
    }

    /**
     * @return the the {@link #mOnKeyCallback}
     */
    public EditTextOnKeyImeInterface getOnKeyCallback() {
        return mOnKeyCallback;
    }

    /**
     * Sets the {@link #mOnKeyCallback} that is called in {@link #onKeyPreIme(int, KeyEvent)}
     * @param onKeyCallback
     */
    public void setOnKeyCallback(EditTextOnKeyImeInterface onKeyCallback) {
        this.mOnKeyCallback = onKeyCallback;
    }
}
