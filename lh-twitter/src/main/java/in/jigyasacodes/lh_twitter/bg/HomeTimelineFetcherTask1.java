package in.jigyasacodes.lh_twitter.bg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.List;

import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;
import in.jigyasacodes.lh_twitter.util.JSONUtils;

public class HomeTimelineFetcherTask1 extends AsyncTask<String, Void, List<MetaHomeTimeline>> {

	private final String PB_MSG_FETCHING_TWEETS = "Fetching your Home Timeline Tweets for you from the Twitter..";
	//	private OAuthService mOauthService;
	//	private Token mRequestToken, mAccessToken;
	//	private Response response;
	private ProgressDialog mProgressDialog = null;
	private Context ctx = null;

	private Auth1 mAuth1;

	private OnHomeTimelineTaskCompleteListener1 onHomeTimelineTaskCompleteListener1;
	//	private int intPaginationValue = 1;

	public HomeTimelineFetcherTask1(
			OnHomeTimelineTaskCompleteListener1 thiss, Context ctx, Auth1 auth1) {

		onHomeTimelineTaskCompleteListener1 = thiss;
		this.ctx = ctx;
		this.mAuth1=auth1;
	}

	public HomeTimelineFetcherTask1(
			OnHomeTimelineTaskCompleteListener1 thiss, Context ctx,
			final int INT_PAGINATION_VALUE) {

		onHomeTimelineTaskCompleteListener1 = thiss;
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
		mProgressDialog.setMessage(PB_MSG_FETCHING_TWEETS);
		mProgressDialog.show();
	}

	@Override
	protected List<MetaHomeTimeline> doInBackground(final String... URLS) {

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

		//	OAuthService oAuthService = Auth.getOAuthService();
		//	Token requestToken = Auth.getRequestToken();

		OAuthService oAuthService = CONSTS.getOAuthService(ctx);

		Token accessToken = mAuth1.getAccessToken();
		OAuthRequest oAuthRequest =
				new OAuthRequest(Verb.GET, URLS[0]);

		oAuthService.signRequest(accessToken, oAuthRequest);

		try {
		/*
			return new Gson()
					.fromJson(oAuthRequest
							.send()
							.getBody()
							, MetaHomeTimeline.class);
		*/
			return JSONUtils.verifyAndParseHomeTimelineJSON(oAuthRequest.send().getBody());

		}catch(Exception e) {

			return null;
		}
	}

	@Override
	protected void onPostExecute(final List<MetaHomeTimeline> LIST_META_HOME_TIMELINE) {

		mProgressDialog.dismiss();

		if (LIST_META_HOME_TIMELINE != null) {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - if () ->  1");
			onHomeTimelineTaskCompleteListener1
					.onHomeTimelineTaskComplete1(true, LIST_META_HOME_TIMELINE);

		} else {

			Log.e("fkkkkkkkkkk--", "onPostExecute() - else () ->  1");
			onHomeTimelineTaskCompleteListener1
					.onHomeTimelineTaskComplete1(false, null);
		}
	}

	public interface OnHomeTimelineTaskCompleteListener1 {

		void onHomeTimelineTaskComplete1(

				final boolean isTResponseSuccessful, final List<MetaHomeTimeline> META);
	}
}