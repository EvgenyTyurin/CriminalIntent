package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 *  Crime List Activity class
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
