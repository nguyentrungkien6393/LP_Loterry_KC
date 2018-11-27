package com.kien.lp.myapplication.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taina on 04/10/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase mDatabase;
    private static String DB_NAME = "tbl_veso.sqlite";
    private static String DB_FOLDER_PATH = "/data/data/com.kien.lp.myapplication.base/databases/";
    // Table Names
    private static final String TABLE_NAME = "tbl_veso_1";
    private static final String ID = "id";
    private static final String NUMBER = "number";
    private static final String TYPE = "type";
    private static final String POSITION_TICKET = "position_ticket";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        //  String dbPath = context.getDatabasePath(DB_NAME).getPath();
        mDatabase = this.getReadableDatabase();
        //  mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null && mDatabase.isOpen()) {
            mDatabase.close();
        }
    }

    //kiểm tra database có tồn tại không
    public void checkDatabase(Context context) {
        File database = context.getApplicationContext().getDatabasePath(DB_NAME);
        if (database.exists() == false) {
            this.getReadableDatabase();
            if (copyDatabase(context)) {
                Log.e("SUCCESS_COPY_DB", "Copy database thành công");
                //    Toast.makeText(context.getApplicationContext(), "Tải thành công", Toast.LENGTH_LONG).show();
            } else {
                Log.e("ERROR_DB", "Lỗi copy database");
                //    Toast.makeText(context.getApplicationContext(), "Tải thất bại", Toast.LENGTH_LONG).show();
            }
        }
    }

    //copy dữ liệu khi không có database
    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DB_NAME);
            String outputFilePath = DB_FOLDER_PATH + DB_NAME;
            OutputStream outputStream = new FileOutputStream(outputFilePath);
            byte[] buff = new byte[1024];
            int length = 0;
            length = inputStream.read(buff);
            while (length > 0) {
                outputStream.write(buff);
                length = inputStream.read(buff);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public List<History> mSelectHistories() {
//        openDatabase();
//        List<History> histories = new ArrayList<>();
//        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);
//        try {
//            while (cursor.moveToNext()) {
//                histories.add(new History(cursor.getString(cursor.getColumnIndex(_KEY_WORD))
//                        , cursor.getInt(cursor.getColumnIndex(_COUNT))));
//            }
//        } catch (Exception e) {
//            Log.e("ERROR_DB_NHANDAN", e.toString());
//        } finally {
//            cursor.close();
//        }
//        Log.i("DB_NHANDAN", cursor.getCount() + "");
//        return histories;
//
//    }

//    public List<VideoCategory> mSelectVideoCate(int id_cate) {
//        openDatabase();
//        List<VideoCategory> videoCategories = new ArrayList<>();
//        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_CATEGORY + " WHERE " + _ID_CATE + "=" + id_cate, null);
//        try {
//            while (cursor.moveToNext()) {
//
//                videoCategories.add(new VideoCategory(cursor.getInt(cursor.getColumnIndex(_ID_CATE))
//                        , cursor.getString(cursor.getColumnIndex(_CATE_NAME)), cursor.getInt(cursor.getColumnIndex(_PARENT_ID))));
//            }
//        } catch (Exception e) {
//            Log.e("ERROR_DB_STYLIST", e.toString());
//        } finally {
//            cursor.close();
//        }
//        Log.i("DB_STYLIST", cursor.getCount() + "");
//        return videoCategories;
//
//    }

//    public List<VideoCategory> mSelectVideoCate() {
//        openDatabase();
//        List<VideoCategory> videoCategories = new ArrayList<>();
//        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + TABLE_CATEGORY, null);
//        try {
//            while (cursor.moveToNext()) {
//                videoCategories.add(new VideoCategory(cursor.getInt(cursor.getColumnIndex(_ID_CATE))
//                        , cursor.getString(cursor.getColumnIndex(_CATE_NAME)), cursor.getInt(cursor.getColumnIndex(_PARENT_ID))));
//            }
//
//
//        } catch (Exception e) {
//            Log.e("ERROR_DB_STYLIST", e.toString());
//        } finally {
//            cursor.close();
//        }
//        Log.i("DB_STYLIST", cursor.getCount() + "");
//        return videoCategories;
//
//    }

//    public boolean insertHistory(History history) {
//        openDatabase();
//        ContentValues values = new ContentValues();
//        values.put(_KEY_WORD, history.getKey_word());
//        values.put(_COUNT, history.getCount());
//
//        try {
//            mDatabase.insert(TABLE_HISTORY, null, values);
//        } catch (Exception e) {
//            Log.e("ERROS_THEMHISTORY", e.toString());
//            return false;
//        }
//        return true;
//    }

//    public boolean insertCateVideo(VideoCategory category) {
//        //ngày có định dạng yyyymmdd
//        openDatabase();
//        ContentValues values = new ContentValues();
//
//        values.put(_ID_CATE, category.getId_cate());
//        values.put(_CATE_NAME, category.getCate_name());
//        values.put(_PARENT_ID, category.getCate_parent());
//        try {
//            mDatabase.insert(TABLE_CATEGORY, null, values);
//        } catch (Exception e) {
//            Log.e("ERROS_THEMCATE", e.toString());
//            return false;
//        }
//        return true;
//    }

//    public void mDeleteCategory() {
//        openDatabase();
//        try {
//            mDatabase.delete(TABLE_CATEGORY, null, null);
//
////            mDatabase.delete(TABLE_HISTORY, null, null);
//            closeDatabase();
//        } catch (Exception e) {
//            Log.e("ERROR_Delete", "" + e);
//        }
//    }

}
