package in.jigyasacodes.lh_twitter.data;

import android.content.Context;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.R;

public final class CONSTS {

	public static final String APPLICATION_NAME = "LH Twitter";

	public static final String LOGIN_ERROR_USERNAME = "Username must not be blank";
	public static final String LOGIN_ERROR_Password = "password.length() == 0 !!";

	public static final String NET_ISSUE_AD_TITLE = "You are OFFLINE !!";
	public static final String NET_ISSUE_AD_MESSAGE = "You are OFFLINE !!\n\nPlease switch ON the internet connection.";


	public static OAuthService getOAuthService(Context ctx) {

		return new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey(ctx.getString(R.string.api_key))
				.apiSecret(ctx.getString(R.string.api_secret))
				.callback("http://jigyasacodes.in")
				.build();
	}

	public static final class TWITTER_API {

		public static final String URL_BASE = "https://api.twitter.com/1.1";

		public static final String URL_HOME_TIMELINE = "/statuses/home_timeline.json";
		public static final String URL_UPDATE_TWEET = "/statuses/update.json";

		public static final String QUERY_COUNT = "count=";
		public static final String QUERY_STATUS = "status=";

		//	Readymade Garments !!
		public static final String URL_BASE_HOME_TIMELINE = URL_BASE + URL_HOME_TIMELINE;
		public static final String URL_BASE_UPDATE_TWEET = URL_BASE + URL_UPDATE_TWEET;
	}
}