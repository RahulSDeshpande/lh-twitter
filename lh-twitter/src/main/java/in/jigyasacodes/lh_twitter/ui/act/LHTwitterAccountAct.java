package in.jigyasacodes.lh_twitter.ui.act;

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

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.ui.frag.HomeTimelineFrag;
import in.jigyasacodes.lh_twitter.ui.frag.NavDrawerFrag;
import in.jigyasacodes.lh_twitter.ui.frag.UpdateTweetFrag;
import in.jigyasacodes.lh_twitter.ui.frag.UserAccountFrag;


public class LHTwitterAccountAct extends AppCompatActivity implements NavDrawerFrag.FragmentDrawerListener {

	private static String TAG = LHTwitterAccountAct.class.getSimpleName();
	private static Auth1 mAuth1;
	private Toolbar mToolbar;
	private NavDrawerFrag drawerFragment;

	public LHTwitterAccountAct() {
	}

	public LHTwitterAccountAct(OAuthService oauthService, Token accessToken) {
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

		//	FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		//	ft.replace(R.id.container_navigation_drawer, new NavDrawerFrag());
		//	ft.commit();

		/*
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.container_navigation_drawer, bundleArgsInNavDrawerFrag())
				.commit();
		*/

		drawerFragment = new NavDrawerFrag();
		drawerFragment = (NavDrawerFrag)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

		Log.e("$$$$$$$$$$$$$$",drawerFragment.equals(null)+"");

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
	}

	private NavDrawerFrag bundleArgsInNavDrawerFrag() {

		NavDrawerFrag navDrawerFrag = new NavDrawerFrag();
		//	navDrawerFrag = navDrawerFragTemp;

		Bundle bundleArgs = new Bundle();
		bundleArgs
				.putSerializable("AUTH1_SERIALIZABLE",
						this.getIntent().getExtras().getSerializable("AUTH1_SERIALIZABLE"));

		navDrawerFrag.setArguments(bundleArgs);

		return navDrawerFrag;
	}

	private HomeTimelineFrag bundleArgsInHomeTimelineFrag() {

		HomeTimelineFrag homeTimelineFrag = new HomeTimelineFrag();
		//	navDrawerFrag = navDrawerFragTemp;

		Bundle bundleArgs = new Bundle();
		bundleArgs
				.putSerializable("AUTH1_SERIALIZABLE",
						this.getIntent().getExtras().getSerializable("AUTH1_SERIALIZABLE"));

		homeTimelineFrag.setArguments(bundleArgs);

		return homeTimelineFrag;
	}

	private UpdateTweetFrag bundleArgsInUpdateTweetFrag() {

		UpdateTweetFrag updateTweetFrag = new UpdateTweetFrag();
		//	navDrawerFrag = navDrawerFragTemp;

		Bundle bundleArgs = new Bundle();
		bundleArgs
				.putSerializable("AUTH1_SERIALIZABLE",
						this.getIntent().getExtras().getSerializable("AUTH1_SERIALIZABLE"));

		updateTweetFrag.setArguments(bundleArgs);

		return updateTweetFrag;
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
				fragment = bundleArgsInHomeTimelineFrag();
				title = getString(R.string.title_home_timeline);
				break;
			case 1:
				fragment = bundleArgsInUpdateTweetFrag();
				title = getString(R.string.title_tweet);
				break;
			case 2:
				fragment = new UserAccountFrag();
				title = getString(R.string.title_account);
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

		//	super.onBackPressed();
		super.finish();
	}
}