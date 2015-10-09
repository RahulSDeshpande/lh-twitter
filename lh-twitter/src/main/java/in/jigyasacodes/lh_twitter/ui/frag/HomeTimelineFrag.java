package in.jigyasacodes.lh_twitter.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.adap.HomeTimelineTweetsAdapter;
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

	private RecyclerView rvHomeTimelineTweets;

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

		View layout = inflater.inflate(R.layout.frag_home_timeline, container, false);

		rvHomeTimelineTweets = (RecyclerView) layout.findViewById(R.id.rvHomeTimelineTweets);

		this.fetchHomeTimeline(CONSTS.TWITTER_API.URL_BASE_HOME_TIMELINE + "?count=" + 20);

		return layout;
	}


	private void fetchHomeTimeline(final String TWITTER_URL) {

		new HomeTimelineFetcherTask1(this, mCtx, mAuth1).execute(TWITTER_URL);
	}

	private void floodHomeTimelineRV(final List<MetaHomeTimeline> META) {

		HomeTimelineTweetsAdapter httAdapter = new HomeTimelineTweetsAdapter(this.mCtx, META);

		rvHomeTimelineTweets.setAdapter(httAdapter);
		rvHomeTimelineTweets.setLayoutManager(new LinearLayoutManager(this.mCtx));

	}

	@Override
	public void onHomeTimelineTaskComplete1(boolean isTResponseSuccessful, List<MetaHomeTimeline> LIST_META_HOME_TIMELINE) {

		if (isTResponseSuccessful && (LIST_META_HOME_TIMELINE != null)) {

			Toast.makeText(mCtx, LIST_META_HOME_TIMELINE.size() + " Tweets loaded !!",
					Toast.LENGTH_LONG).show();
			floodHomeTimelineRV(LIST_META_HOME_TIMELINE);

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