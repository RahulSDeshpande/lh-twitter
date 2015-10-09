package in.jigyasacodes.lh_twitter.adap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.home_timeline.MetaHomeTimeline;


/**
 * Created by rahulsdeshpande on 5/7/15.
 */
public class HomeTimelineTweetsAdapter extends RecyclerView.Adapter<HomeTimelineTweetsAdapter.RecyclerViewHolder> {

	private List<MetaHomeTimeline> mListMetaHomeTimeline = Collections.emptyList();
	private LayoutInflater mLayoutInflater;
	private Context mCtx;

	public HomeTimelineTweetsAdapter(Context context, final List<MetaHomeTimeline> LIST_META_HOME_TIMELINE)
	{
		this.mCtx = context;
		mLayoutInflater = LayoutInflater.from(this.mCtx);

		this.mListMetaHomeTimeline = LIST_META_HOME_TIMELINE;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = mLayoutInflater.inflate(R.layout.tweet_timeline_rv_item_1, parent, false);
		return new RecyclerViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder rvHolder, int position) {

		MetaHomeTimeline  metaHomeTimelineCurrent = mListMetaHomeTimeline.get(position);

		Picasso.with(this.mCtx).load(metaHomeTimelineCurrent.getUser().getProfileImageUrl())
				.error(R.drawable.username)
				.placeholder(R.drawable.username)
				.into(rvHolder.ivProfilePic);

		rvHolder.tvUserHandle.setText(metaHomeTimelineCurrent.getUser().getScreenName());
		rvHolder.tvTweetText.setText(metaHomeTimelineCurrent.getText());
	}

	@Override
	public int getItemCount() {
		return 0;
	}

	public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

		public TextView tvUserHandle, tvTweetText;
		public ImageView ivProfilePic;

		public RecyclerViewHolder(View itemView) {

			super(itemView);

			tvUserHandle = (TextView) itemView.findViewById(R.id.tvUserHandle);
			tvTweetText = (Button) itemView.findViewById(R.id.tvTweetText);
			ivProfilePic = (ImageView) itemView.findViewById(R.id.ivProfilePic);
		}
	}
}