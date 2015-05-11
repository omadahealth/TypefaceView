package com.github.omadahealth.typefaceview.interfaces;

import android.view.KeyEvent;

/**
 * Created by stoyan on 5/11/15.
 */
public interface EditTextOnKeyImeInterface {

    /**
     * Set to be called in {@link com.github.omadahealth.typefaceview.TypefaceEditText#onKeyPreIme(int, KeyEvent)}
     * @param keyCode
     * @param event
     * @return
     */
    boolean onKeyPreIme(int keyCode, KeyEvent event);
}
