package co.edureka.edureka29sep2018.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import co.edureka.edureka29sep2018.model.Util;

/*
Ishants-Macbook-Air:~ ishantkumar$ cd /Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ cd platform-tools/
Ishants-Macbook-Air:platform-tools ishantkumar$ pwd
/Users/ishantkumar/Library/Android/sdk/platform-tools
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb root
restarting adbd as root
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/co.edureka.edureka29sep2018/databases
generic_x86:/data/data/co.edureka.edureka29sep2018/databases # ls
Users.db Users.db-journal
generic_x86:/data/data/co.edureka.edureka29sep2018/databases # sqlite3 Users.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
User              android_metadata
sqlite> select * from User;
1|John|+91 99999 88888|john@example.com
2|Jennie|+91 99999 77777|jennie@example.com
3|Fionna|+91 9879879870|fionna@example.com
sqlite> select * from User;
1|John|+91 99999 88888|john@example.com
2|Jennie|+91 99999 77777|jennie@example.com
3|Fionna|+91 9879879870|fionna@example.com
4|Mia|+91 9090909090|mia@example.com
sqlite>

 */


public class MyContentProvider extends ContentProvider {

    DBHelper dbHelper;              // Reference to DBHelper Object
    SQLiteDatabase sqLiteDatabase;  // Reference to SQLiteDatabase Object

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String tabName = uri.getLastPathSegment();
        int i = sqLiteDatabase.delete(tabName,selection,null);
        return i;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String tabName = uri.getLastPathSegment();
        long id = sqLiteDatabase.insert(tabName,null,values);

        Uri returnUri = Uri.parse("dummy://anything/"+id);
        return returnUri;
    }

    @Override
    public boolean onCreate() { // Belongs to ContentProvider and will be executed when App is installed on device

        dbHelper = new DBHelper(getContext(), Util.DB_NAME,null,Util.DB_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase(); // To perform read and write operations in DB

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        String tabName = uri.getLastPathSegment();
        Cursor cursor = sqLiteDatabase.query(tabName,projection,null,null,null,null,null);
        return cursor; // cursor is data structure containing rows of table
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        String tabName = uri.getLastPathSegment();
        int i = sqLiteDatabase.update(tabName,values,selection,null);
        return i;
    }

    // One Line of Code: Nested Class - DBHelper, can be any name of your choice
    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version); // Create DB with Version
        }

        @Override
        public void onCreate(SQLiteDatabase db) {   // immediately after constructor onCreate of DBHelper shall be executed
            db.execSQL(Util.CREATE_TAB_QUERY);      // Create Table for us
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
