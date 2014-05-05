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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import android.R;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
//ACTIONBAR
//This is only for api11 and above:
import android.app.ActionBar;
//And so we use this ActionBar for api7-8-9-10:
//import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Button;
import android.util.Log;
import android.database.Cursor;

public class Herbarium extends Activity {
	static final String TAG = "Herbarium";
	String speciesTaxcd = "";
	DBAdapter db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		try {
			String destPath = "/data/data/" + getPackageName() + "/databases";
			File f = new File(destPath);
			if (!f.exists()) {
				f.mkdirs();
				f.createNewFile();
				Toast.makeText(this, "Please Wait...Database Being Created",
						Toast.LENGTH_LONG).show();
				CopyDB(getBaseContext().getAssets().open("wisherb"),
						new FileOutputStream(destPath + "/wisherb"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		db = new DBAdapter(this);
	}

	public void CopyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

	public void DisplayTaxon(Cursor c) {
		Toast.makeText(
				this,
				"Taxcd: " + c.getString(0) + "\n" + "Syncd: " + c.getString(1)
						+ "\n" + "family_code:  " + c.getString(2),
				Toast.LENGTH_LONG).show();
	}

	/*
	 * ACTIONBAR These lines assume getActionBar() has already been called but
	 * we are now abandoning ActionBar, using buttons instead, to support api8
	 * 
	 * @Override public boolean onCreateOptionsMenu(Menu menu) {
	 * super.onCreateOptionsMenu(menu); CreateMenu(menu); return true; }
	 * 
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { return
	 * MenuChoice(item); }
	 * 
	 * private void CreateMenu(Menu menu) { //Three tappable menu items. Put
	 * 32x32, 48x48,72x72,96x96,144x144 png images all with the // same name
	 * into folders in res/drawable-ldpi, res/drawable-mdpi, etc. Depending on
	 * the // device in use, the properly-sized image will display. MenuItem
	 * mnu1 = menu.add(0, 0, 0, "Family"); {
	 * mnu1.setIcon(R.drawable.menu_family);
	 * mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM |
	 * MenuItem.SHOW_AS_ACTION_WITH_TEXT); } MenuItem mnu2 = menu.add(0, 1, 1,
	 * "Genus"); { mnu2.setIcon(R.drawable.menu_genus);
	 * mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM |
	 * MenuItem.SHOW_AS_ACTION_WITH_TEXT); } MenuItem mnu3 = menu.add(0, 2, 2,
	 * "Common"); { mnu3.setIcon(R.drawable.menu_commonname);
	 * mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
	 * |MenuItem.SHOW_AS_ACTION_WITH_TEXT); } }
	 * 
	 * private boolean MenuChoice(MenuItem item) { switch (item.getItemId()) {
	 * case android.R.id.home: Intent i = new Intent(this, Herbarium.class);
	 * i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(i); return
	 * true; case 0: //Toast.makeText(this, "You clicked on Family",
	 * Toast.LENGTH_LONG).show(); Intent iFamily = new
	 * Intent("edu.wisc.botany.Wisconsinherbarium.Families");
	 * startActivityForResult(iFamily, 0); return true; case 1:
	 * //Toast.makeText(this, "You clicked on Genus", Toast.LENGTH_LONG).show();
	 * Intent iGenus = new Intent("edu.wisc.botany.Wisconsinherbarium.Genus");
	 * startActivityForResult(iGenus, 1); return true; case 2:
	 * //Toast.makeText(this, "You clicked on Common",
	 * Toast.LENGTH_LONG).show(); Intent iCommon = new
	 * Intent("edu.wisc.botany.Wisconsinherbarium.Common");
	 * startActivityForResult(iCommon, 2); return true; } return false; }
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// ---check if the request code is 0 1 2 3 4 5---
		Log.w(TAG, "In Herbarium.onActivityResult(), checking reqCod");
		if (requestCode == 0) {
			Log.w(TAG,
					"In Herbarium.onActivityResult(int-reqCode=0,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to make new DBAdapter, db");
				// DBAdapter db = new DBAdapter(this);
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to db.open()");
				db.open();
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to get data.getData().toString()");
				String family = data.getData().toString();
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to get cursor, c");
				Cursor c = db.getLikeFamily(family);
				Toast.makeText(this, "Please Wait...Querying " + family,
						Toast.LENGTH_LONG).show();
				// results will hold a long String of all the results, and will
				// be passed with the Intent
				String results = "";
				String taxcd = "";
				if (c != null) {
					Log.w(TAG,
							"In Herbarium.onActivityResult(), cursor c is not null.");
				}
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to c.moveToFirst()");
				if (c.moveToFirst()) {
					Log.w(TAG,
							"In Herbarium.onActivityResult(), c.moveToFirst() returned true");
					Log.w(TAG,
							"In Herbarium.onActivityResult(), about to DisplayTaxon() for first time");

					do {
						Log.w(TAG,
								"In Herbarium.onActivityResult(), about to DisplayTaxon()");
						results += "Family: " + c.getString(26) + "\nGenus: "
								+ c.getString(3) + "\nTaxa: " + c.getString(51)
								+ "\nCommon: " + c.getString(14) + "\nTaxcd: "
								+ c.getString(0) + "___";
						taxcd += c.getString(0);
					} while (c.moveToNext());
				}
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to db.close()");
				Intent iResults = new Intent(
						"edu.wisc.botany.Wisconsinherbarium.Results");
				iResults.putExtra("results", results);
				iResults.putExtra("taxcd", taxcd);
				startActivityForResult(iResults, 3);
				db.close();
			}
		}
		if (requestCode == 1) {
			Log.w(TAG,
					"In Herbarium.onActivityResult(), about to make new DBAdapter, db");
			// DBAdapter db = new DBAdapter(this);
			Log.w(TAG, "In Herbarium.onActivityResult(), about to db.open()");
			db.open();
			Log.w(TAG,
					"In Herbarium.onActivityResult(), about to get data.getData().toString()");
			String genus = data.getData().toString();
			Log.w(TAG,
					"In Herbarium.onActivityResult(), about to get cursor, c");
			Cursor c = db.getByGenus(genus);
			Toast.makeText(this, "Please Wait...Querying " + genus,
					Toast.LENGTH_LONG).show();
			// results will hold a long String of all the results, and will be
			// passed with the Intent
			String results = "";
			String taxcd = "";
			if (c != null) {
				Log.w(TAG,
						"In Herbarium.onActivityResult(), cursor c is not null.");
			}
			Log.w(TAG,
					"In Herbarium.onActivityResult(), about to c.moveToFirst()");
			if (c.moveToFirst()) {
				Log.w(TAG,
						"In Herbarium.onActivityResult(), c.moveToFirst() returned true");
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to DisplayTaxon() for first time");

				do {
					Log.w(TAG,
							"In Herbarium.onActivityResult(), about to DisplayTaxon()");
					results += "Family: " + c.getString(26) + "\nGenus: "
							+ c.getString(3) + "\nTaxa: " + c.getString(51)
							+ "\nCommon: " + c.getString(14) + "\nTaxcd: "
							+ c.getString(0) + "___";
					taxcd += c.getString(0);
				} while (c.moveToNext());
			}
			Log.w(TAG, "In Herbarium.onActivityResult(), about to db.close()");
			Intent iResults = new Intent(
					"edu.wisc.botany.Wisconsinherbarium.Results");
			iResults.putExtra("results", results);
			iResults.putExtra("taxcd", taxcd);
			startActivityForResult(iResults, 3);
			db.close();
		}
		if (requestCode == 2) {
			if (resultCode == RESULT_OK) {
				// Toast.makeText(this, "Common returned okay",
				// Toast.LENGTH_LONG).show();
				// Toast.makeText(this, data.getData().toString(),
				// Toast.LENGTH_SHORT).show();
				Log.w(TAG,
						"In Herbarium.onActivityResult(), reqCode=2, about to db.open()");
				db.open();
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to get data.getData().toString()");
				String common = data.getData().toString();
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to get cursor, c");
				Cursor c = db.getByCommon(common);
				Toast.makeText(this, "Please Wait...Querying " + common,
						Toast.LENGTH_LONG).show();
				// results will hold a long String of all the results, and will
				// be passed with the Intent
				String results = "";
				String taxcd = "";
				if (c != null) {
					Log.w(TAG,
							"In Herbarium.onActivityResult(), cursor c is not null.");
				}
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to c.moveToFirst()");
				if (c.moveToFirst()) {
					Log.w(TAG,
							"In Herbarium.onActivityResult(), c.moveToFirst() returned true");
					Log.w(TAG,
							"In Herbarium.onActivityResult(), about to DisplayTaxon() for first time");

					do {
						Log.w(TAG,
								"In Herbarium.onActivityResult(), about to DisplayTaxon()");
						results += "Family: " + c.getString(26) + "\nGenus: "
								+ c.getString(3) + "\nTaxa: " + c.getString(51)
								+ "\nCommon: " + c.getString(14) + "\nTaxcd: "
								+ c.getString(0) + "___";
						taxcd += c.getString(0);
					} while (c.moveToNext());
				}
				Log.w(TAG,
						"In Herbarium.onActivityResult(), about to db.close()");
				Intent iResults = new Intent(
						"edu.wisc.botany.Wisconsinherbarium.Results");
				iResults.putExtra("results", results);
				iResults.putExtra("taxcd", taxcd);
				startActivityForResult(iResults, 3);
				db.close();
			}
		}
		if (requestCode == 3) {
			Log.w(TAG,
					"In Herbarium.onActivityResult(int-reqCode=3,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {

				speciesTaxcd = data.getData().toString();
				speciesTaxcd = data.getStringExtra("taxcd");
				// TODO-fix this because Taxcds not always 6 characters, instead
				// pass value in db directly
				speciesTaxcd = speciesTaxcd.substring(
						speciesTaxcd.length() - 6, speciesTaxcd.length());
				// Toast.makeText(this, data.getData().toString(),
				// Toast.LENGTH_SHORT).show();
				db.open();
				Cursor c = db.getByTaxcd(speciesTaxcd);
				String speciesDetails = "Species Data";
				if (c.moveToFirst()) {
					Log.w(TAG,
							"In Herbarium.onActivityForResult3(), c.moveToFirst() returned true");
					do {
						Log.w(TAG,
								"In Herbarium.Herbarium.onActivityForResult3()");
						speciesTaxcd = c.getString(0);
						speciesDetails += "\nFAMILY: " + c.getString(26)
								+ "\nTaxa: " + c.getString(51) + "\ncommon: "
								+ c.getString(14) + "\ngrowth_habit_bck: "
								+ c.getString(43) + "\nblooming_dt_bck: "
								+ c.getString(44) + "\ngrowth_habit: "
								+ c.getString(48) + "\nblooming_dt: "
								+ c.getString(49) + "\nTaxcd: "
								+ c.getString(0) + "___";
					} while (c.moveToNext());
				}
				// Toast.makeText(this, speciesDetails,
				// Toast.LENGTH_SHORT).show();
				Log.w(TAG,
						"In Herbarium.onActivityResult3(), about to db.close()");
				Intent iDetails = new Intent(
						"edu.wisc.botany.Wisconsinherbarium.Species");
				iDetails.putExtra("speciesDetails", speciesDetails);
				iDetails.putExtra("speciesTaxcd", speciesTaxcd);
				startActivityForResult(iDetails, 4);
				db.close();
			}
		}
		if (requestCode == 4) {
			Log.w(TAG,
					"In Herbarium.onActivityResult4(int-reqCode=4,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {
				// Toast.makeText(this, "Getting records for " +
				// data.getData().toString(), Toast.LENGTH_SHORT).show();
				Log.w(TAG, "In Herbarium.onActivityResult4()");
				// DBAdapter db = new DBAdapter(this);
				db.open();
				Cursor c = db.getSpecimensByTaxcd(speciesTaxcd);
				String specimenDetails = "";
				if (c.moveToFirst()) {
					Log.w(TAG,
							"In Herbarium.onActivityResult4onClickspecimen(), c.moveToFirst() returned true");
					do {
						Log.w(TAG,
								"In Herbarium.onActivityResult4onClickspecimen()");
						specimenDetails += "Accession: " + c.getString(0)
								+ " \nTaxcd: " + c.getString(19) + "\nDate: "
								+ c.getString(2) + "\nColl No.: "
								+ c.getString(17) + "\nCounty: "
								+ c.getString(47) + "___";
					} while (c.moveToNext());
				}
				Log.w(TAG,
						"In Herbarium.onActivityResult4(), about to db.close()");
				Intent iSpecimen = new Intent(
						"edu.wisc.botany.Wisconsinherbarium.Specimens");
				iSpecimen.putExtra("specimenDetails", specimenDetails);
				startActivityForResult(iSpecimen, 5);
				db.close();
			}
		}
		if (requestCode == 5) {
			Log.w(TAG,
					"In Herbarium.onActivityResult(int-reqCode=5,int-resCode,Intent-data)");
			if (resultCode == RESULT_OK) {
				// Here someone has clicked on a specific specimen and wants the
				// data for that specimen
				// Pull out the data chosen from the Intent:
				String specimenChoice = data.getData().toString();
				// And then pull out the Accession number only...for use with a
				// query. Notice
				// that this uses an unfortunate trick such that we know the
				// Accession value is
				// exactly after the first space and before the second
				// space--WORKS but need to FIX
				String specimenAccessionNumber = specimenChoice.split(" ")[1];
				// specimenAccessionNumber = specimenChoice.split(" ")[1];
				// Toast.makeText(this, "About to get specimen: " +
				// specimenAccessionNumber, Toast.LENGTH_SHORT).show();

				Log.w(TAG, "In Herbarium.onActivityResult5()");
				// DBAdapter db = new DBAdapter(this);
				db.open();
				Cursor c = db.getSpecimen(specimenAccessionNumber);
				String specimenDetails = "";
				if (c.moveToFirst()) {
					Log.w(TAG,
							"In Herbarium.onActivityResult5(), c.moveToFirst() returned true");
					do {
						Log.w(TAG, "In Herbarium.onActivityResult5()");
						// DisplayTaxon(c);
						// Here we get what is on the page...the += will do lots
						// of String creation and
						// destruction--using a StringBuilder or something here
						// will help speed.
						// Cursor contains complete specimen record.
						// specimenDetails += "ACCESSION: " + c.getString(0) +
						// "TYPE: " + c.getString(1) + "COLLDATE: " +
						// c.getString(2) + "FLOWER: " + c.getString(3) +
						// "FRUIT: " + c.getString(4) + "STERILE: " +
						// c.getString(5) + "OBJTYPE: " + c.getString(6) +
						// "INST: " + c.getString(7) + "ANNCODE: " +
						// c.getString(8) + "ANNDATE: " + c.getString(9) +
						// "ANNSOURCE: " + c.getString(10) + "CITY: " +
						// c.getString(11) + "SITENO: " + c.getString(12) +
						// "CITYTYPE: " + c.getString(13) + "COLL2NAME: " +
						// c.getString(14) + "COLL3NAME: " + c.getString(15) +
						// "COLL1NAME: " + c.getString(16) + "COLLNO1: " +
						// c.getString(17) + "COLLEVENT: " + c.getString(18) +
						// "TAXCD: " + c.getString(19) + "CFS: " +
						// c.getString(20) + "CFV: " + c.getString(21) +
						// "CFVariety: " + c.getString(22) + "HABITAT_MISC: " +
						// c.getString(23) + "HABITAT: " + c.getString(24) +
						// "LONGX: " + c.getString(25) + "LAT: " +
						// c.getString(26) + "ELEV: " + c.getString(27) +
						// "LLGENER: " + c.getString(28) + "LONG2: " +
						// c.getString(29) + "LAT2: " + c.getString(30) +
						// "LTDEC: " + c.getString(31) + "LGDEC: " +
						// c.getString(32) + "NOWLOC: " + c.getString(33) +
						// "LOAN: " + c.getString(34) + "PAGES: " +
						// c.getString(35) + "ORIGCD: " + c.getString(36) +
						// "PUBCD: " + c.getString(37) + "LITCIT: " +
						// c.getString(38) + "PUBDATE: " + c.getString(39) +
						// "PUBDATEA: " + c.getString(40) + "VERPERS: " +
						// c.getString(41) + "VERDATE: " + c.getString(42) +
						// "EX: " + c.getString(43) + "ARTICLE: " +
						// c.getString(44) + "PREC: " + c.getString(45) +
						// "STATEL: " + c.getString(46) + "COUNTY: " +
						// c.getString(47) + "COUNTRY: " + c.getString(48) +
						// "T1: " + c.getString(49) + "R1: " + c.getString(51) +
						// "S1: " + c.getString(52) + "NSEW_1: " +
						// c.getString(53) + "TRSGENER: " + c.getString(54) +
						// "T2: " + c.getString(55) + "R2: " + c.getString(56) +
						// "S2: " + c.getString(57) + "NSEW_2: " +
						// c.getString(58) + "PLACE: " + c.getString(59) +
						// "scan: " + c.getString(60) + "MAPFILE: " +
						// c.getString(61) + "username: " + c.getString(62) +
						// "date_time: " + c.getString(63) + "DTRS: " +
						// c.getString(64);
						// specimenDetails += "ACCESSION: " + c.getString(0) +
						// "TYPE: " + c.getString(1) + "COLLDATE: " +
						// c.getString(2) + "FLOWER: " + c.getString(3) +
						// "FRUIT: " + c.getString(4) + "STERILE: " +
						// c.getString(5) + "OBJTYPE: " + c.getString(6) +
						// "INST: " + c.getString(7) + "ANNCODE: " +
						// c.getString(8) + "ANNDATE: " + c.getString(9) +
						// "ANNSOURCE: " + c.getString(10) + "CITY: " +
						// c.getString(11) + "SITENO: " + c.getString(12) +
						// "CITYTYPE: " + c.getString(13) + "COLL2NAME: " +
						// c.getString(14) + "COLL3NAME: " + c.getString(15) +
						// "COLL1NAME: " + c.getString(16) + "COLLNO1: " +
						// c.getString(17) + "COLLEVENT: " + c.getString(18) +
						// "TAXCD: " + c.getString(19) + "CFS: " +
						// c.getString(20) + "CFV: " + c.getString(21) +
						// "CFVariety: " + c.getString(22) + "HABITAT_MISC: " +
						// c.getString(23) + "HABITAT: " + c.getString(24) +
						// "LONGX: " + c.getString(25) + "LAT: " +
						// c.getString(26) + "ELEV: " + c.getString(27) +
						// "LLGENER: " + c.getString(28) + "LONG2: " +
						// c.getString(29) + "LAT2: " + c.getString(30) +
						// "LTDEC: " + c.getString(31) + "LGDEC: " +
						// c.getString(32) + "NOWLOC: " + c.getString(33) +
						// "LOAN: " + c.getString(34) + "PAGES: " +
						// c.getString(35) + "ORIGCD: " + c.getString(36) +
						// "PUBCD: " + c.getString(37) + "LITCIT: " +
						// c.getString(38) + "PUBDATE: " + c.getString(39) +
						// "PUBDATEA: " + c.getString(40) + "VERPERS: " +
						// c.getString(41) + "VERDATE: " + c.getString(42) +
						// "EX: " + c.getString(43) + "ARTICLE: " +
						// c.getString(44) + "PREC: " + c.getString(45) +
						// "STATEL: " + c.getString(46) + "COUNTY: " +
						// c.getString(47) + "COUNTRY: " + c.getString(48) +
						// "T1: " + c.getString(49) + "R1: " + c.getString(51) +
						// "S1: " + c.getString(52) + "NSEW_1: " +
						// c.getString(53) + "TRSGENER: " + c.getString(54) +
						// "T2: " + c.getString(55) + "R2: " + c.getString(56) +
						// "S2: " + c.getString(57) + "NSEW_2: " +
						// c.getString(58) + "PLACE: " + c.getString(59) +
						// "scan: " + c.getString(60) + "MAPFILE: " +
						// c.getString(61) + "username: " + c.getString(62) +
						// "date_time: " + c.getString(63) + "DTRS: " +
						// c.getString(64);
						specimenDetails += "ACCESSION: " + c.getString(0)
								+ "\nTYPE: " + c.getString(1) + "\nCOLLDATE: "
								+ c.getString(2) + "\nFLOWER: "
								+ c.getString(3) + "\nFRUIT: " + c.getString(4)
								+ "\nSTERILE: " + c.getString(5)
								+ "\nOBJTYPE: " + c.getString(6) + "\nINST: "
								+ c.getString(7) + "\nANNCODE: "
								+ c.getString(8) + "\nANNDATE: "
								+ c.getString(9) + "\nANNSOURCE: "
								+ c.getString(10) + "\nCITY: "
								+ c.getString(11) + "\nSITENO: "
								+ c.getString(12) + "\nCITYTYPE: "
								+ c.getString(13) + "\nCOLL2NAME: "
								+ c.getString(14) + "\nCOLL3NAME: "
								+ c.getString(15) + "\nCOLL1NAME: "
								+ c.getString(16) + "\nCOLLNO1: "
								+ c.getString(17) + "\nCOLLEVENT: "
								+ c.getString(18) + "\nTAXCD: "
								+ c.getString(19) + "\nCFS: " + c.getString(20)
								+ "\nCFV: " + c.getString(21) + "\nCFVariety: "
								+ c.getString(22) + "\nHABITAT_MISC: "
								+ c.getString(23) + "\nHABITAT: "
								+ c.getString(24) + "\nLONGX: "
								+ c.getString(25) + "\nLAT: " + c.getString(26)
								+ "\nELEV: " + c.getString(27) + "\nLLGENER: "
								+ c.getString(28) + "\nLONG2: "
								+ c.getString(29) + "\nLAT2: "
								+ c.getString(30) + "\nLTDEC: "
								+ c.getString(31) + "\nLGDEC: "
								+ c.getString(32) + "\nNOWLOC: "
								+ c.getString(33) + "\nLOAN: "
								+ c.getString(34) + "\nPAGES: "
								+ c.getString(35) + "\nORIGCD: "
								+ c.getString(36) + "\nPUBCD: "
								+ c.getString(37) + "\nLITCIT: "
								+ c.getString(38) + "\nPUBDATE: "
								+ c.getString(39) + "\nPUBDATEA: "
								+ c.getString(40) + "\nVERPERS: "
								+ c.getString(41) + "\nVERDATE: "
								+ c.getString(42) + "\nEX: " + c.getString(43)
								+ "\nARTICLE: " + c.getString(44) + "\nPREC: "
								+ c.getString(45) + "\nSTATEL: "
								+ c.getString(46) + "\nCOUNTY: "
								+ c.getString(47) + "\nCOUNTRY: "
								+ c.getString(48) + "\nT1: " + c.getString(49)
								+ "\nR1: " + c.getString(51) + "\nS1: "
								+ c.getString(52) + "\nNSEW_1: "
								+ c.getString(53) + "\nTRSGENER: "
								+ c.getString(54) + "\nT2: " + c.getString(55)
								+ "\nR2: " + c.getString(56) + "\nS2: "
								+ c.getString(57) + "\nNSEW_2: "
								+ c.getString(58) + "\nPLACE: "
								+ c.getString(59) + "\nscan: "
								+ c.getString(60) + "\nMAPFILE: "
								+ c.getString(61) + "\nusername: "
								+ c.getString(62) + "\ndate_time: "
								+ c.getString(63) + "\nDTRS: "
								+ c.getString(64);
						// specimenDetails += "Accession: " + c.getString(0) +
						// " \nTaxcd: " + c.getString(19) + "\nDate: " +
						// c.getString(2) + "\nColl No.: " + c.getString(17) +
						// "\nCounty: " + c.getString(47) + "___";
					} while (c.moveToNext());
				}
				// Toast.makeText(this, "This is the specimen you want: " +
				// specimenDetails, Toast.LENGTH_SHORT).show();
				Log.w(TAG,
						"In Herbarium.onActivityResult5(), about to db.close()");
				TextView tv = (TextView) findViewById(R.id.txt_Specimen);
				tv.setVisibility(View.VISIBLE);
				tv.setText(specimenDetails);
				db.close();
			}
		}
	}

	public void onClick(View view) {
		Log.w(TAG, "In Herbarium.onClick()");
		// DBAdapter db = new DBAdapter(this);
		db.open();
		Cursor c = db.getByTaxcd(speciesTaxcd);
		String speciesDetails = "Species Data";
		if (c.moveToFirst()) {
			Log.w(TAG, "In Herbarium.onClick(), c.moveToFirst() returned true");
			do {
				Log.w(TAG, "In Herbarium.onClick()");
				speciesDetails += "Family: " + c.getString(26) + "\nGenus: "
						+ c.getString(3) + "\nTaxa: " + c.getString(50)
						+ "\nCommon: " + c.getString(14) + "\nTaxcd: "
						+ c.getString(0) + "___";
			} while (c.moveToNext());
		}
		// Toast.makeText(this, speciesDetails, Toast.LENGTH_SHORT).show();
		Log.w(TAG, "In Herbarium.onActivityResult(), about to db.close()");
		Intent iDetails = new Intent(
				"edu.wisc.botany.Wisconsinherbarium.Species");
		iDetails.putExtra("speciesDetails", speciesDetails);
		startActivityForResult(iDetails, 4);
		db.close();
	}

	public void onClickSpecimen(View view) {
		Log.w(TAG, "In Herbarium.onClickspecimen()");
		// DBAdapter db = new DBAdapter(this);
		db.open();
		Cursor c = db.getSpecimensByTaxcd(speciesTaxcd);
		String specimenDetails = "Specimen Data";
		if (c.moveToFirst()) {
			Log.w(TAG,
					"In Herbarium.onClickspecimen(), c.moveToFirst() returned true");
			do {
				Log.w(TAG, "In Herbarium.onClickspecimen()");
				// Cursor contains complete spdetail records.
				specimenDetails += "Accession: " + c.getString(0) + "\nTaxcd: "
						+ c.getString(19) + "\nDate: " + c.getString(2)
						+ "\nColl No.: " + c.getString(17) + "\nCounty: "
						+ c.getString(47) + "___";
			} while (c.moveToNext());
		}
		Log.w(TAG, "In Herbarium.onActivityResult(), about to db.close()");
		Intent iSpecimen = new Intent(
				"edu.wisc.botany.Wisconsinherbarium.Specimens");
		iSpecimen.putExtra("specimenDetails", specimenDetails);
		startActivityForResult(iSpecimen, 5);
		db.close();
	}

	public void onClickFamily(View view) {
		Log.w(TAG, "In Herbarium.onClickFamily()");
		Intent iFamily = new Intent(
				"edu.wisc.botany.Wisconsinherbarium.Families");
		startActivityForResult(iFamily, 0);
	}

	public void onClickGenus(View view) {
		Log.w(TAG, "In Herbarium.onClickGenus()");
		Intent iGenus = new Intent("edu.wisc.botany.Wisconsinherbarium.Genus");
		startActivityForResult(iGenus, 1);
	}

	public void onClickCommon(View view) {
		Log.w(TAG, "In Herbarium.onClickCommon()");
		Intent iCommon = new Intent("edu.wisc.botany.Wisconsinherbarium.Common");
		startActivityForResult(iCommon, 1);
	}

}
