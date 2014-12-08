package com.signalmapper.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.signalmapper.data.OperatorCountryDataBaseHelper;
import com.signalmapper.data.OperatorDataBaseHelper;

public class OperatorUtil {
	public static Long validateAndInsertOperator(String name,
			Context signalStrengthService) {
		OperatorDataBaseHelper operatorDBHelper = new OperatorDataBaseHelper(
				signalStrengthService);
		Cursor cursor = operatorDBHelper.getOperator(name);
		boolean addCountry = true;
		Long id = null;
		if (cursor.moveToFirst()) {
			id = cursor.getLong(0);
			Log.d("Operator name is ", cursor.getString(1));
			addCountry = false;
		}
		if (addCountry) {
			id = insertOperator(name, operatorDBHelper);
		}
		return id;
	}

	private static Long insertOperator(String name,
			OperatorDataBaseHelper operatorDBHelper) {
		Map<String, String> operatorDetails = new HashMap<String, String>();
		operatorDetails.put("Name", name);
		return operatorDBHelper.insertRecord(operatorDetails);
	}

	public static void insertOperatorCountry(Long operatorID, Long countryID,
			Context signalStrengthService) {
		OperatorCountryDataBaseHelper operatorCountryDataBaseHelper = new OperatorCountryDataBaseHelper(
				signalStrengthService);
		Cursor cursor = operatorCountryDataBaseHelper.getOperatorCountryRow(operatorID, countryID);
		boolean addRecord = true;
		Long id = null;
		if (cursor.moveToFirst()) {
			id = cursor.getLong(0);
			addRecord = false;
		}
		if (addRecord) {
			id = insertOperatorCountry(operatorID,countryID, operatorCountryDataBaseHelper);
		}
		Log.d("Operator Country Id is ", String.valueOf(id));
	}
	
	private static Long insertOperatorCountry(Long operatorID,Long countryID,
			OperatorCountryDataBaseHelper operatorCountryDataBaseHelper) {
		Map<String, String> operatorDetails = new HashMap<String, String>();
		operatorDetails.put("OperatorID", String.valueOf(operatorID));
		operatorDetails.put("CountryID", String.valueOf(countryID));
		return operatorCountryDataBaseHelper.insertRecord(operatorDetails);
	}

}
