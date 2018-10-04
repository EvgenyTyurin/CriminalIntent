package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Prototype of typical activity with one fragment
 */

public abstract class SingleFragmentActivity extends AppCompatActivity {

    // This must be implemented to create particular fragment
    protected abstract Fragment createFragment();


    // Subclasses may change layout (for tablets version)
    @LayoutRes
    protected int getLayoutResId() {
        return R.layout.activity_fragment;
    }

    // Run point
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        // Add fragment to activity
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().
                    add(R.id.fragment_container, fragment).
                    commit();
        }
    }

}
