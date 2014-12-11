package com.signalmapper.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SignalStrengthDataBaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "SignalStrength";

	public SignalStrengthDataBaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "
				+ DB_NAME
				+ " (SignalStrengthID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ " TechnologyID INTEGER,"
				+ " OperatorCountryID INTEGER,"
				+ " FOREIGN KEY(OperatorID) REFERENCES Operator(OperatorID),"
				+ " FOREIGN KEY(CountryID) REFERENCES Country(CountryID));");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);

		onCreate(db);
	}
}
