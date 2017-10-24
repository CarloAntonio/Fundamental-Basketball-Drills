package com.riskitbiskit.fundamentalsbasketballdrills.Drills;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.riskitbiskit.fundamentalsbasketballdrills.Drills.DrillContract.DrillListEntry;


public class DrillProvider extends ContentProvider {
    private DatabaseOpenHelper mDatabaseOpenHelper;
    Context mContext;

    public static final int DRILL = 100;
    public static final int DRILL_WITH_ID = 101;
    public static final int DRILL_TWO_SELECTIONS = 102;

    //With IDs
    public static final int DRILL_FROSHX_ID = 150;
    public static final int DRILL_SOPHX_ID = 151;
    public static final int DRILL_JUNIORX_ID = 152;
    public static final int DRILL_SENIORX_ID = 153;

    //Completed Uris
    public static final int CP_FROSH_DRIB = 200;
    public static final int CP_SOPH_DRIB = 201;
    public static final int CP_JUNIOR_DRIB = 202;
    public static final int CP_SENIOR_DRIB = 203;

    public static final int CP_FROSH_PASS = 204;
    public static final int CP_SOPH_PASS = 205;
    public static final int CP_JUNIOR_PASS = 206;
    public static final int CP_SENIOR_PASS = 207;

    public static final int CP_FROSH_SHOOT = 208;
    public static final int CP_SOPH_SHOOT = 209;
    public static final int CP_JUNIOR_SHOOT = 210;
    public static final int CP_SENIOR_SHOOT = 211;

    //In Progress Uris
    public static final int IP_FROSH_DRIB = 300;
    public static final int IP_SOPH_DRIB = 301;
    public static final int IP_JUNIOR_DRIB = 302;
    public static final int IP_SENIOR_DRIB = 303;

    public static final int IP_FROSH_PASS = 304;
    public static final int IP_SOPH_PASS = 305;
    public static final int IP_JUNIOR_PASS = 306;
    public static final int IP_SENIOR_PASS = 307;

    public static final int IP_FROSH_SHOOT = 308;
    public static final int IP_SOPH_SHOOT = 309;
    public static final int IP_JUNIOR_SHOOT = 310;
    public static final int IP_SENIOR_SHOOT = 311;

    //Full List Uris
    public static final int DRILL_FULL_LIST_FROSH_AND_DRIB = 400;
    public static final int DRILL_FULL_LIST_SOPH_AND_DRIB = 401;
    public static final int DRILL_FULL_LIST_JUNIOR_AND_DRIB = 402;
    public static final int DRILL_FULL_LIST_SENIOR_AND_DRIB = 403;

    public static final int DRILL_FULL_LIST_FROSH_AND_PASS = 404;
    public static final int DRILL_FULL_LIST_SOPH_AND_PASS = 405;
    public static final int DRILL_FULL_LIST_JUNIOR_AND_PASS = 406;
    public static final int DRILL_FULL_LIST_SENIOR_AND_PASS = 407;

    public static final int DRILL_FULL_LIST_FROSH_AND_SHOOT = 408;
    public static final int DRILL_FULL_LIST_SOPH_AND_SHOOT = 409;
    public static final int DRILL_FULL_LIST_JUNIOR_AND_SHOOT = 410;
    public static final int DRILL_FULL_LIST_SENIOR_AND_SHOOT = 411;

    //Uri Matcher Section
    public static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        //Currently in use
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH, DRILL);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/#", DRILL_WITH_ID);

        //Test
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/TEST", DRILL_TWO_SELECTIONS);

        //Extensions
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillContract.FROSH_EXTENSION + "/#", DRILL_FROSHX_ID);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillContract.SOPH_EXTENSION + "/#", DRILL_SOPHX_ID);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillContract.JUNIOR_EXTENSION + "/#", DRILL_JUNIORX_ID);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillContract.SENIOR_EXTENSION + "/#", DRILL_SENIORX_ID);

        //Completed List Uri Match Frosh
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_FROSH_AND_DRIB, CP_FROSH_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_FROSH_AND_PASS, CP_FROSH_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_FROSH_AND_SHOOT, CP_FROSH_SHOOT);

        //Completed List Uri Match Soph
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_SOPH_AND_DRIB, CP_SOPH_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_SOPH_AND_PASS, CP_SOPH_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_SOPH_AND_SHOOT, CP_SOPH_SHOOT);

        //Completed List Uri Match Junior
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_JUNIOR_AND_DRIB, CP_JUNIOR_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_JUNIOR_AND_PASS, CP_JUNIOR_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_JUNIOR_AND_SHOOT, CP_JUNIOR_SHOOT);

        //Completed List Uri Match Senior
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_SENIOR_AND_DRIB, CP_SENIOR_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_SENIOR_AND_PASS, CP_SENIOR_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.CP_SENIOR_AND_SHOOT, CP_SENIOR_SHOOT);

        //In Progress List Uri Match Frosh
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_FROSH_AND_DRIB, IP_FROSH_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_FROSH_AND_PASS, IP_FROSH_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_FROSH_AND_SHOOT, IP_FROSH_SHOOT);

        //In Progress List Uri Match Soph
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_SOPH_AND_DRIB, IP_SOPH_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_SOPH_AND_PASS, IP_SOPH_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_SOPH_AND_SHOOT, IP_SOPH_SHOOT);

        //In Progress List Uri Match Junior
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_JUNIOR_AND_DRIB, IP_JUNIOR_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_JUNIOR_AND_PASS, IP_JUNIOR_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_JUNIOR_AND_SHOOT, IP_JUNIOR_SHOOT);

        //In Progress List Uri Matcher Senior
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_SENIOR_AND_DRIB, IP_SENIOR_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_SENIOR_AND_PASS, IP_SENIOR_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.IP_SENIOR_AND_SHOOT, IP_SENIOR_SHOOT);

        //Full List Uri Matcher Frosh
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.FROSH_AND_DRIB, DRILL_FULL_LIST_FROSH_AND_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.FROSH_AND_PASS, DRILL_FULL_LIST_FROSH_AND_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.FROSH_AND_SHOOT, DRILL_FULL_LIST_FROSH_AND_SHOOT);

        //Full List Uri Matcher Soph
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.SOPH_AND_DRIB, DRILL_FULL_LIST_SOPH_AND_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.SOPH_AND_PASS, DRILL_FULL_LIST_SOPH_AND_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.SOPH_AND_SHOOT, DRILL_FULL_LIST_SOPH_AND_SHOOT);

        //Full List Uri Matcher Junior
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.JUNIOR_AND_DRIB, DRILL_FULL_LIST_JUNIOR_AND_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.JUNIOR_AND_PASS, DRILL_FULL_LIST_JUNIOR_AND_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.JUNIOR_AND_SHOOT, DRILL_FULL_LIST_JUNIOR_AND_SHOOT);

        //Full List Uri Matcher Junior
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.SENIOR_AND_DRIB, DRILL_FULL_LIST_SENIOR_AND_DRIB);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.SENIOR_AND_PASS, DRILL_FULL_LIST_SENIOR_AND_PASS);
        uriMatcher.addURI(DrillContract.AUTHORITY, DrillContract.PATH + "/" + DrillListEntry.SENIOR_AND_SHOOT, DRILL_FULL_LIST_SENIOR_AND_SHOOT);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDatabaseOpenHelper = new DatabaseOpenHelper(mContext);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase sqLiteDatabase = mDatabaseOpenHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);

        switch (match) {

            //General
            case DRILL:
                cursor = sqLiteDatabase.query(DrillListEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DRILL_WITH_ID:
                selection = DrillListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(DrillListEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case DRILL_TWO_SELECTIONS:
                cursor = sqLiteDatabase.query(DrillListEntry.TABLE_NAME,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"2", "2"},
                        null,
                        null,
                        sortOrder);
                break;

            //With IDs
            case DRILL_FROSHX_ID:
                selection = DrillListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case DRILL_SOPHX_ID:
                selection = DrillListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case DRILL_JUNIORX_ID:
                selection = DrillListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case DRILL_SENIORX_ID:
                selection = DrillListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;


            //Completed List Matchers//

            //Completed Frosh
            case CP_FROSH_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"1", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_FROSH_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"2", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_FROSH_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"3", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            //Completed Soph
            case CP_SOPH_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"1", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_SOPH_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"2", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_SOPH_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"3", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            //Completed Junior
            case CP_JUNIOR_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"1", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_JUNIOR_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"2", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_JUNIOR_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"3", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            //Completed Senior
            case CP_SENIOR_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"1", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_SENIOR_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"2", "4"},
                        null,
                        null,
                        sortOrder);
                break;

            case CP_SENIOR_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?" + " AND " + DrillListEntry.SKILL_COMPLETION + "=?",
                        new String[] {"3", "4"},
                        null,
                        null,
                        sortOrder);
                break;


            //In Progress List Matchers//

            //In Progress Frosh
            case IP_FROSH_DRIB:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablefrosh WHERE skillType=1) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_FROSH_PASS:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablefrosh WHERE skillType=2) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_FROSH_SHOOT:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablefrosh WHERE skillType=3) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            //In Progress Soph
            case IP_SOPH_DRIB:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablesoph WHERE skillType=1) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_SOPH_PASS:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablesoph WHERE skillType=2) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_SOPH_SHOOT:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablesoph WHERE skillType=3) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            //In Progress Junior
            case IP_JUNIOR_DRIB:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablejunior WHERE skillType=1) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_JUNIOR_PASS:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablejunior WHERE skillType=2) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_JUNIOR_SHOOT:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablejunior WHERE skillType=3) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            //In Progress Frosh
            case IP_SENIOR_DRIB:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablesenior WHERE skillType=1) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_SENIOR_PASS:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablesenior WHERE skillType=2) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            case IP_SENIOR_SHOOT:
                cursor = sqLiteDatabase.rawQuery("SELECT _id, skillName, skillCompletion FROM (SELECT * FROM drilltablesenior WHERE skillType=3) WHERE skillCompletion in (?, ?, ?, ?)",
                        new String [] {"0", "1", "2", "3"});
                break;

            //Full List Matchers//

            //Full List Frosh
            case DRILL_FULL_LIST_FROSH_AND_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"1"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_FROSH_AND_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"2"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_FROSH_AND_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.FROSH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"3"},
                        null,
                        null,
                        sortOrder);
                break;

            //Full List Soph
            case DRILL_FULL_LIST_SOPH_AND_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"1"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_SOPH_AND_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"2"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_SOPH_AND_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.SOPH_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"3"},
                        null,
                        null,
                        sortOrder);
                break;

            //Full List Junior
            case DRILL_FULL_LIST_JUNIOR_AND_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"1"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_JUNIOR_AND_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"2"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_JUNIOR_AND_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.JUNIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"3"},
                        null,
                        null,
                        sortOrder);
                break;

            //Full List Senior
            case DRILL_FULL_LIST_SENIOR_AND_DRIB:
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"1"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_SENIOR_AND_PASS:
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"2"},
                        null,
                        null,
                        sortOrder);
                break;

            case DRILL_FULL_LIST_SENIOR_AND_SHOOT:
                cursor = sqLiteDatabase.query(DrillListEntry.SENIOR_DRILL_TABLE,
                        projection,
                        DrillListEntry.SKILL_TYPE + "=?",
                        new String[] {"3"},
                        null,
                        null,
                        sortOrder);
                break;

            //Default
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase sqLiteDatabase = mDatabaseOpenHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        switch (match) {
            case DRILL_WITH_ID:
                selection = DrillListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return sqLiteDatabase.update(DrillListEntry.TABLE_NAME, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Could not update" + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}
