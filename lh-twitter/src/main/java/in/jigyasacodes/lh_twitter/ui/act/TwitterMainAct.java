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

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.ui.frag.HomeFrag;
import in.jigyasacodes.lh_twitter.ui.frag.NavDrawerFrag;
import in.jigyasacodes.lh_twitter.ui.frag.TimelineFrag;
import in.jigyasacodes.lh_twitter.ui.frag.TweetFrag;


public class TwitterMainAct extends AppCompatActivity implements NavDrawerFrag.FragmentDrawerListener {

	private static String TAG = MainActivity.class.getSimpleName();

	private Toolbar mToolbar;
	private NavDrawerFrag drawerFragment;

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
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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