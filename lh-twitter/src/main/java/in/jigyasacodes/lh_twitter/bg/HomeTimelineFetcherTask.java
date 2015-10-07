package in.jigyasacodes.lh_twitter.bg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

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
		// onProgressUpdate();

		final JSONObject JSON_OBJ_MAIN = fetchJSON(URLS[0]);

		if (!JSON_OBJ_MAIN.equals(null)) {

			Log.e("doInBackground() - if()",
					"RAW RESPONSE data Successfully Parsed to JSON ------------\n"
							+ JSON_OBJ_MAIN.toString() + "\n------------");

			return --;
		}

		return null;
	}

	@Override
	protected void onPostExecute(final MetaHomeTimeline META) {

		progressDialog.cancel();

		if (!META.equals(null)) {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - if () ->  1");
			onHomeTimelineTaskCompleteListener
					.OnHomeTimelineTaskComplete(true, META);

		} else {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - else () ->  1");
			onHomeTimelineTaskCompleteListener
					.OnHomeTimelineTaskComplete(false, null);
		}
	}

	public interface OnHomeTimelineTaskCompleteListener {

		void OnHomeTimelineTaskComplete(

			final boolean isTResponseSuccessful, final MetaHomeTimeline META);
	}
}