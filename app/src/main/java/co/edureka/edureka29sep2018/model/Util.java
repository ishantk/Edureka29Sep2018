package co.edureka.edureka29sep2018.model;

import android.net.Uri;

public class Util {

    // DB Related Constants
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Users.db";

    // TABLE Related Constants
    public static final String TAB_NAME = "User";
    public static final String COL_ID = "_ID";       //_ID is primary key and autoincrement in Android
    public static final String COL_NAME = "NAME";
    public static final String COL_PHONE = "PHONE";
    public static final String COL_EMAIL = "EMAIL";

    public static final String CREATE_TAB_QUERY =   "create table User(" +
                                                    "_ID integer primary key autoincrement," +
                                                    "NAME varchar(256)," +
                                                    "PHONE varchar(256)," +
                                                    "EMAIL varchar(256)" +
                                                    ")";

    public static final Uri URI_TAB_USER = Uri.parse("content://co.edureka.edureka29sep2018.provider.mycp/"+TAB_NAME);

}
