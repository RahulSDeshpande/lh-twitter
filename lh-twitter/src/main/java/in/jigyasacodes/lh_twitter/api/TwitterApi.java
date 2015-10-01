package in.jigyasacodes.lh_twitter.api;

import in.jigyasacodes.lh_twitter.data.usertimeline.UserTimeLine;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by rahulsdeshpande on 1/10/15.
 */
public interface TwitterApi {

	@GET("/statuses/user_timeline.json")
	UserTimeLine getAllUserTimeline(@Query("count") int count);

	@GET("/statuses/user_timeline.json")
	UserTimeLine getUserTimeline(@Query("count") int count);

	@GET("/statuses/user_timeline.json")
	UserTimeLine getUserTimeline(@Query("count") int count, @Query("since_id") int sinceId);

	@GET("/statuses/update.json")
	UserTimeLine updateStatusTweet(@Query("status") String status);

	@GET("/statuses/update.json")
	UserTimeLine updateStatusTweet(@Query("status") String status, @Query("in_reply_to_status_id") String inReplyToStatusId);

	@GET("/statuses/retweet/{id}.json")
	UserTimeLine retweetATweet(@Path("id") String id, @Query("trim_user") String trimUser);
}