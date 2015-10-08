package in.jigyasacodes.lh_twitter.bg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.data.Auth;
import in.jigyasacodes.lh_twitter.data.update_tweet.MetaUpdateTweet;

public class UpdateTweetTask extends AsyncTask<String, Void, MetaUpdateTweet> {

	private final String PB_MSG_UPDATING_TWEET = "Updating your Tweet on your Twitter Account..";

	private ProgressDialog mProgressDialog = null;
	private Context ctx = null;

	private OnUpdateTweetTaskCompleteListener onUpdateTweetTaskCompleteListener;
	//	private int intPaginationValue = 1;

	public UpdateTweetTask(
			OnUpdateTweetTaskCompleteListener thiss, Context ctx) {

		onUpdateTweetTaskCompleteListener = thiss;
		this.ctx = ctx;
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
		mProgressDialog.show();
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

		OAuthService oAuthService = Auth.getOAuthService();
		//	Token requestToken = Auth.getRequestToken();
		Token accessToken = Auth.getAccessToken();
		OAuthRequest oAuthRequest =
				new OAuthRequest(Verb.POST, URL_AND_TWEET[0] + "?status=" + URL_AND_TWEET[1]);

		oAuthService.signRequest(accessToken, oAuthRequest);

		try {

			return new Gson()
					.fromJson(oAuthRequest
							.send()
							.getBody()
							, MetaUpdateTweet.class);

		} catch (Exception e) {

			return null;
		}
	}

	@Override
	protected void onPostExecute(final MetaUpdateTweet META) {

		mProgressDialog.dismiss();

		if (!META.equals(null)) {

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

		void onUpdateTweetTaskComplete(

			final boolean isTResponseSuccessful, final MetaUpdateTweet META);
	}
}