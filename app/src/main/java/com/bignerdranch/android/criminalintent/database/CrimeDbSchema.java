package com.bignerdranch.android.criminalintent.database;

/**
 * DB structure
 */

public class CrimeDbSchema {

    // Table Crimes structure
    public static final class CrimeTable {

        public static final String NAME = "crimes";

        public static final class Cols {
            public static final  String UUID = "uuid";
            public static final  String TITLE = "title";
            public static final  String DATE = "date";
            public static final  String SOLVED = "solved";

        }

    }

}
