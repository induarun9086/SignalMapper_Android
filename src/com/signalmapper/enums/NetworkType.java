package com.signalmapper.enums;

import android.telephony.TelephonyManager;

public enum NetworkType {
	
	xRTT(TelephonyManager.NETWORK_TYPE_1xRTT, "2G"), 
	CDMA(TelephonyManager.NETWORK_TYPE_CDMA, "2G"),
	EDGE(TelephonyManager.NETWORK_TYPE_EDGE, "2G"),
	EHRPD(TelephonyManager.NETWORK_TYPE_EHRPD, "3G"),
	EVDO_0(TelephonyManager.NETWORK_TYPE_EVDO_0, "3G"),
	EVDO_A(TelephonyManager.NETWORK_TYPE_EVDO_A, "3G"),
	EVDO_B(TelephonyManager.NETWORK_TYPE_EVDO_B, "3G"),
	GPRS(TelephonyManager.NETWORK_TYPE_GPRS, "2G"),
	HSDPA(TelephonyManager.NETWORK_TYPE_HSDPA, "3G"),
	HSPA(TelephonyManager.NETWORK_TYPE_HSPA, "3G"),
	HSPAP(TelephonyManager.NETWORK_TYPE_HSPAP, "3G"),
	HSUPA(TelephonyManager.NETWORK_TYPE_HSUPA, "3G"),
	IDEN(TelephonyManager.NETWORK_TYPE_IDEN, "2G"),
	LTE(TelephonyManager.NETWORK_TYPE_LTE, "4G"),
	UMTS(TelephonyManager.NETWORK_TYPE_UMTS, "3G"),
	UNKNOWN(TelephonyManager.NETWORK_TYPE_UNKNOWN, "Unknown"),;

	private int value;
	private String label;

	private NetworkType(int value, String label) {
		this.value = value;
		this.label = label;

	}

	public static String getLabel(int value) {
		NetworkType[] networkTypeArr = NetworkType.values();
		for (int i = 0; i < networkTypeArr.length; i++) {
			NetworkType networkType = networkTypeArr[i];
			if (networkType.value == value) {
				return networkType.label;
			}
		}
		return null;
	}

	public static int getValue(String label) {
		NetworkType[] networkTypeArr = NetworkType.values();
		for (int i = 0; i < networkTypeArr.length; i++) {
			NetworkType networkType = networkTypeArr[i];
			if (networkType.label.equalsIgnoreCase( label)) {
				return networkType.value;
			}
		}
		return 0;
	}

}
