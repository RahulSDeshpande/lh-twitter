package in.jigyasacodes.lh_twitter.api;

import in.jigyasacodes.lh_twitter.data.usertimeline.UserTimeLine;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by rahulsdeshpande on 1/10/15.
 */
public interface TwitterApi {

		@GET("/")
		UserTimeLine getUserTimeline(@Query("count") int count);



		@GET("/group/{id}/users")
		Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);



		@POST("/users/new")
		Call<User> createUser(@Body User user);

	}
}
