/**
 * Copyright (C) 2015 Rahul S Deshpande & Jigyasa Codes
 *
 * RECIPROCAL PUBLIC LICENSE
 * 
 * Unless explicitly acquired and licensed from Licensor under
 * another license, the contents of this file are subject to the
 * Reciprocal Public License ("RPL") Version 1.3, or subsequent
 * versions as allowed by the RPL, and You may not copy or use this
 * file in either source code or executable form, except in
 * compliance with the terms and conditions of the RPL.
 *
 * http://www.technicalpursuit.com/licenses/RPL_1.3.html
 * 
 * All software distributed under the RPL is provided strictly on an
 * "AS IS" basis, WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR
 * IMPLIED, AND LICENSOR HEREBY DISCLAIMS ALL SUCH WARRANTIES,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE, QUIET ENJOYMENT, OR
 * NON-INFRINGEMENT. See the RPL for specific language governing
 * rights and limitations under the RPL.
 **/

package in.jigyasacodes.lh_twitter.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class Dialogs {

	static private Context ctx;

	private OnNetIssueADYesClickListener onNetIssueADYesClickListener;

	public Dialogs(Context ctx/* , View view */) {

		Dialogs.ctx = ctx;
		this.onNetIssueADYesClickListener = (OnNetIssueADYesClickListener) Dialogs.ctx;
		// this.view = view;
	}

	public void showNetworkIssueAD(
			final String NET_ISSUE_AD_TITLE, final String NET_ISSUE_AD_MESSAGE) {

		Log.e(this.getClass().getSimpleName(), "showNetworkIssueAD() -> 1");

		AlertDialog.Builder builder = new AlertDialog.Builder(ctx/*,
				android.R.style.Theme_Holo_Dialog*/);

		builder.setMessage(NET_ISSUE_AD_MESSAGE)
				.setCancelable(true)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int id) {

								Toast.makeText(
										Dialogs.ctx,
										"Navigating to your Smartphone's Data Connection settings >",
										Toast.LENGTH_LONG).show();

								onNetIssueADYesClickListener
										.onNetIssueADYesClickListener(Settings.ACTION_DATA_ROAMING_SETTINGS);
							}
						})
				.setNegativeButton("Leave it",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int id) {

								//Toast.makeText(Dialogs.ctx, "Alas.. Exiting..",
										//Toast.LENGTH_LONG).show();
								dialog.cancel();
								
								onNetIssueADYesClickListener
								.onNetIssueADYesClickListener(null);
							}
						}).create().show();//setCanceledOnTouchOutside(false);
		//builder.show();

		Log.e(this.getClass().getSimpleName(), "showNetworkIssueAD() -> 2");

		// AlertDialog alert = builder.create();
		// alert.show();
	}

	public interface OnNetIssueADYesClickListener {

		void onNetIssueADYesClickListener(
				final String INTENT_SETTINGS_STRING);
	}
}