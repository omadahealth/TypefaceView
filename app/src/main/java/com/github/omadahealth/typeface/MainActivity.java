package com.github.omadahealth.typeface;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.omadahealth.typeface.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Library supports the new gradle 1.3 plugin data binding MvvM framework.
         * For more information {@link https://developer.android.com/tools/data-binding/guide.html}
         */
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        BindingTV medium = new BindingTV("roboto_medium", false);
        BindingTV html = new BindingTV("roboto_thin", true);
        binding.setTypeface(medium);
        binding.setHtml(html);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
