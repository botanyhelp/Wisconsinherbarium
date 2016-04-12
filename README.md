### Android Wisconsin Herbarium App README

This file discusses how to create the Wisconsinherbarium Android 
application.  It runs on most phones but is in development and 
in experimental stage. 

The SQLite database that sits inside the Android application 
was difficult to create from database backup files.  Tools to 
migrate from MySQL to SQLite did not work.  The SQLite database 
was created by hand using database backup files converted to 
suitable logical backups (SQL INSERTS).  Almost all of the 
database records made it.  The real database, running at 
www.botany.wisc.edu/herbarium/, has several more tables than 
this Android version.

The Android source code contains inline documentation.  It uses 
mostly older Android API and should run on any phone that can 
run APIv8, which is almost every phone out there.  The entire 
application runs alone without any Internet or any other 
permissions.  

Google Play limits application size to 50 megabytes.  The sqlite3 
database file itself, uncompressed, is 141 megabytes and probably 
makes the bulk of the 124 megabytes.  For this reason, installing 
Wisconsinherbarium.apk requires one to (on their phone) visit the 
Settings to allow non-Google-Play apps to be installed...and then 
they need to get the Wisconsinherbarium.apk from the web (I think). 

The database itself, looks like this:
```
sqlite3 Wisconsinherbarium/assets/wisherb
SQLite version 3.7.13 2012-06-11 02:05:22
Enter ".help" for instructions
Enter SQL statements terminated with a ";"
sqlite> .schema
CREATE TABLE ANNLKUP ( ANNCODE text default null , WHOLENAME text default null , BIO text default null , PKID integer primary key autoincrement);
CREATE TABLE colleventlkup ( COLLEVENT text default null , NAME text default null , COLLCD text default null , COLL1NAME text default null , TEXT text default null , PKID integer primary key autoincrement);
CREATE TABLE habitat ( Taxcd text default null , Descp text default null , PKID integer primary key autoincrement);
CREATE TABLE sitelkup ( SITENO text default null , SPLACE text default null , SCOUNTY text default null , STYPE text default null , PKID integer primary key autoincrement);
CREATE TABLE spdetail (Taxcd text default null , Syncd text default null , family_code text default null , genus text default null , species text default null , authority text default null , subsp text default null , variety text default null , forma text default null , subsp_auth text default null , var_auth text default null , forma_auth text default null , sub_family text default null , tribe text default null , common text default null , Wisc_found text default null , ssp text default null , var text default null , f text default null , hybrids text default null , status_code text default null , hide text default null , USDA text default null , COFC text default null , WETINDICAT text default null , FAM_NAME text default null , FAMILY text default null , GC text default null , FAMILY_COMMON text default null , SYNWisc_found text default null , SYNS_STATUS text default null , SYNV_STATUS text default null , SYNF_STATUS text default null , SYNHYBRIDS_STATUS text default null , SYNW_STATUS text default null , speciesweb_Taxcd text default null , Status text default null , Photo text default null , Photographer text default null , Thumbmaps text default null , Accgenus text default null , SORTOR text default null , Hand text default null , growth_habit_bck text default null , blooming_dt_bck text default null , origin_bck text default null , Thumbphoto text default null , date_time text default null , growth_habit text default null , blooming_dt text default null , origin text default null , Taxa text default null , PKID integer primary key autoincrement);
CREATE TABLE specimen ( ACCESSION text default null , TYPE text default null , COLLDATE text default null , FLOWER text default null , FRUIT text default null , STERILE text default null , OBJTYPE text default null , INST text default null , ANNCODE text default null , ANNDATE text default null , ANNSOURCE text default null , CITY text default null , SITENO text default null , CITYTYPE text default null , COLL2NAME text default null , COLL3NAME text default null , COLL1NAME text default null , COLLNO1 text default null , COLLEVENT text default null , TAXCD text default null , CFS text default null , CFV text default null , CFVariety text default null , HABITAT_MISC text default null , HABITAT text default null , LONGX text default null , LAT text default null , ELEV text default null , LLGENER text default null , LONG2 text default null , LAT2 text default null , LTDEC text default null , LGDEC text default null , NOWLOC text default null , LOAN text default null , PAGES text default null , ORIGCD text default null , PUBCD text default null , LITCIT text default null , PUBDATE text default null , PUBDATEA text default null , VERPERS text default null , VERDATE text default null , EX text default null , ARTICLE text default null , PREC text default null , STATEL text default null , COUNTY text default null , COUNTRY text default null , T1 text default null , R1 text default null , S1 text default null , NSEW_1 text default null , TRSGENER text default null , T2 text default null , R2 text default null , S2 text default null , NSEW_2 text default null , PLACE text default null , scan text default null , MAPFILE text default null , username text default null , date_time text default null , DTRS text default null , PKID integer primary key autoincrement);
CREATE TABLE t_vascular_common_names ( taxcd text default null , common text default null , WiscFound text default null , t_vascular_common_names_id integer primary key autoincrement);
CREATE INDEX idxspdetailcommon ON spdetail(common);
CREATE INDEX idxspdetailfamily ON spdetail(family);
CREATE INDEX idxspdetailsyncd ON spdetail(Syncd);
CREATE INDEX idxspdetailtaxcd ON spdetail(Taxcd);
CREATE INDEX idxspdetailwiscfound ON spdetail(Wisc_found);
CREATE INDEX idxspecimenaccession ON specimen(ACCESSION);
CREATE INDEX idxspecimentaxcd ON specimen(Taxcd);
CREATE INDEX idxtvascularcommonnamescommon ON t_vascular_common_names(common);
CREATE INDEX idxtvascularcommonnameswiscfound ON t_vascular_common_names(WiscFound);
sqlite>
```
