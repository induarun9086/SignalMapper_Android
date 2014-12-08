package com.signalmapper.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;

import com.signalmapper.data.TechnologyDataBaseHelper;

public class TechnologyUtil {
	public static Long validateAndInsertTechnology(String name,
			Context signalStrengthService) {
		TechnologyDataBaseHelper techDBHelper = new TechnologyDataBaseHelper(
				signalStrengthService);
		Cursor cursor = techDBHelper.getTechnology(name);
		boolean addCountry = true;
		Long id = null;
		if (cursor.moveToFirst()) {
			id = cursor.getLong(0);
			addCountry = false;
		}
		if (addCountry) {
			id = insertCountry(name, techDBHelper);
		}
		return id;
	}

	private static Long insertCountry(String name,
			TechnologyDataBaseHelper techDBHelper) {
		Map<String, String> technologyDetails = new HashMap<String, String>();
		technologyDetails.put("Name", name);
		return techDBHelper.insertRecord(technologyDetails);
	}

}
