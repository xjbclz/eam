package com.ruwant.eam.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ruwant.eam.presenter.EamPresenter;
import com.ruwant.eam.util.EamLog;

import java.util.List;

public class EamDataBaseImpl implements EamDataBase {
	// 用于打印log
	private static final String	TAG				= "EmaDataBaseAdapter";

	// 表中一条数据的名称
	public static final String	KEY_ID		= "_id";

	// 表中一条数据的内容
	public static final String	KEY_ASSET_NUM		= "如旺资产编号";

	// 表中一条数据的id
	public static final String	KEY_LABEL_NUM		= "标签号码";

	private static final int	DB_COLS_CONTENT_COUNT			= 2;

	public static final String[]	colsname		= {KEY_ASSET_NUM, KEY_LABEL_NUM};

	// 数据库名称为data
	private static final String	DB_NAME			= "eam.db";

	// 数据库表名
	private static final String	DB_TABLE		= "eam";

	// 数据库版本
	private static final int	DB_VERSION		= 1;

	// 本地Context对象
	private Context				mContext		= null;

	//创建一个表
	private static final String	DB_CREATE		= "CREATE TABLE " + DB_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ASSET_NUM + " TEXT,"+ KEY_LABEL_NUM + " TEXT)";

	// 执行open（）打开数据库时，保存返回的数据库对象
	private SQLiteDatabase		mSQLiteDatabase	= null;

	// 由SQLiteOpenHelper继承过来
	private DatabaseHelper		mDatabaseHelper	= null;

	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		/* 构造函数-创建一个数据库 */
		DatabaseHelper(Context context)
		{
			//当调用getWritableDatabase()
			//或 getReadableDatabase()方法时
			//则创建一个数据库
			super(context, DB_NAME, null, DB_VERSION);


		}

		/* 创建一个表 */
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// 数据库没有表时创建一个
			db.execSQL(DB_CREATE);
		}

		/* 升级数据库 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}

	/* 构造函数-取得Context */
	public EamDataBaseImpl(Context context)
	{
		mContext = context;
	}


	// 打开数据库，返回数据库对象
	@Override
	public void open() throws SQLException
	{
		mDatabaseHelper = new DatabaseHelper(mContext);
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
	}


	// 关闭数据库
	public void close()
	{
		mDatabaseHelper.close();
	}

	/* 插入一条数据 */
	@Override
	public long insertData(int cols, List<String> contentList)
	{
		ContentValues initialValues = new ContentValues();

		for (int i = 0; i < cols; i++) {
			initialValues.put(colsname[i], contentList.get(i));
		}

		return mSQLiteDatabase.insert(DB_TABLE, KEY_ID, initialValues);
	}

	/* 删除一条数据 */
	public boolean deleteData(long rowId)
	{
		return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}

	/* 通过Cursor查询所有数据 */
	public Cursor fetchAllData()
	{
		return mSQLiteDatabase.query(DB_TABLE, new String[] { KEY_ID, KEY_ASSET_NUM, KEY_LABEL_NUM }, null, null, null, null, null);
	}

	/* 查询指定数据 */
	public Cursor queryData(int colNum, String queryContent) throws SQLException
	{

		Cursor mCursor =

				mSQLiteDatabase.query(true, DB_TABLE, new String[] { KEY_ID, KEY_ASSET_NUM, KEY_LABEL_NUM }, colsname[colNum] + "=" + "'" + queryContent + "'", null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	/* 查询和显示数据 */
	public void queryDataAndDisplay(final EamPresenter eamPresenter, int colNum, String queryContent)
	{

		Cursor mCursor = queryData(colNum, queryContent);

		if (mCursor != null && mCursor.getCount() > 0)
		{
			EamLog.v("", "Count=" + String.valueOf(mCursor.getCount()));

			String[] rowContent = new String[DB_COLS_CONTENT_COUNT];

			do{
				for (int i = 0; i < DB_COLS_CONTENT_COUNT; i++) {
					rowContent[i] = mCursor.getString(mCursor.getColumnIndex(colsname[i]));
				}

				eamPresenter.setDataBaseContentToPresenter(DB_COLS_CONTENT_COUNT, rowContent);

			}while(mCursor.moveToNext());
		}

		mCursor.close();

	}

	/* 更新一条数据 */
	public boolean updateData(long rowId, int num, String data)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_ASSET_NUM, num);
		args.put(KEY_LABEL_NUM, data);

		return mSQLiteDatabase.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}

	@Override
	public  String[] getColsName(){

		return colsname;
	}

}

