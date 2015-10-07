package in.jigyasacodes.lh_twitter.ui.act;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.Auth;
import in.jigyasacodes.lh_twitter.ui.frag.HomeFrag;
import in.jigyasacodes.lh_twitter.ui.frag.NavDrawerFrag;
import in.jigyasacodes.lh_twitter.ui.frag.TimelineFrag;
import in.jigyasacodes.lh_twitter.ui.frag.TweetFrag;


public class TwitterMainAct extends AppCompatActivity implements NavDrawerFrag.FragmentDrawerListener {

	private static final String TWITTER_VERIFY_CREDENTIALS_URL = "https://api.twitter.com/1.1/account/verify_credentials.json";
	private static String TAG = MainActivity.class.getSimpleName();
	private ProgressDialog mProgressDialog = null;

	private Toolbar mToolbar;
	private NavDrawerFrag drawerFragment;

	private OAuthService oAuthService;
	private Token accessToken;
	private Response response;

	public TwitterMainAct() {
	}

	public TwitterMainAct(OAuthService oauthService, Token accessToken) {
		this.oAuthService = oauthService;
		this.accessToken = accessToken;
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

		// display the first navigation drawer view on app launch
		displayView(0);

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

		//  this.oauthRequest();
	}

	private void oauthRequest() {
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);

		(new AsyncTask<Void, Void, Response>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();

				mProgressDialog.setMessage("Verifying your credentials with Twitter..");
				mProgressDialog.show();
			}

			@Override
			protected Response doInBackground(Void... params) {

				Log.e("----------------------", "1==============================================");

				OAuthRequest request = new OAuthRequest(Verb.GET, TWITTER_VERIFY_CREDENTIALS_URL);

				Log.e("----------------------", "2==============================================");

				accessToken = Auth.getAccessToken();
				oAuthService.signRequest(accessToken, request);

				Log.e("----------------------", "3==============================================");

				return request.send();
			}

			@Override
			protected void onPostExecute(Response resp) {

				Log.e("----------------------", "4==============================================");
				Log.e("-----RESPONSE-----", resp.getBody());
				Log.e("----------------------", "5==============================================");

				response = resp;

				mProgressDialog.dismiss();

				/*
				runOnUiThread(new Runnable() {
					public void run() {

						Toast.makeText(TwitterMainAct.this,
								"RESPONSE_BODY:\n\n" + response.getBody(),
								Toast.LENGTH_SHORT)
								.show();
					}
				});
				*/
			}
		}).execute();
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
		displayView(position);
	}

	private void displayView(int position) {

		Fragment fragment = null;
		String title = getString(R.string.app_name);

		switch (position) {
			case 0:
				fragment = new HomeFrag();
				title = getString(R.string.title_home);
				break;
			case 1:
				fragment = new TweetFrag();
				title = getString(R.string.title_tweet);
				break;
			case 2:
				fragment = new TimelineFrag();
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

	@Override
	public void onBackPressed() {

		super.onBackPressed();
	}
}