package in.jigyasacodes.lh_twitter.api;

import android.os.AsyncTask;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;
import org.scribe.builder.api.TwitterApi;

/**
 * Created by rahulsdeshpande on 2/10/15.
 */
public class TwitterOAuth extends AsyncTask{

	private static final String PROTECTED_RESOURCE_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";

	@Override
	protected Object doInBackground(Object[] params) {

		OAuthService service = new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey("G7sol9ofG6c3W4vp5tesDdi0m")
				.apiSecret("AF9PiVvdGdE0TDB9qYbsCbM9z2Z82mjmIsiiL5yea1RpLnEtgb")
				.build();

		return null;
	}
}