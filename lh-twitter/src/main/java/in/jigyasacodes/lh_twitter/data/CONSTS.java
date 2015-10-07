package in.jigyasacodes.lh_twitter.data;

public final class CONSTS {

	public static final String APPLICATION_NAME = "LH Twitter";

	public static final String LOGIN_ERROR_USERNAME = "Username must not be blank";
	public static final String LOGIN_ERROR_Password = "password.length() == 0 !!";

	public static final class TWITTER_API {

		public static final String URL_BASE = "https://api.twitter.com/1.1";

		public static final String URL_HOME_TIMELINE = "/statuses/home_timeline.json";
		public static final String URL_UPDATE_TWEET = "/statuses/update.json";

		public static final String QUERY_COUNT = "count=";
		public static final String QUERY_STATUS = "status=";

		//	Readymade Garments !!
		public static final String URL_BASE_HOME_TIMELINE = URL_BASE+URL_HOME_TIMELINE;
		public static final String URL_BASE_UPDATE_TWEET = URL_BASE+URL_UPDATE_TWEET;
	}
}