package in.jigyasacodes.lh_twitter.ui.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.bg.HomeTimelineFetcherTask1;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;
import in.jigyasacodes.lh_twitter.ui.frag.HomeTimelineFrag;
import in.jigyasacodes.lh_twitter.ui.frag.NavDrawerFrag;
import in.jigyasacodes.lh_twitter.ui.frag.UpdateTweetFrag;
import in.jigyasacodes.lh_twitter.ui.frag.UserAccountFrag;


public class TwitterMainAct extends AppCompatActivity implements NavDrawerFrag.FragmentDrawerListener, HomeTimelineFetcherTask1.OnHomeTimelineTaskCompleteListener1 {

	//	private static final String TWITTER_VERIFY_CREDENTIALS_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
	private static String TAG = TwitterMainAct.class.getSimpleName();

	private Toolbar mToolbar;
	private NavDrawerFrag drawerFragment;

	//	private OAuthService oAuthService;
	//	private Token accessToken;
	//	private Response response;

	public TwitterMainAct() {
	}

	public TwitterMainAct(OAuthService oauthService, Token accessToken) {
		//	this.oAuthService = oauthService;
		//	this.accessToken = accessToken;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main_lp);

		mToolbar = (Toolbar) findViewById(R.id.toolbar);

		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		drawerFragment = (NavDrawerFrag)
				getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
		drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
		drawerFragment.setFragDrawerListener(this);

		initUI(0);

		/*
		String API_KEY = getSharedPreferences("lh-twitter-tokens", 0).getString("lh-twitter-tokens-api-key", "N/A");
		String API_SECRET = getSharedPreferences("lh-twitter-tokens", 0).getString("lh-twitter-tokens-api-secret", "N/A");

		String REQUEST_KEY = getSharedPreferences("lh-twitter-tokens", 0).getString("lh-twitter-tokens-request-key", "N/A");
		String REQUEST_TOKEN = getSharedPreferences("lh-twitter-tokens", 0).getString("lh-twitter-tokens-request-secret", "N/A");

		String ACCESS_KEY = getSharedPreferences("lh-twitter-tokens", 0).getString("lh-twitter-tokens-access-key", "N/A");
		String ACCESS_SECRET = getSharedPreferences("lh-twitter-tokens", 0).getString("lh-twitter-tokens-access-secret", "N/A");

		Toast.makeText
				(this, "API, REQUEST & ACCESS KEYs:\n\n"
						+ API_KEY + "\n" + API_SECRET
						+ "\n\n" + REQUEST_KEY + "\n" + REQUEST_TOKEN
						+ "\n\n" + ACCESS_KEY + "\n" + ACCESS_SECRET
						, Toast.LENGTH_LONG).show();
		*/

		this.fetchHomeTimeline(CONSTS.TWITTER_API.URL_BASE_HOME_TIMELINE);
	}

	private void fetchHomeTimeline(final String TWITTER_URL) {

		new HomeTimelineFetcherTask1(this,this.getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		if (id == R.id.action_search) {
			Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDrawerItemSelected(View view, int position) {
		initUI(position);
	}

	private void initUI(int position) {

		Fragment fragment = null;
		String title = getString(R.string.app_name);

		switch (position) {
			case 0:
				fragment = new HomeTimelineFrag();
				title = getString(R.string.title_home);
				break;
			case 1:
				fragment = new UpdateTweetFrag();
				title = getString(R.string.title_tweet);
				break;
			case 2:
				fragment = new UserAccountFrag();
				title = getString(R.string.title_messages);
				break;
			default:
				break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.container_body, fragment);
			fragmentTransaction.commit();

			// set the toolbar title
			getSupportActionBar().setTitle(title);
		}
	}

	private void floodHomeTimelineRV(final MetaHomeTimeline META)
	{

	}

	@Override
	public void OnHomeTimelineTaskComplete1(boolean isTResponseSuccessful, MetaHomeTimeline META) {

		if(isTResponseSuccessful && META.equals(null))
		{

			floodHomeTimelineRV(META);

		}else
		{
			Toast.makeText(this,
					"Oops..\n\nCould not fetch Tweets from your Twitter account..\n\nPlease try again..",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}
}