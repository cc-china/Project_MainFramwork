package com.grirms.theft_electricity_check.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grirms.theft_electricity_check.doEvent.model.NOtUploadSummaryDetailsModel;
import com.grirms.theft_electricity_check.doEvent.model.SummaryDetailsModel;
import com.grirms.theft_electricity_check.doEvent.model.SummaryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019\4\10 0010.
 */

public class SQLiteDB {
    private static SQLiteDB mSQLiteDB = null;
    private final Context ctx;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    private SQLiteDB(Context ctx) {
        this.ctx = ctx;
    }

    public static SQLiteDB open_SQLiteDB(Context ctx) {
        if (mSQLiteDB == null) {
            synchronized (SQLiteDB.class) {
                if (mSQLiteDB == null) {
                    mSQLiteDB = new SQLiteDB(ctx);
                }
            }
        }
        return mSQLiteDB;
    }

    public void openDB() {
        dbHelper = DBHelper.getInstance(ctx);
        if (db != null) {
            return;
        }
        try {
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void turnOffDB() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    /**
     * 查询工单概要全部数据
     */
    public List<SummaryModel.DTSBean> querySummaryAllData() {
        StringBuilder sql = new StringBuilder();
        List<SummaryModel.DTSBean> mList = new ArrayList<>();
        sql.append("select * from ").append(dbHelper.SUMMARY_TABLE_NAME);
        Cursor cursor = dbHelper.query(sql.toString(), null);
        while (cursor.moveToNext()) {
            SummaryModel.DTSBean bean = getSummaryObjectModel(cursor);
            mList.add(bean);
        }
        cursor.close();
        return mList;
    }

    /**
     * 插入工单概要数据
     */
    public void insertSummary(SummaryModel model) {
        if (model.getDTS() != null && model.getDTS().size() != 0) {
            for (int i = 0; i < model.getDTS().size(); i++) {
                ContentValues contentValues = putSummaryContentValues(model.getDTS().get(i));
                try {
                    db.insert(dbHelper.SUMMARY_TABLE_NAME, null, contentValues);
                } catch (Exception e) {
                    Log.e("111111111111111111111", e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 插入工单详情数据
     */
    public void insertSummaryDetails(SummaryDetailsModel model) {
        if (model.getDTS() != null && model.getDTS().size() != 0) {
            ContentValues contentValues = putSummaryDetailsContentValues(model.getDTS().get(0));
            try {
                db.insert(dbHelper.SUMMARY_DETAILS_TABLE_NAME, null, contentValues);
            } catch (Exception e) {
                Log.e("111111111111111111111", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * 插入待传工单数据
     */
    public void insertNOtUploadSummaryDetails(NOtUploadSummaryDetailsModel model) {
        if (model != null) {
            ContentValues contentValues = putNOtUploadSummaryDetailsContentValues(model);
            try {
                db.insert(dbHelper.NOT_UPLOAD_SUMMARY_DETAILS_TABLE_NAME, null, contentValues);
            } catch (Exception e) {
                Log.e("111111111111111111111", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * insert 待传工单 model赋值
     */
    private ContentValues putNOtUploadSummaryDetailsContentValues(NOtUploadSummaryDetailsModel model) {

        ContentValues value = new ContentValues();
        value.put(dbHelper.APP_NO, model.getAPP_NO());
        value.put(dbHelper.APP_TYPE, model.getAPP_TYPE());
        value.put(dbHelper.IMG_TIME, model.getIMG_TIME());
        value.put(dbHelper.EQUIP_ADDR, model.getEQUIP_ADDR());
        value.put(dbHelper.RESULT_VOLT, model.getRESULT_VOLT());
        value.put(dbHelper.RESULT_CURR, model.getRESULT_CURR());
        value.put(dbHelper.RESULT_PF, model.getRESULT_PF());
        value.put(dbHelper.ERROR_RATIO, model.getERROR_RATIO());
        value.put(dbHelper.ABNORMAL_DATE, model.getABNORMAL_DATE());
        value.put(dbHelper.BASE64_IMAGE, model.getBASE64_IMAGE());
        value.put(dbHelper.STEAL_REASON, model.getSTEAL_REASON());
        return value;
    }

    /**
     * insert 工单概要model赋值
     */
    private ContentValues putSummaryContentValues(SummaryModel.DTSBean model) {

        ContentValues value = new ContentValues();
        value.put(dbHelper.APP_NO, model.getAPP_NO());
        value.put(dbHelper.GPS_LATITUDE, model.getGPS_LATITUDE());
        value.put(dbHelper.TG_NAME, model.getTG_NAME());
        value.put(dbHelper.TG_NO, model.getTG_NO());
        value.put(dbHelper.ELEC_ADDR, model.getELEC_ADDR());
        value.put(dbHelper.CONS_NO, model.getCONS_NO());
        value.put(dbHelper.BAR_CODE, model.getBAR_CODE());
        value.put(dbHelper.GPS_LONGITUDE, model.getGPS_LONGITUDE());
        value.put(dbHelper.CONS_NAME, model.getCONS_NAME());
        value.put(dbHelper.COMPLETE_TIME, model.getCOMPLETE_TIME());
        value.put(dbHelper.USER_TYPE, model.getUSER_TYPE());
        value.put(dbHelper.METER_TYPE, model.getMETER_TYPE());
        value.put(dbHelper.MOBILE, model.getMOBILE());
        return value;
    }

    /**
     * insert 工单详情model赋值
     */
    private ContentValues putSummaryDetailsContentValues(SummaryDetailsModel.DTSBean model) {

        ContentValues value = new ContentValues();
        value.put(dbHelper.APP_NO, model.getAPP_NO());
        value.put(dbHelper.BAR_CODE, model.getBAR_CODE());
        value.put(dbHelper.GPS_LATITUDE, model.getGPS_LATITUDE());
        value.put(dbHelper.TG_NAME, model.getTG_NAME());
        value.put(dbHelper.TG_NO, model.getTG_NO());
        value.put(dbHelper.ELEC_ADDR, model.getELEC_ADDR());
        value.put(dbHelper.CONS_NO, model.getCONS_NO());
        value.put(dbHelper.BAR_CODE, model.getBAR_CODE());
        value.put(dbHelper.GPS_LONGITUDE, model.getGPS_LONGITUDE());
        value.put(dbHelper.CONS_NAME, model.getCONS_NAME());
        value.put(dbHelper.COMPLETE_TIME, model.getCOMPLETE_TIME());
        value.put(dbHelper.USER_TYPE, model.getUSER_TYPE());
        value.put(dbHelper.METER_TYPE, model.getMETER_TYPE());
        value.put(dbHelper.RATE_VOLT, model.getRATE_VOLT());
        value.put(dbHelper.RATE_CURR, model.getRATE_CURR());
        value.put(dbHelper.CT, model.getCT());
        value.put(dbHelper.PT, model.getPT());
        value.put(dbHelper.MATER_SORT, model.getMATER_SORT());
        value.put(dbHelper.MEASURE_MODE, model.getMEASURE_MODE());
        value.put(dbHelper.SEAL_CODE, model.getSEAL_CODE());
        value.put(dbHelper.BUD, model.getBUD());
        value.put(dbHelper.COMM_PROT, model.getCOMM_PORT());
        value.put(dbHelper.DEAL_USER, model.getDEAL_USER());
        value.put(dbHelper.COMM_TYPE, model.getCOMM_TYPE());
        value.put(dbHelper.WIRE_TYPE, model.getWIRE_TYPE());
        value.put(dbHelper.DISP_TIME, model.getDISP_TIME());
        value.put(dbHelper.VOLT_CODE, model.getVOLT_CODE());
        value.put(dbHelper.INST_DATE, model.getINST_DATE());
        value.put(dbHelper.COM_ADDR1, model.getCOM_ADDR1());
        value.put(dbHelper.STEAL_TYPE, model.getCOM_ADDR1());
        value.put(dbHelper.DISP_USR, model.getDISP_USR());
        value.put(dbHelper.COMM_PORT, model.getCOMM_PORT());
        value.put(dbHelper.MOBILE, model.getMOBILE());
        return value;
    }

    /**
     * 查询工单概要model赋值
     */
    private SummaryModel.DTSBean getSummaryObjectModel(Cursor cursor) {
        SummaryModel.DTSBean bean = new SummaryModel.DTSBean();
        bean.setGPS_LATITUDE(cursor.getString(cursor.getColumnIndex("GPS_LATITUDE")));
        bean.setTG_NAME(cursor.getString(cursor.getColumnIndex("TG_NAME")));
        bean.setAPP_NO(cursor.getString(cursor.getColumnIndex("APP_NO")));
        bean.setTG_NO(cursor.getString(cursor.getColumnIndex("TG_NO")));
        bean.setELEC_ADDR(cursor.getString(cursor.getColumnIndex("ELEC_ADDR")));
        bean.setCONS_NO(cursor.getInt(cursor.getColumnIndex("CONS_NO")));
        bean.setBAR_CODE(cursor.getString(cursor.getColumnIndex("BAR_CODE")));
        bean.setGPS_LONGITUDE(cursor.getString(cursor.getColumnIndex("GPS_LONGITUDE")));
        bean.setCONS_NAME(cursor.getString(cursor.getColumnIndex("CONS_NAME")));
        bean.setCOMPLETE_TIME(cursor.getString(cursor.getColumnIndex("COMPLETE_TIME")));
        bean.setUSER_TYPE(cursor.getString(cursor.getColumnIndex("USER_TYPE")));
        bean.setMETER_TYPE(cursor.getString(cursor.getColumnIndex("METER_TYPE")));
        bean.setMOBILE(cursor.getString(cursor.getColumnIndex("MOBILE")));
        return bean;
    }

    /**
     * 查询工单详情数据
     */
    public SummaryDetailsModel.DTSBean querySummaryDetailsData(String app_no) {
        SummaryDetailsModel.DTSBean bean = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(dbHelper.SUMMARY_DETAILS_TABLE_NAME).append(" where APP_NO =" + "'").append(app_no).append("'");
        Cursor cursor = dbHelper.query(sql.toString(), null);
        while (cursor.moveToNext()) {
            bean = getSummaryDetailsObjectModel(cursor);
        }
        cursor.close();
        return bean;
    }

    /**
     * 查询工单详情model赋值
     */
    private SummaryDetailsModel.DTSBean getSummaryDetailsObjectModel(Cursor cursor) {
        SummaryDetailsModel.DTSBean bean = new SummaryDetailsModel.DTSBean();
        bean.setAPP_NO(cursor.getString(cursor.getColumnIndex("APP_NO")));
        bean.setCONS_NO(cursor.getString(cursor.getColumnIndex("CONS_NO")));
        bean.setCONS_NAME(cursor.getString(cursor.getColumnIndex("CONS_NAME")));
        bean.setCOMPLETE_TIME(cursor.getString(cursor.getColumnIndex("COMPLETE_TIME")));
        bean.setBAR_CODE(cursor.getString(cursor.getColumnIndex("BAR_CODE")));
        bean.setELEC_ADDR(cursor.getString(cursor.getColumnIndex("ELEC_ADDR")));
        bean.setGPS_LATITUDE(cursor.getString(cursor.getColumnIndex("GPS_LATITUDE")));
        bean.setGPS_LONGITUDE(cursor.getString(cursor.getColumnIndex("GPS_LONGITUDE")));
        bean.setTG_NO(cursor.getString(cursor.getColumnIndex("TG_NO")));
        bean.setTG_NAME(cursor.getString(cursor.getColumnIndex("TG_NAME")));
        bean.setUSER_TYPE(cursor.getString(cursor.getColumnIndex("USER_TYPE")));
        bean.setMETER_TYPE(cursor.getString(cursor.getColumnIndex("METER_TYPE")));
        bean.setMOBILE(cursor.getString(cursor.getColumnIndex("MOBILE")));
        bean.setRATE_VOLT(cursor.getString(cursor.getColumnIndex("RATE_VOLT")));
        bean.setRATE_CURR(cursor.getString(cursor.getColumnIndex("RATE_CURR")));
        bean.setCT(cursor.getString(cursor.getColumnIndex("CT")));
        bean.setPT(cursor.getString(cursor.getColumnIndex("PT")));
        bean.setMATER_SORT(cursor.getString(cursor.getColumnIndex("MATER_SORT")));
        bean.setMEASURE_MODE(cursor.getString(cursor.getColumnIndex("MEASURE_MODE")));
        bean.setSEAL_CODE(cursor.getString(cursor.getColumnIndex("SEAL_CODE")));
        bean.setBUD(cursor.getString(cursor.getColumnIndex("BUD")));
        bean.setCOMM_PROT(cursor.getString(cursor.getColumnIndex("COMM_PROT")));
        bean.setDEAL_USER(cursor.getString(cursor.getColumnIndex("DEAL_USER")));
        bean.setCOMM_TYPE(cursor.getString(cursor.getColumnIndex("COMM_TYPE")));
        bean.setWIRE_TYPE(cursor.getString(cursor.getColumnIndex("WIRE_TYPE")));
        bean.setDISP_TIME(cursor.getString(cursor.getColumnIndex("DISP_TIME")));
        bean.setVOLT_CODE(cursor.getString(cursor.getColumnIndex("VOLT_CODE")));
        bean.setINST_DATE(cursor.getString(cursor.getColumnIndex("INST_DATE")));
        bean.setCOM_ADDR1(cursor.getString(cursor.getColumnIndex("COM_ADDR1")));
        bean.setSTEAL_TYPE(cursor.getString(cursor.getColumnIndex("STEAL_TYPE")));
        bean.setDISP_USR(cursor.getString(cursor.getColumnIndex("DISP_USR")));
        bean.setCOMM_PORT(cursor.getString(cursor.getColumnIndex("COMM_PORT")));
        return bean;
    }

    /**
     * 查询待传工单全部数据
     */
    public List<NOtUploadSummaryDetailsModel> queryNotuploadSummaryAllData() {
        StringBuilder sql = new StringBuilder();
        List<NOtUploadSummaryDetailsModel> mList = new ArrayList<>();
        sql.append("select * from ").append(dbHelper.NOT_UPLOAD_SUMMARY_DETAILS_TABLE_NAME);
        Cursor cursor = dbHelper.query(sql.toString(), null);
        while (cursor.moveToNext()) {
            NOtUploadSummaryDetailsModel bean = getNOtUploadSummaryObjectModel(cursor);
            mList.add(bean);
        }
        cursor.close();
        return mList;
    }

    /**
     * 查询待传工单 model赋值
     */
    private NOtUploadSummaryDetailsModel getNOtUploadSummaryObjectModel(Cursor cursor) {
        NOtUploadSummaryDetailsModel bean = new NOtUploadSummaryDetailsModel();
        bean.setAPP_NO(cursor.getString(cursor.getColumnIndex(dbHelper.APP_NO)));
        bean.setAPP_TYPE(cursor.getString(cursor.getColumnIndex(dbHelper.APP_TYPE)));
        bean.setIMG_TIME(cursor.getString(cursor.getColumnIndex(dbHelper.IMG_TIME)));
        bean.setEQUIP_ADDR(cursor.getString(cursor.getColumnIndex(dbHelper.EQUIP_ADDR)));
        bean.setRESULT_VOLT(cursor.getString(cursor.getColumnIndex(dbHelper.RESULT_VOLT)));
        bean.setRESULT_CURR(cursor.getString(cursor.getColumnIndex(dbHelper.RESULT_CURR)));
        bean.setRESULT_PF(cursor.getString(cursor.getColumnIndex(dbHelper.RESULT_PF)));
        bean.setERROR_RATIO(cursor.getString(cursor.getColumnIndex(dbHelper.ERROR_RATIO)));
        bean.setABNORMAL_DATE(cursor.getString(cursor.getColumnIndex(dbHelper.ABNORMAL_DATE)));
        bean.setBASE64_IMAGE(cursor.getString(cursor.getColumnIndex(dbHelper.BASE64_IMAGE)));
        bean.setSTEAL_REASON(cursor.getString(cursor.getColumnIndex(dbHelper.STEAL_REASON)));
        return bean;
    }

    /**
     * 删除待传工单
     */
    public int deleteNotUploadSummaryData(String app_no) {
        return dbHelper.delete(app_no, dbHelper.NOT_UPLOAD_SUMMARY_DETAILS_TABLE_NAME);
    }
}
