package in.jigyasacodes.lh_twitter.bg;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by rahulsdeshpande on 6/10/15.
 */
public class OAuthService extends IntentService
{
	/**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 * @param name Used to name the worker thread, important only for debugging.
	 */
	public OAuthService(String name) {
		super(name);
	}

	@Override
	protected void onHandleIntent(Intent intent) {

	}
}
