package in.jigyasacodes.lh_twitter.ui.frag.act;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.ui.frag.FragAllTweets;


public class MainActivity extends AppCompatActivity {

	private FrameLayout fl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		ft.add(R.id.flMain, new FragAllTweets(), "FragAllTweets");
	}
}