package com.grirms.theft_electricity_check.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.grirms.theft_electricity_check.fpt.APP;

/**
 * Created by Administrator on 2019\4\10 0010.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = APP.getApplication().getPackageName() + "_DB";
    public final String SUMMARY_TABLE_NAME = "summary_table";
    public final String SUMMARY_DETAILS_TABLE_NAME = "summary_details_table";
    public final String NOT_UPLOAD_SUMMARY_DETAILS_TABLE_NAME = "not_upload_summary_details_table";
    private static int VERSION = 1;
    private static DBHelper mInstance;
    public String GPS_LATITUDE = "GPS_LATITUDE";
    public String TG_NAME = "TG_NAME";
    public String APP_NO = "APP_NO";
    public String TG_NO = "TG_NO";
    public String ELEC_ADDR = "ELEC_ADDR";
    public String CONS_NO = "CONS_NO";
    public String BAR_CODE = "BAR_CODE";
    public String GPS_LONGITUDE = "GPS_LONGITUDE";
    public String CONS_NAME = "CONS_NAME";
    public String COMPLETE_TIME = "COMPLETE_TIME";
    public String USER_TYPE = "USER_TYPE";
    public String METER_TYPE = "METER_TYPE";
    public String MOBILE = "MOBILE";
    public String RATE_VOLT = "RATE_VOLT";
    public String CT = "CT";
    public String PT = "PT";
    public String MATER_SORT = "MATER_SORT";
    public String MEASURE_MODE = "MEASURE_MODE";
    public String RATE_CURR = "RATE_CURR";
    public String SEAL_CODE = "SEAL_CODE";
    public String BUD = "BUD";
    public String COMM_PROT = "COMM_PROT";
    public String DEAL_USER = "DEAL_USER";
    public String COMM_TYPE = "COMM_TYPE";
    public String WIRE_TYPE = "WIRE_TYPE";
    public String DISP_TIME = "DISP_TIME";
    public String VOLT_CODE = "VOLT_CODE";
    public String INST_DATE = "INST_DATE";
    public String COM_ADDR1 = "COM_ADDR1";
    public String STEAL_TYPE = "STEAL_TYPE";
    public String DISP_USR = "DISP_USR";
    public String COMM_PORT = "COMM_PORT";
    public String APP_TYPE = "APP_TYPE";
    public String IMG_TIME = "IMG_TIME";
    public String EQUIP_ADDR = "EQUIP_ADDR";
    public String RESULT_VOLT = "RESULT_VOLT";
    public String RESULT_CURR = "RESULT_CURR";
    public String RESULT_PF = "RESULT_PF";
    public String ERROR_RATIO = "ERROR_RATIO";
    public String ABNORMAL_DATE = "ABNORMAL_DATE";
    public String BASE64_IMAGE = "BASE64_IMAGE";
    public String STEAL_REASON = "STEAL_REASON";

    private DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public static DBHelper getInstance(Context ctx) {
        if (mInstance == null) {
            synchronized (DBHelper.class) {
                if (mInstance == null) {
                    mInstance = new DBHelper(ctx);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //工单列表
        String summarySQL = "create table IF NOT EXISTS " + SUMMARY_TABLE_NAME + " (" +
                APP_NO + " VARCHAR primary key ," +
                GPS_LATITUDE + " VARCHAR ," +
                TG_NAME + " VARCHAR ," +
                TG_NO + " VARCHAR ," +
                ELEC_ADDR + " VARCHAR ," +
                CONS_NO + " VARCHAR ," +
                BAR_CODE + " VARCHAR ," +
                GPS_LONGITUDE + " VARCHAR ," +
                CONS_NAME + " VARCHAR ," +
                COMPLETE_TIME + " VARCHAR ," +
                USER_TYPE + " VARCHAR ," +
                METER_TYPE + " VARCHAR ," +
                MOBILE + " VARCHAR" + " );";
        //工单详情
        String summaryDetails = "create table IF NOT EXISTS " + SUMMARY_DETAILS_TABLE_NAME + " (" +
                APP_NO + " VARCHAR primary key ," +
                BAR_CODE + " VARCHAR ," +
                GPS_LATITUDE + " VARCHAR ," +
                TG_NAME + " VARCHAR ," +
                TG_NO + " VARCHAR ," +
                ELEC_ADDR + " VARCHAR ," +
                CONS_NO + " VARCHAR ," +
                GPS_LONGITUDE + " VARCHAR ," +
                CONS_NAME + " VARCHAR ," +
                COMPLETE_TIME + " VARCHAR ," +
                USER_TYPE + " VARCHAR ," +
                METER_TYPE + " VARCHAR ," +
                RATE_VOLT + " VARCHAR ," +
                RATE_CURR + " VARCHAR ," +
                CT + " VARCHAR ," +
                PT + " VARCHAR ," +
                MATER_SORT + " VARCHAR ," +
                MEASURE_MODE + " VARCHAR ," +
                SEAL_CODE + " VARCHAR ," +
                BUD + " VARCHAR ," +
                COMM_PROT + " VARCHAR ," +
                DEAL_USER + " VARCHAR ," +
                COMM_TYPE + " VARCHAR ," +
                WIRE_TYPE + " VARCHAR ," +
                DISP_TIME + " VARCHAR ," +
                VOLT_CODE + " VARCHAR ," +
                INST_DATE + " VARCHAR ," +
                COM_ADDR1 + " VARCHAR ," +
                STEAL_TYPE + " VARCHAR ," +
                DISP_USR + " VARCHAR ," +
                COMM_PORT + " VARCHAR ," +
                MOBILE + " VARCHAR" + " );";

        //待上传工单
        String notUpLoadSummarySQL = "create table IF NOT EXISTS " + NOT_UPLOAD_SUMMARY_DETAILS_TABLE_NAME + " (" +
                APP_NO + " VARCHAR primary key ," +
                APP_TYPE + " VARCHAR ," +
                IMG_TIME + " VARCHAR ," +
                EQUIP_ADDR + " VARCHAR ," +
                RESULT_VOLT + " VARCHAR ," +
                RESULT_CURR + " VARCHAR ," +
                RESULT_PF + " VARCHAR ," +
                ERROR_RATIO + " VARCHAR ," +
                ABNORMAL_DATE + " VARCHAR ," +
                BASE64_IMAGE + " VARCHAR ," +
                STEAL_REASON + " VARCHAR );";
        db.execSQL(summarySQL);
        db.execSQL(summaryDetails);
        db.execSQL(notUpLoadSummarySQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public synchronized Cursor query(String sql, String[] selectionArgs) {
        Cursor cursor = getWritableDatabase().rawQuery(sql, selectionArgs);
        return cursor;
    }

    public int delete(String app_no, String table_name) {
        return getWritableDatabase().delete(table_name, APP_NO + " = ?", new String[]{app_no});
    }
}
