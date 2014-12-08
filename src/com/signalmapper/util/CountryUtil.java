package com.signalmapper.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;

import com.signalmapper.data.CountryDataBaseHelper;

public class CountryUtil {

	public static Long validateAndInsertCountry(String countryCode,
			Context signalStrengthService) {
		CountryDataBaseHelper countryDBHelper = new CountryDataBaseHelper(
				signalStrengthService);
		Cursor cursor = countryDBHelper.getCountry(countryCode);
		boolean addCountry = true;
		Long id = null;
		if (cursor.moveToFirst()) {
			id = cursor.getLong(0);
			addCountry = false;
		}
		if (addCountry) {
			id = insertCountry(countryCode, countryDBHelper);
		}
		return id;
	}
	
	private static Long  insertCountry(String countryCode,CountryDataBaseHelper countryDBHelper)
	{
		Map<String, String> countryDetails = new HashMap<String, String>();
		countryDetails.put("CountryCode", countryCode);
		Locale locale = new Locale("", countryCode);
		countryDetails.put("Name", locale.getDisplayCountry());
		return countryDBHelper.insertRecord(countryDetails);
	}
}
