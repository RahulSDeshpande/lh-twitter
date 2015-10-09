package in.jigyasacodes.lh_twitter.bg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.data.Auth;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;

public class HomeTimelineFetcherTask extends AsyncTask<String, Void, MetaHomeTimeline> {

	private static final String TWITTER_VERIFY_CREDENTIALS_URL
			= "https://api.twitter.com/1.1/account/verify_credentials.json";
	private final String PB_MSG_FETCHING_TWEETS = "Fetching your Home Timeline Tweets for you from the Twitter..";
	private OAuthService mOauthService;
	private Token mRequestToken, mAccessToken;
	private Response response;
	private ProgressDialog progressDialog = null;
	private Context ctx = null;
	private OnHomeTimelineTaskCompleteListener onHomeTimelineTaskCompleteListener;
	private int intPaginationValue = 1;

	public HomeTimelineFetcherTask(
			OnHomeTimelineTaskCompleteListener thiss, Context ctx) {

		onHomeTimelineTaskCompleteListener = thiss;
		this.ctx = ctx;
	}

	public HomeTimelineFetcherTask(
			OnHomeTimelineTaskCompleteListener thiss, Context ctx,
			final int INT_PAGINATION_VALUE) {

		onHomeTimelineTaskCompleteListener = thiss;
		this.ctx = ctx;
		this.intPaginationValue = INT_PAGINATION_VALUE;
	}

	@Override
	protected void onPreExecute() {

		super.onPreExecute();

		progressDialog = new ProgressDialog(this.ctx);
		progressDialog.setMessage("Rukiye..");
		progressDialog.setTitle(PB_MSG_FETCHING_TWEETS);
		progressDialog.setIndeterminate(true);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setCancelable(false);
		progressDialog.show();

		//onProgressUpdate();
	}

	@Override
	protected MetaHomeTimeline doInBackground(final String... URLS) {

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
				new OAuthRequest(Verb.GET, CONSTS.TWITTER_API.URL_BASE_HOME_TIMELINE);

		oAuthService.signRequest(accessToken,oAuthRequest);

		try {

			return new Gson()
					.fromJson(oAuthRequest
							.send()
							.getBody()
							, MetaHomeTimeline.class);

		}catch(Exception e) {

			return null;
		}
	}

	@Override
	protected void onPostExecute(final MetaHomeTimeline META) {

		progressDialog.cancel();

		if (!META.equals(null)) {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - if () ->  1");
			onHomeTimelineTaskCompleteListener
					.onHomeTimelineTaskComplete(true, META);

		} else {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - else () ->  1");
			onHomeTimelineTaskCompleteListener
					.onHomeTimelineTaskComplete(false, null);
		}
	}

	public interface OnHomeTimelineTaskCompleteListener {

		void onHomeTimelineTaskComplete(

				final boolean isTResponseSuccessful, final MetaHomeTimeline META);
	}
}