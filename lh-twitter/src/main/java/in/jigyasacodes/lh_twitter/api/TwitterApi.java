package in.jigyasacodes.lh_twitter.api;

import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by rahulsdeshpande on 1/10/15.
 */
public interface TwitterApi {

	@GET("/statuses/user_timeline.json")
	MetaHomeTimeline getAllUserTimeline(@Query("count") int count);

	@GET("/statuses/user_timeline.json")
	MetaHomeTimeline getUserTimeline(@Query("count") int count);

	@GET("/statuses/user_timeline.json")
	MetaHomeTimeline getUserTimeline(@Query("count") int count, @Query("since_id") int sinceId);

	@GET("/statuses/update.json")
	MetaHomeTimeline updateStatusTweet(@Query("status") String status);

	@GET("/statuses/update.json")
	MetaHomeTimeline updateStatusTweet(@Query("status") String status, @Query("in_reply_to_status_id") String inReplyToStatusId);

	@GET("/statuses/retweet/{id}.json")
	MetaHomeTimeline retweetATweet(@Path("id") String id, @Query("trim_user") String trimUser);
}