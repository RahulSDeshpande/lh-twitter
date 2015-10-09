package in.jigyasacodes.lh_twitter.bg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.data.update_tweet.MetaUpdateTweet;
import in.jigyasacodes.lh_twitter.util.JSONUtils;

public class UpdateTweetTask extends AsyncTask<String, Void, MetaUpdateTweet> {

	private final String PB_MSG_UPDATING_TWEET = "Updating your Tweet on your Twitter Account..";

	private ProgressDialog mProgressDialog = null;
	private Context ctx = null;

	private Auth1 mAuth1;

	private OnUpdateTweetTaskCompleteListener onUpdateTweetTaskCompleteListener;
	//	private int intPaginationValue = 1;

	public UpdateTweetTask(
			OnUpdateTweetTaskCompleteListener thiss, Context ctx, Auth1 auth1) {

		onUpdateTweetTaskCompleteListener = thiss;
		this.ctx = ctx;
		this.mAuth1 = auth1;
	}

	public UpdateTweetTask(
			OnUpdateTweetTaskCompleteListener thiss, Context ctx,
			final int INT_PAGINATION_VALUE) {

		onUpdateTweetTaskCompleteListener = thiss;
		this.ctx = ctx;
		//	this.intPaginationValue = INT_PAGINATION_VALUE;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		mProgressDialog = new ProgressDialog(this.ctx);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);
		mProgressDialog.setMessage(PB_MSG_UPDATING_TWEET);
		mProgressDialog.show();
	}

	@Override
	protected MetaUpdateTweet doInBackground(final String... URL_AND_TWEET) {

		// //Looper.prepare();

		// This fkn line wasted my 2.5 hrs :O :'( !!
		// onRESTCompleteListener = new MainActivity();

		/**
		 * :O Causes ->
		 *
		 * java.lang.RuntimeException: Can't create handler inside thread that
		 * has not called Looper.prepare()
		 *
		 **/

		OAuthService oAuthService = CONSTS.getOAuthService(ctx);
		//	Token requestToken = Auth.getRequestToken();
		Token accessToken = mAuth1.getAccessToken();
		OAuthRequest oAuthRequest =
				new OAuthRequest(Verb.POST, URL_AND_TWEET[0] + "?status=" + URL_AND_TWEET[1]);

		oAuthService.signRequest(accessToken, oAuthRequest);

		try {

			Log.e("doInBackground()", "UPDATE TWEET - SUCCESSFUL");

			return JSONUtils.parseUpdateTweetJSON
					(JSONUtils.verifyJSONObject(oAuthRequest
							.send()
							.getBody()));

		} catch (Exception e) {

			Log.e("doInBackground()", "UPDATE TWEET - UN-SUCCESSFUL\n" + e.getMessage());
			return null;
		}
	}

	@Override
	protected void onPostExecute(final MetaUpdateTweet META) {

		mProgressDialog.dismiss();

		if (META != null) {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - if () ->  1");
			onUpdateTweetTaskCompleteListener
					.onUpdateTweetTaskComplete(true, META);

		} else {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - else () ->  1");
			onUpdateTweetTaskCompleteListener
					.onUpdateTweetTaskComplete(false, null);
		}
	}

	public interface OnUpdateTweetTaskCompleteListener {

		void onUpdateTweetTaskComplete(final boolean isTResponseSuccessful, final MetaUpdateTweet META);
	}
}