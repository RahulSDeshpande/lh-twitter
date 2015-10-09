package in.jigyasacodes.lh_twitter.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;
import in.jigyasacodes.lh_twitter.data.home_timeline.User;
import in.jigyasacodes.lh_twitter.data.update_tweet.MetaUpdateTweet;
import in.jigyasacodes.lh_twitter.data.verify_creds.MetaCreds;

public class JSONUtils {

	// public JSONData jsonData = null;
	private String strJSONRaw = null;
	private JSONObject jsonObjMain = null;

	public JSONUtils(final String STR_RAW_JSON) {

		// jsonData = new JSONData(strJSONRaw);
		// jsonData.setStrJSONRaw(this.strJSONRaw);

		this.strJSONRaw = STR_RAW_JSON;
	}

	public JSONUtils(final JSONObject JSON_OBJ_MAIN) {

		// jsonData = new JSONData(strJSONRaw);
		// jsonData.setStrJSONRaw(this.strJSONRaw);

		this.jsonObjMain = JSON_OBJ_MAIN;
	}

	public static JSONObject verifyJSONObject(final String STR_RAW) {

		try {

			final JSONObject JSON_OBJ_MAIN = new JSONObject(STR_RAW);
			Log.e("verifyJSONObject()",
					"RAW RESPONSE data Successfully Parsed to JSON ------------\n"
							+ STR_RAW + "\n------------");

			return JSON_OBJ_MAIN;

		} catch (JSONException e) {

			e.printStackTrace();
			Log.e("verifyJSONObject()",
					"Error parsing JSON data..\n--------\n" + e.toString()
							+ "\n--------");
			return null;
		}
	}

	public static JSONArray verifyJSONArray(final String STR_RAW) {

		try {

			final JSONArray JSON_ARR_MAIN = new JSONArray(STR_RAW);
			Log.e("verifyJSONArray()",
					"RAW RESPONSE data Successfully Parsed to JSON ------------\n"
							+ STR_RAW + "\n------------");

			return JSON_ARR_MAIN;

		} catch (JSONException e) {

			e.printStackTrace();
			Log.e("verifyJSONArray()",
					"Error parsing JSON data..\n--------\n" + e.toString()
							+ "\n--------");
			return null;
		}
	}

	public static MetaCreds parseVerifyCredsJSON(final JSONObject JSON_OBJ_CERDS) {

		MetaCreds metaCreds = new MetaCreds();

		try {

			JSON_OBJ_CERDS.getJSONArray("errors");
			return null;

		} catch (JSONException je) {

			je.printStackTrace();
			Log.e("parseVerifyCredsJSON()",
					"!!!! VALID JSON_OBJ_CERDS !!!!");
		}

		try {
			metaCreds.setIdStr(JSON_OBJ_CERDS.getString("id_str"));
			metaCreds.setName(JSON_OBJ_CERDS.getString("name"));
			metaCreds.setScreenName(JSON_OBJ_CERDS.getString("screen_name"));
			metaCreds.setProfileImageUrl(JSON_OBJ_CERDS.getString("profile_image_url"));

		} catch (JSONException je) {

			je.printStackTrace();
		}
		return metaCreds;
	}

	public static MetaUpdateTweet parseUpdateTweetJSON(final JSONObject JSON_OBJ_UPDATE_TWEET) {

		MetaUpdateTweet metaUpdateTweet = new MetaUpdateTweet();

		try {

			JSON_OBJ_UPDATE_TWEET.getJSONArray("errors");
			return null;

		} catch (JSONException je) {

			je.printStackTrace();
			Log.e("parseUpdateTweetJSON()",
					"!!!! VALID JSON_OBJ_UPDATE_TWEET !!!!");
		}

		try {

			metaUpdateTweet.setIdStr(JSON_OBJ_UPDATE_TWEET.getString("id_str"));
			metaUpdateTweet.setText(JSON_OBJ_UPDATE_TWEET.getString("text"));
			metaUpdateTweet.setCreatedAt(JSON_OBJ_UPDATE_TWEET.getString("created_at"));
			metaUpdateTweet.setSource(JSON_OBJ_UPDATE_TWEET.getString("source"));
			metaUpdateTweet.setRetweetCount(JSON_OBJ_UPDATE_TWEET.getInt("retweet_count"));
			metaUpdateTweet.setRetweeted(JSON_OBJ_UPDATE_TWEET.getBoolean("retweeted"));

		} catch (JSONException e) {

			e.printStackTrace();
		}
		return metaUpdateTweet;
	}

	public static MetaHomeTimeline verifyAndParseHomeTimelineJSON(final String STR_RAW) {

		try {

			JSONObject JSON_OBJ_HOME_TIMELINE = new JSONObject(STR_RAW);
			JSON_OBJ_HOME_TIMELINE.getJSONArray("errors");
			return null;

		} catch (JSONException je) {

			je.printStackTrace();
			Log.e("vAPHomeTimelineJSON()",
					"!!!! NO ERROR in STR_RAW !!!!");
		}

		MetaHomeTimeline metaHomeTimeline = new MetaHomeTimeline();

		try {

			JSONArray JSON_ARR_HOME_TIMELINE = new JSONArray(STR_RAW);

			final int TWEETS_COUNT = JSON_ARR_HOME_TIMELINE.length();

			for (int i = 0; i < TWEETS_COUNT; ++i) {

				final JSONObject JSON_OBJ_TWEET = JSON_ARR_HOME_TIMELINE.getJSONObject(i);


				metaHomeTimeline.setIdStr(JSON_OBJ_TWEET.getString("id_str"));
				metaHomeTimeline.setText(JSON_OBJ_TWEET.getString("text"));
				metaHomeTimeline.setCreatedAt(JSON_OBJ_TWEET.getString("created_at"));
				metaHomeTimeline.setSource(JSON_OBJ_TWEET.getString("source"));

				metaHomeTimeline
						.setUser(JSONUtils.parseHomeTimelineUser(JSON_OBJ_TWEET.getJSONObject("user")));

				metaHomeTimeline.setRetweetCount(JSON_OBJ_TWEET.getInt("retweet_count"));
				metaHomeTimeline.setRetweeted(JSON_OBJ_TWEET.getBoolean("retweeted"));

			}
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return metaHomeTimeline;
	}

	private static User parseHomeTimelineUser(final JSONObject JSON_OBJ_USER) {

		User user = new User();

		try {

			user.setIdStr(JSON_OBJ_USER.getString("id_str"));
			user.setName(JSON_OBJ_USER.getString("name"));
			user.setScreenName(JSON_OBJ_USER.getString("screen_name"));
			user.setProfileImageUrl(JSON_OBJ_USER.getString("profile_image_url"));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return user;
	}
}