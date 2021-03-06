package com.android.service_getdata;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.android.service_getdata.Helper.HelperClass;
import com.android.service_getdata.database.DBQuery;
import com.android.service_getdata.provider.ServiceProvider;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by inbjavia on 7/2/2015.
 */
<<<<<<< HEAD
public class PickUpDataThread extends Thread
{
    ContentResolver mContentResolver;
    private String TAG = PickUpDataThread.class.getSimpleName();

    public PickUpDataThread(ContentResolver contentResolver)
    {
        mContentResolver = contentResolver;
    }
    @Override
    public void run()
    {
        try
        {
            getSmsData();
            getCallData();
        }
        catch (Exception exc)
        {
=======
public class PickUpDataThread extends Thread{
    ContentResolver mContentResolver;
    private String TAG = PickUpDataThread.class.getSimpleName();
    public static final int SMS_THREAD = 1;
    public static final int CALLLOGS_THREAD = 2;
    public static final int CONTACT_THREAD = 3;
    private int currentThread;

    public PickUpDataThread(ContentResolver contentResolver, int type) {
        mContentResolver = contentResolver;
        currentThread = type;
    }
    @Override
    public void run() {
        try {
            switch (currentThread){

                case SMS_THREAD:
                    Log.d(TAG,"run() SMS_THREAD");
                    getSmsData();
                    break;
                case CALLLOGS_THREAD:
                    Log.d(TAG,"run() CALLLOGS_THREAD");
                    getCallData();
                    break;
                case CONTACT_THREAD:
                    break;
                default:
                    break;
            }
        }
        catch (Exception exc){
>>>>>>> ec52dde34d5ca4957bd29e0dcc8021491a4a7cc8
            exc.printStackTrace();
        }
    }
    private void getSmsData(){
        Log.d(TAG,"getSmsData() START");
            Cursor cursor = null;
            cursor = mContentResolver.query(Uri.parse("content://sms"), new String[] {"_id","thread_id","address","person","date","date_sent","type","body"}, null, null, null);
            if (cursor == null) {
                return;
            }
            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    String msgData = "";
                    ContentValues contentValues = new ContentValues();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        if ("m_id".equalsIgnoreCase(cursor.getColumnName(i))) {
                            continue;
                        }
                        if (cursor.getType(i) == Cursor.FIELD_TYPE_INTEGER) {
<<<<<<< HEAD
                            Log.d(TAG, "ColumnName: " + cursor.getColumnName(i) + " Value: " + cursor.getInt(i));
=======
//                            Log.d(TAG, "ColumnName: " + cursor.getColumnName(i) + " Value: " + cursor.getInt(i));
>>>>>>> ec52dde34d5ca4957bd29e0dcc8021491a4a7cc8
                            contentValues.put(cursor.getColumnName(i), cursor.getInt(i));
                        } else if (cursor.getType(i) == Cursor.FIELD_TYPE_STRING)
                        {
                            if (DBQuery.DbFields.COLUMN_SMS_DATE.equals(cursor.getColumnName(i)) ||
                                    DBQuery.DbFields.COLUMN_SMS_ADDRESS.equals(cursor.getColumnName(i)) ||
                                            DBQuery.DbFields.COLUMN_SMS_BODY.equals(cursor.getColumnName(i)) ){
                                msgData += "" + cursor.getString(i);
                            }
<<<<<<< HEAD
                            Log.d(TAG, "ColumnName: " + cursor.getColumnName(i) + " Value: " + cursor.getString(i));
                            contentValues.put(cursor.getColumnName(i), cursor.getString(i));
                        }
                        if (i == cursor.getColumnCount() - 1)
                        {
                            Log.d(TAG, "ColumnName: m_id" + " Value: " + HelperClass.getMessageID(msgData,"",""));
                            contentValues.put("m_id", HelperClass.getMessageID(msgData,"",""));
                        }
                    }

                    if (!HelperClass.isMessageIDExist(contentValues.getAsString("m_id"), mContentResolver)) {
                        Uri uri = mContentResolver.insert(ServiceProvider.CONTENT_URI_SMS, contentValues);
                        Log.d(TAG, "getSmsData() Uri : " + uri);
                        Log.d(TAG, "getSmsData() MessageID : " + contentValues.getAsString("m_id"));
                    }
                    Log.d("My_Service", "mesage Data = " + msgData);
=======
//                            Log.d(TAG, "ColumnName: " + cursor.getColumnName(i) + " Value: " + cursor.getString(i));
                            contentValues.put(cursor.getColumnName(i), cursor.getString(i));
                        }
                        if (i == cursor.getColumnCount() - 1) {
//                            Log.d(TAG, "ColumnName: m_id" + " Value: " + HelperClass.getMessageID(msgData,"",""));
                            contentValues.put(DBQuery.DbFields.COLUMN_SMS_MESSAGE_ID, HelperClass.getMessageID(msgData,"",""));
                        }
                    }

                    if (!HelperClass.isMessageCallIDExist(ServiceProvider.CONTENT_URI_SMS,contentValues.getAsString(DBQuery.DbFields.COLUMN_SMS_MESSAGE_ID), DBQuery.DbFields.COLUMN_SMS_MESSAGE_ID, mContentResolver)) {
                        Uri uri = mContentResolver.insert(ServiceProvider.CONTENT_URI_SMS, contentValues);
//                        Log.d(TAG, "getSmsData() Uri : " + uri);
//                        Log.d(TAG, "getSmsData() MessageID : " + contentValues.getAsString("m_id"));
                    }
//                    Log.d("My_Service", "mesage Data = " + msgData);
>>>>>>> ec52dde34d5ca4957bd29e0dcc8021491a4a7cc8
                    // use msgData
                } while (cursor.moveToNext());
                cursor.close();
            }
            Log.d(TAG, "getSmsData() END");
    }



<<<<<<< HEAD
    private void getCallData()
    {
=======
    private void getCallData(){
>>>>>>> ec52dde34d5ca4957bd29e0dcc8021491a4a7cc8
        Log.d(TAG,"getCallData() START");
        Cursor cursor = null;

            cursor = mContentResolver.query(Uri.parse("content://call_log/calls"), new String[]{"number", "new", "formatted_number", "numbertype", "date", "duration", "numberlabel", "name", "type"}, null, null, null);
            if (cursor == null) {
                return;
            }
            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    String callData = "";
                    ContentValues contentValues = new ContentValues();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        if ("call_id".equalsIgnoreCase(cursor.getColumnName(i))) {
                            continue;
                        }
                        if (cursor.getType(i) == Cursor.FIELD_TYPE_INTEGER) {
                            callData += "" + cursor.getInt(i);
                            Log.d(TAG, "ColumnName: " + cursor.getColumnName(i) + " Value: " + cursor.getInt(i));
                            contentValues.put(cursor.getColumnName(i), cursor.getInt(i));
                        } else if (cursor.getType(i) == Cursor.FIELD_TYPE_STRING) {
                            callData += "" + cursor.getString(i);
                            Log.d(TAG, "ColumnName: " + cursor.getColumnName(i) + " Value: " + cursor.getString(i));
                            contentValues.put(cursor.getColumnName(i), cursor.getString(i));
                        }
                        if (i == cursor.getColumnCount() - 1) {
                            Log.d(TAG, "ColumnName: call_id" + " Value: " + HelperClass.getMessageID(callData,"",""));
<<<<<<< HEAD
                            contentValues.put("call_id", HelperClass.getMessageID(callData,"",""));
                        }
                    }
                    if (!HelperClass.isMessageIDExist(contentValues.getAsString("call_id"), mContentResolver)) {
                        Uri uri = mContentResolver.insert(ServiceProvider.CONTENT_URI_CALL_LOGS, contentValues);
                        Log.d(TAG, "getCallData() Uri : " + uri);
                        Log.d(TAG, "getCallData() call_id : " + contentValues.getAsString("call_id"));
                    }
                    Log.d(TAG, "Call Data = " + callData);
                    // use msgData
=======
                            contentValues.put(DBQuery.DbFields.COLUMN_CALL_LOGS_CALL_ID, HelperClass.getMessageID(callData,"",""));
                        }
                    }
                    if (!HelperClass.isMessageCallIDExist(ServiceProvider.CONTENT_URI_CALL_LOGS,contentValues.getAsString(DBQuery.DbFields.COLUMN_CALL_LOGS_CALL_ID), DBQuery.DbFields.COLUMN_CALL_LOGS_CALL_ID, mContentResolver)) {
                        Uri uri = mContentResolver.insert(ServiceProvider.CONTENT_URI_CALL_LOGS, contentValues);
                        Log.d(TAG, "getSmsData() Uri : " + uri);
                        Log.d(TAG, "getSmsData() call_id : " + contentValues.getAsString("call_id"));
                    }
>>>>>>> ec52dde34d5ca4957bd29e0dcc8021491a4a7cc8
                } while (cursor.moveToNext());
                cursor.close();
            }
            Log.d(TAG, "getCallData() END");
    }
}
