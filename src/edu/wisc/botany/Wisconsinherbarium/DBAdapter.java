/**Copyright (C) 2009-2014 Thomas Maher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package edu.wisc.botany.Wisconsinherbarium;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class DBAdapter {
    static final String TAG = "DBAdapter";
    
    static final String DATABASE_NAME = "wisherb";
    static final String DATABASE_TABLE = "spdetail";
    static final String DATABASE_TABLE_SPECIMEN = "specimen";
    static final int DATABASE_VERSION = 2;
    
    static final String KEY_ROWID = "PKID";
    static final String KEY_NAME = "Taxcd";
    static final String KEY_EMAIL = "Syncd";
    
    static final String Taxcd = "Taxcd";
    static final String Syncd = "Syncd";
    static final String family_code = "family_code";
    static final String genus = "genus";
    static final String species = "species";
    static final String authority = "authority";
    static final String subsp = "subsp";
    static final String variety = "variety";
    static final String forma = "forma";
    static final String subsp_auth = "subsp_auth";
    static final String var_auth = "var_auth";
    static final String forma_auth = "forma_auth";
    static final String sub_family = "sub_family";
    static final String tribe = "tribe";
    static final String common = "common";
    static final String Wisc_found = "Wisc_found";
    static final String ssp = "ssp";
    static final String var = "var";
    static final String f = "f";
    static final String hybrids = "hybrids";
    static final String status_code = "status_code";
    static final String hide = "hide";
    static final String USDA = "USDA";
    static final String COFC = "COFC";
    static final String WETINDICAT = "WETINDICAT";
    static final String FAM_NAME = "FAM_NAME";
    static final String FAMILY = "FAMILY";
    static final String GC = "GC";
    static final String FAMILY_COMMON = "FAMILY_COMMON";
    static final String SYNWisc_found = "SYNWisc_found";
    static final String SYNS_STATUS = "SYNS_STATUS";
    static final String SYNV_STATUS = "SYNV_STATUS";
    static final String SYNF_STATUS = "SYNF_STATUS";
    static final String SYNHYBRIDS_STATUS = "SYNHYBRIDS_STATUS";
    static final String SYNW_STATUS = "SYNW_STATUS";
    static final String speciesweb_Taxcd = "speciesweb_Taxcd";
    static final String Status = "Status";
    static final String Photo = "Photo";
    static final String Photographer = "Photographer";
    static final String Thumbmaps = "Thumbmaps";
    static final String Accgenus = "Accgenus";
    static final String SORTOR = "SORTOR";
    static final String Hand = "Hand";
    static final String growth_habit_bck = "growth_habit_bck";
    static final String blooming_dt_bck = "blooming_dt_bck";
    static final String origin_bck = "origin_bck";
    static final String Thumbphoto = "Thumbphoto";
    static final String date_time = "date_time";
    static final String growth_habit = "growth_habit";
    static final String blooming_dt = "blooming_dt";
    static final String origin = "origin";
    static final String Taxa = "Taxa";
    static final String PKID = "PKID";
    
    //Specimen fields:
    static final String ACCESSION = "ACCESSION";
    static final String TYPE = "TYPE";
    static final String COLLDATE = "COLLDATE";
    static final String FLOWER = "FLOWER";
    static final String FRUIT = "FRUIT";
    static final String STERILE = "STERILE";
    static final String OBJTYPE = "OBJTYPE";
    static final String INST = "INST";
    static final String ANNCODE = "ANNCODE";
    static final String ANNDATE = "ANNDATE";
    static final String ANNSOURCE = "ANNSOURCE";
    static final String CITY = "CITY";
    static final String SITENO = "SITENO";
    static final String CITYTYPE = "CITYTYPE";
    static final String COLL2NAME = "COLL2NAME";
    static final String COLL3NAME = "COLL3NAME";
    static final String COLL1NAME = "COLL1NAME";
    static final String COLLNO1 = "COLLNO1";
    static final String COLLEVENT = "COLLEVENT";
    static final String TAXCD = "TAXCD";
    static final String CFS = "CFS";
    static final String CFV = "CFV";
    static final String CFVariety = "CFVariety";
    static final String HABITAT_MISC = "HABITAT_MISC";
    static final String HABITAT = "HABITAT";
    static final String LONGX = "LONGX";
    static final String LAT = "LAT";
    static final String ELEV = "ELEV";
    static final String LLGENER = "LLGENER";
    static final String LONG2 = "LONG2";
    static final String LAT2 = "LAT2";
    static final String LTDEC = "LTDEC";
    static final String LGDEC = "LGDEC";
    static final String NOWLOC = "NOWLOC";
    static final String LOAN = "LOAN";
    static final String PAGES = "PAGES";
    static final String ORIGCD = "ORIGCD";
    static final String PUBCD = "PUBCD";
    static final String LITCIT = "LITCIT";
    static final String PUBDATE = "PUBDATE";
    static final String PUBDATEA = "PUBDATEA";
    static final String VERPERS = "VERPERS";
    static final String VERDATE = "VERDATE";
    static final String EX = "EX";
    static final String ARTICLE = "ARTICLE";
    static final String PREC = "PREC";
    static final String STATEL = "STATEL";
    static final String COUNTY = "COUNTY";
    static final String COUNTRY = "COUNTRY";
    static final String T1 = "T1";
    static final String R1 = "R1";
    static final String S1 = "S1";
    static final String NSEW_1 = "NSEW_1";
    static final String TRSGENER = "TRSGENER";
    static final String T2 = "T2";
    static final String R2 = "R2";
    static final String S2 = "S2";
    static final String NSEW_2 = "NSEW_2";
    static final String PLACE = "PLACE";
    static final String scan = "scan";
    static final String MAPFILE = "MAPFILE";
    static final String username = "username";
    //static final String date_time = "date_time";
    static final String DTRS = "DTRS";
    //static final String PKID = "PKID";

    
    static final String DATABASE_CREATE = "CREATE TABLE spdetail (Taxcd text default null , Syncd text default null , family_code text default null , genus text default null , species text default null , authority text default null , subsp text default null , variety text default null , forma text default null , subsp_auth text default null , var_auth text default null , forma_auth text default null , sub_family text default null , tribe text default null , common text default null , Wisc_found text default null , ssp text default null , var text default null , f text default null , hybrids text default null , status_code text default null , hide text default null , USDA text default null , COFC text default null , WETINDICAT text default null , FAM_NAME text default null , FAMILY text default null , GC text default null , FAMILY_COMMON text default null , SYNWisc_found text default null , SYNS_STATUS text default null , SYNV_STATUS text default null , SYNF_STATUS text default null , SYNHYBRIDS_STATUS text default null , SYNW_STATUS text default null , speciesweb_Taxcd text default null , Status text default null , Photo text default null , Photographer text default null , Thumbmaps text default null , Accgenus text default null , SORTOR text default null , Hand text default null , growth_habit_bck text default null , blooming_dt_bck text default null , origin_bck text default null , Thumbphoto text default null , date_time text default null , growth_habit text default null , blooming_dt text default null , origin text default null , Taxa text default null , PKID integer primary key autoincrement);";
    //also need this one:
    //CREATE TABLE specimen ( ACCESSION text default null , TYPE text default null , COLLDATE text default null , FLOWER text default null , FRUIT text default null , STERILE text default null , OBJTYPE text default null , INST text default null , ANNCODE text default null , ANNDATE text default null , ANNSOURCE text default null , CITY text default null , SITENO text default null , CITYTYPE text default null , COLL2NAME text default null , COLL3NAME text default null , COLL1NAME text default null , COLLNO1 text default null , COLLEVENT text default null , TAXCD text default null , CFS text default null , CFV text default null , CFVariety text default null , HABITAT_MISC text default null , HABITAT text default null , LONGX text default null , LAT text default null , ELEV text default null , LLGENER text default null , LONG2 text default null , LAT2 text default null , LTDEC text default null , LGDEC text default null , NOWLOC text default null , LOAN text default null , PAGES text default null , ORIGCD text default null , PUBCD text default null , LITCIT text default null , PUBDATE text default null , PUBDATEA text default null , VERPERS text default null , VERDATE text default null , EX text default null , ARTICLE text default null , PREC text default null , STATEL text default null , COUNTY text default null , COUNTRY text default null , T1 text default null , R1 text default null , S1 text default null , NSEW_1 text default null , TRSGENER text default null , T2 text default null , R2 text default null , S2 text default null , NSEW_2 text default null , PLACE text default null , scan text default null , MAPFILE text default null , username text default null , date_time text default null , DTRS text default null , PKID integer primary key autoincrement);

    final Context context;

    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    public void close() 
    {
        DBHelper.close();
    }

    public long insertContact(String name, String email) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(Taxcd, name);
        initialValues.put(Syncd, email);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteContact(long rowId) 
    {
        return db.delete(DATABASE_TABLE, PKID + "=" + rowId, null) > 0;
    }

    public Cursor getAllContacts()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_EMAIL}, null, null, null, null, null);
    }

    public Cursor getContact(long rowId) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getContact(long rowId), About to make Cursor, c, with db.query()");
        //WORKS--need to use Long.toString on the long argument
    	Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_EMAIL}, KEY_ROWID + "=" + Long.toString(rowId), null, null, null, null, null);
    	Log.w(TAG, "In DBAdapter.getContact(long rowId), About to make Cursor, c, with db.query()");
    	Log.w(TAG, "In DBAdapter.getContact(long rowId), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getContact(long rowId), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    public Cursor getByTaxcd(String taxcd) throws SQLException 
    {
    	//Notice that the actual query specifies that Syncd="."...in other cases we want all taxcd=?
    	//  ...and not just the ASN where Syncd="."
    	Log.w(TAG, "In DBAdapter.getByTaxcd(String taxcd), About to make Cursor, c, with db.query()");
    	String[] columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	//String selection = "Taxcd=?";
    	String selection = "Taxcd=? AND Syncd=?";
    	//WORKS:
    	//String[] selectionArgs = new String[] {"ZANPAL"};
    	//String[] selectionArgs = new String[] {taxcd};
    	String[] selectionArgs = new String[] {taxcd, "."};
    	Log.w(TAG, "In DBAdapter.getByTaxcd(), About to make Cursor, c, with db.query()");
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
        
    	Log.w(TAG, "In DBAdapter.getByTaxcd(String taxcd), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getByTaxcd(String taxcd), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    public Cursor getSpecimensByTaxcd(String taxcd) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getSpecimensByTaxcd(String taxcd) About to make Cursor, c, with db.query()");
    	String[] columns = new String[] {ACCESSION, TYPE, COLLDATE, FLOWER, FRUIT, STERILE, OBJTYPE, INST, ANNCODE, ANNDATE, ANNSOURCE, CITY, SITENO, CITYTYPE, COLL2NAME, COLL3NAME, COLL1NAME, COLLNO1, COLLEVENT, TAXCD, CFS, CFV, CFVariety, HABITAT_MISC, HABITAT, LONGX, LAT, ELEV, LLGENER, LONG2, LAT2, LTDEC, LGDEC, NOWLOC, LOAN, PAGES, ORIGCD, PUBCD, LITCIT, PUBDATE, PUBDATEA, VERPERS, VERDATE, EX, ARTICLE, PREC, STATEL, COUNTY, COUNTRY, T1, R1, S1, NSEW_1, TRSGENER, T2, R2, S2, NSEW_2, PLACE, scan, MAPFILE, username, date_time, DTRS, PKID};
    	String selection = "TAXCD=?";
    	String[] selectionArgs = new String[] {taxcd};
    	Cursor mCursor = db.query(DATABASE_TABLE_SPECIMEN, columns, selection, selectionArgs, null, null, null);
    	Log.w(TAG, "In DBAdapter.getSpecimensByTaxcd(String taxcd), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getSpecimensByTaxcd(String taxcd), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
        return mCursor;		
    }
    
    public Cursor getSpecimen(String accession) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getSpecimen(String accession) About to make Cursor, c, with db.query()");
    	String[] columns = new String[] {ACCESSION, TYPE, COLLDATE, FLOWER, FRUIT, STERILE, OBJTYPE, INST, ANNCODE, ANNDATE, ANNSOURCE, CITY, SITENO, CITYTYPE, COLL2NAME, COLL3NAME, COLL1NAME, COLLNO1, COLLEVENT, TAXCD, CFS, CFV, CFVariety, HABITAT_MISC, HABITAT, LONGX, LAT, ELEV, LLGENER, LONG2, LAT2, LTDEC, LGDEC, NOWLOC, LOAN, PAGES, ORIGCD, PUBCD, LITCIT, PUBDATE, PUBDATEA, VERPERS, VERDATE, EX, ARTICLE, PREC, STATEL, COUNTY, COUNTRY, T1, R1, S1, NSEW_1, TRSGENER, T2, R2, S2, NSEW_2, PLACE, scan, MAPFILE, username, date_time, DTRS, PKID};
    	String selection = "ACCESSION=?";
    	String[] selectionArgs = new String[] {accession};
        Cursor mCursor = db.query(DATABASE_TABLE_SPECIMEN, columns, selection, selectionArgs, null, null, null);
    	Log.w(TAG, "In DBAdapter.getSpecimensByTaxcd(String taxcd), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getSpecimensByTaxcd(String taxcd), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
        return mCursor;		
    }
    
    public Cursor getByCommon(String commonParam) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getByCommon(String common), About to make Cursor, c, with db.query()");
    	String[] columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	String selection = "common=?";
    	String[] selectionArgs = new String[] {commonParam};
    	Log.w(TAG, "In DBAdapter.getByCommon(), About to make Cursor, c, with db.query()");
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);        
    	Log.w(TAG, "In DBAdapter.getByFamily(String family), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getByFamily(String family), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
    	Log.w(TAG, "In DBAdapter.getByFamily(String family), about to return cursor, c");
        return mCursor;
    }
    
    public Cursor getByFamily(String family) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getByFamily(String family), About to make Cursor, c, with db.query()");
    	String[] columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	String selection = "FAMILY=?";
    	String[] selectionArgs = new String[] {family};
    	Log.w(TAG, "In DBAdapter.getByFamily(), About to make Cursor, c, with db.query()");
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);        
    	Log.w(TAG, "In DBAdapter.getByFamily(String family), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getByFamily(String family), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
    	Log.w(TAG, "In DBAdapter.getByFamily(String family), about to return cursor, c");
        return mCursor;
    }
    
    public Cursor getByGenus(String genusParam) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getByGenus(String genus), About to make Cursor, c, with db.query()");
    	String[] columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	String selection = "genus=?";
    	String[] selectionArgs = new String[] {genusParam};
    	Log.w(TAG, "In DBAdapter.getByGenus(), About to make Cursor, c, with db.query()");
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);        
    	Log.w(TAG, "In DBAdapter.getByGenus(String genus), About to check if Cursor c is null");
    	if (mCursor != null) {
    		Log.w(TAG, "In DBAdapter.getByGenus(String genus), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
    	Log.w(TAG, "In DBAdapter.getByGenus(String genus), about to return cursor, c");
        return mCursor;
    }
    
    public Cursor getLikeFamily(String family) throws SQLException 
    {
    	Log.w(TAG, "In DBAdapter.getLikeFamily(String family), About to make Cursor, c, with db.query()");
    	//String[] columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	String[] columns = new String[] {Taxcd};
    	String selection = "FAMILY LIKE ?";
    	String[] selectionArgs = new String[] {family+"%"};
    	Log.w(TAG, "In DBAdapter.getLikeFamily(), About to make Cursor, c, with db.query()");
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);        
    	Log.w(TAG, "In DBAdapter.getLikeFamily(String family), About to check if Cursor c is null");
    	//ArrayList<String> taxcdsInFamily = new ArrayList<String>();
    	//Notice that the two spaces after the open-parentheses-character are needed for when zero records.
    	//This worked at one time...but now returns zero 
    	String taxcdsInFamilySql = "Taxcd IN (";
    	//String taxcdsInFamilySql = "Taxcd IN (   ";
    	//String taxcdsInFamilySql = "Taxcd IN ( \"TETTET\", ";
    	if (mCursor.moveToFirst())
        {
    		do {
    			//taxcdsInFamily.add(mCursor.getString(0));
    			taxcdsInFamilySql += "\"" + mCursor.getString(0) + "\", ";
    		} while (mCursor.moveToNext());
        }
    	//remove the extra two characters: ", "
    	taxcdsInFamilySql = taxcdsInFamilySql.substring(0,taxcdsInFamilySql.length()-2);
    	taxcdsInFamilySql += ")";
    	columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	selection = taxcdsInFamilySql;
    	mCursor = db.query(DATABASE_TABLE, columns, selection, null, null, null, null);
    	return mCursor;
    }
    
    public Cursor getZanpal() throws SQLException     
    {
        long pkid=99;
    	String[] columns = new String[] {Taxcd, Syncd, family_code, genus, species, authority, subsp, variety, forma, subsp_auth, var_auth, forma_auth, sub_family, tribe, common, Wisc_found, ssp, var, f, hybrids, status_code, hide, USDA, COFC, WETINDICAT, FAM_NAME, FAMILY, GC, FAMILY_COMMON, SYNWisc_found, SYNS_STATUS, SYNV_STATUS, SYNF_STATUS, SYNHYBRIDS_STATUS, SYNW_STATUS, speciesweb_Taxcd, Status, Photo, Photographer, Thumbmaps, Accgenus, SORTOR, Hand, growth_habit_bck, blooming_dt_bck, origin_bck, Thumbphoto, date_time, growth_habit, blooming_dt, origin, Taxa, PKID};
    	String selection = "PKID=?";
    	String[] selectionArgs = new String[] {"16945"};
    	Log.w(TAG, "In DBAdapter.getZanpal(), About to make Cursor, c, with db.query()");
        Cursor mCursor = db.query(DATABASE_TABLE, columns, selection, selectionArgs, null, null, null);
        Log.w(TAG, "In DBAdapter.getZanpal(), About to check if Cursor c is null");
        if (mCursor != null) {
        	Log.w(TAG, "In DBAdapter.getZanpal(), c is NOT null, about to c.moveToFirst()");
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public boolean updateContact(long rowId, String name, String email) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

}
