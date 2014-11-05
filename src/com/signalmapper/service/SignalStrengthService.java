package com.signalmapper.service;

import com.signalmapper.activity.R;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class SignalStrengthService extends Service {

	TelephonyManager Tel;
	MyPhoneStateListener MyListener;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Let it continue running until it is stopped.
		MyListener = new MyPhoneStateListener();
		Tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Toast.makeText(getApplicationContext(),
				"Strength of your signal is " + Tel.getNetworkOperator(),
				Toast.LENGTH_SHORT).show();
		Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}

	/* —————————– */
	/* Start the PhoneState listener */
	/* —————————– */
	private class MyPhoneStateListener extends PhoneStateListener {
		/*
		 * Get the Signal strength from the provider, each time there is an
		 * update
		 */
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			super.onSignalStrengthsChanged(signalStrength);
			int strength = signalStrength.getGsmSignalStrength();
			Log.d("Signal Strength is ", String.valueOf(strength));
			

			if (strength < 5) {
				sendNotification(strength);
			}

		}

		@SuppressLint("NewApi")
		private void sendNotification(int strength)

		{
			Intent intent = new Intent();
			PendingIntent pIntent = PendingIntent.getActivity(
					SignalStrengthService.this, 0, intent, 0);
			Notification noti = new Notification.Builder(
					SignalStrengthService.this)
					.setTicker("Signal Strength Notification")
					.setContentTitle("Signal Strength gets low")
					.setContentText(
							"Your signal strength has gone down to " + strength)
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentIntent(pIntent).build();
			noti.flags = Notification.FLAG_AUTO_CANCEL;
			NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
			notificationManager.notify(0, noti);
		}

	};/* End of private Class */

}
