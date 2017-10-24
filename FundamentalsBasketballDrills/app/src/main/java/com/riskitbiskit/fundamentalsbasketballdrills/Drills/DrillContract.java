package com.riskitbiskit.fundamentalsbasketballdrills.Drills;

import android.net.Uri;
import android.provider.BaseColumns;

public class DrillContract {

    public static final String SCHEME = "content://";

    public static final String AUTHORITY = "com.riskitbiskit.fundamentalsbasketballdrills";

    public static final Uri BASE_CONTENT_URI = Uri.parse(SCHEME + AUTHORITY);

    public static final String PATH = "drills";

    public static final String FROSH_EXTENSION = "froshx";
    public static final String SOPH_EXTENSION = "sophx";
    public static final String JUNIOR_EXTENSION = "juniorx";
    public static final String SENIOR_EXTENSION = "seniorx";

    public static final class DrillListEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        //Extended Content Uri
        public static final Uri FROSH_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(FROSH_EXTENSION).build();
        public static final Uri SOPH_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(SOPH_EXTENSION).build();
        public static final Uri JUNIOR_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(JUNIOR_EXTENSION).build();
        public static final Uri SENIOR_CONTENT_URI = CONTENT_URI.buildUpon().appendPath(SENIOR_EXTENSION).build();

        //TABLE NAMES
        public static final String FROSH_DRILL_TABLE = "drilltablefrosh";
        public static final String SOPH_DRILL_TABLE = "drilltablesoph";
        public static final String JUNIOR_DRILL_TABLE = "drilltablejunior";
        public static final String SENIOR_DRILL_TABLE = "drilltablesenior";

        //GENERAL
        public static final String TABLE_NAME = "drilltable";
        public static final String SKILL_LEVEL = "skillLevel";
        public static final String SKILL_TYPE = "skillType";
        public static final String SKILL_NAME = "skillName";
        public static final String SKILL_VIDEO_ID = "skillVideoId";
        public static final String SKILL_CHALLENGE = "skillChallenge";
        public static final String SKILL_COMPLETION = "skillCompletion";

        //FULL LIST
        public static final String FROSH_AND_DRIB = "froshAndDrib";
        public static final String SOPH_AND_DRIB = "sophAndDrib";
        public static final String JUNIOR_AND_DRIB = "juniorAndDrib";
        public static final String SENIOR_AND_DRIB = "seniorAndDrib";

        public static final String FROSH_AND_PASS = "froshAndPass";
        public static final String SOPH_AND_PASS = "sophAndPass";
        public static final String JUNIOR_AND_PASS = "juniorAndPass";
        public static final String SENIOR_AND_PASS = "seniorAndPass";

        public static final String FROSH_AND_SHOOT = "froshAndShoot";
        public static final String SOPH_AND_SHOOT = "sophAndShoot";
        public static final String JUNIOR_AND_SHOOT = "juniorAndShoot";
        public static final String SENIOR_AND_SHOOT = "seniorAndShoot";

        //IN PROGRESS LIST
        public static final String IP_FROSH_AND_DRIB = "IPfroshAndDrib";
        public static final String IP_SOPH_AND_DRIB = "IPsophAndDrib";
        public static final String IP_JUNIOR_AND_DRIB = "IPjuniorAndDrib";
        public static final String IP_SENIOR_AND_DRIB = "IPseniorAndDrib";

        public static final String IP_FROSH_AND_PASS = "IPfroshAndPass";
        public static final String IP_SOPH_AND_PASS = "IPsophAndPass";
        public static final String IP_JUNIOR_AND_PASS = "IPjuniorAndPass";
        public static final String IP_SENIOR_AND_PASS = "IPseniorAndPass";

        public static final String IP_FROSH_AND_SHOOT = "IPfroshAndShoot";
        public static final String IP_SOPH_AND_SHOOT = "IPsophAndShoot";
        public static final String IP_JUNIOR_AND_SHOOT = "IPjuniorAndShoot";
        public static final String IP_SENIOR_AND_SHOOT = "IPseniorAndShoot";

        //COMPLETED LIST
        public static final String CP_FROSH_AND_DRIB = "CPfroshAndDrib";
        public static final String CP_SOPH_AND_DRIB = "CPsophAndDrib";
        public static final String CP_JUNIOR_AND_DRIB = "CPjuniorAndDrib";
        public static final String CP_SENIOR_AND_DRIB = "CPseniorAndDrib";

        public static final String CP_FROSH_AND_PASS = "CPfroshAndPass";
        public static final String CP_SOPH_AND_PASS = "CPsophAndPass";
        public static final String CP_JUNIOR_AND_PASS = "CPjuniorAndPass";
        public static final String CP_SENIOR_AND_PASS = "CPseniorAndPass";

        public static final String CP_FROSH_AND_SHOOT = "CPfroshAndShoot";
        public static final String CP_SOPH_AND_SHOOT = "CPsophAndShoot";
        public static final String CP_JUNIOR_AND_SHOOT = "CPjuniorAndShoot";
        public static final String CP_SENIOR_AND_SHOOT = "CPseniorAndShoot";
    }
}
