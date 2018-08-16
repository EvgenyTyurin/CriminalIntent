package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

// Crime activity class
// no use for now since chapter 11

public class CrimeActivity extends SingleFragmentActivity {

    /* for direct access to crime id
    public static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintnet.crime_id";
    */

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintnet.crime_id";

    @Override
    protected Fragment createFragment() {

        // return new CrimeFragment(); for direct access to crime id
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
    }

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}
