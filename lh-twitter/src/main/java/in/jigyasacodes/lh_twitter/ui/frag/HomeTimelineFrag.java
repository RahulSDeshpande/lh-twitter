package in.jigyasacodes.lh_twitter.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.bg.HomeTimelineFetcherTask1;
import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;


/**
 * Created by rahulsdeshpande on 5/7/15.
 */
public class HomeTimelineFrag extends Fragment implements HomeTimelineFetcherTask1.OnHomeTimelineTaskCompleteListener1 {

	private Context mCtx;

	private Auth1 mAuth1;

	public HomeTimelineFrag() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mAuth1 = (Auth1) getArguments().getSerializable("AUTH1_SERIALIZABLE");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.frag_home_timeline, container, false);

		this.fetchHomeTimeline(CONSTS.TWITTER_API.URL_BASE_HOME_TIMELINE);

		// Inflate the layout for this fragment
		return rootView;
	}


	private void fetchHomeTimeline(final String TWITTER_URL) {

		new HomeTimelineFetcherTask1(this, mCtx);
	}

	private void floodHomeTimelineRV(final MetaHomeTimeline META) {

	}

	@Override
	public void onHomeTimelineTaskComplete1(boolean isTResponseSuccessful, MetaHomeTimeline META) {

		if (isTResponseSuccessful && META.equals(null)) {

			floodHomeTimelineRV(META);

		} else {
			Toast.makeText(mCtx,
					"Oops..\n\nCould not fetch Tweets from your Twitter account..\n\nPlease try again..",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		this.mCtx = activity;
	}

	@Override
	public void onDetach() {

		super.onDetach();
	}
}