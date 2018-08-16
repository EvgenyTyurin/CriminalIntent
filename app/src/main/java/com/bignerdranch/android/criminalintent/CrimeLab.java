package com.bignerdranch.android.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Crime collection singleton class
 */

public class CrimeLab {

    private Map<UUID,Crime> mCrimes;
    private static CrimeLab sCrimeLab;

    // Returns crimes list
    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    // Constructor
    private CrimeLab(Context context) {
        mCrimes = new LinkedHashMap<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.put(crime.getID(), crime);
        }
    }

    // Returns crime by id
    public Crime getCrime(UUID id) {
        return mCrimes.get(id);
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

}
