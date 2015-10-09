package in.jigyasacodes.lh_twitter.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.bg.UpdateTweetTask;
import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.data.update_tweet.MetaUpdateTweet;


public class UpdateTweetFrag
		extends Fragment
		implements UpdateTweetTask.OnUpdateTweetTaskCompleteListener {

	private Context mCtx;

	private EditText etTweet;
	private ImageButton imgBtnTweet;
	private TextView tvTextWatcher;

	private Auth1 mAuth1;

	public UpdateTweetFrag() {
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

		View layout = inflater.inflate(R.layout.frag_update_tweet, container, false);

		return initUIs(layout);
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		this.mCtx = activity;

		//	mAuth1 = (Auth1) activity.getIntent().getSerializableExtra("AUTH1_SERIALIZABLE");
	}

	private View initUIs(View viewLayout) {

		etTweet = (EditText) viewLayout.findViewById(R.id.etTweet);
		imgBtnTweet = (ImageButton) viewLayout.findViewById(R.id.imgBtnTweet);
		tvTextWatcher = (TextView) viewLayout.findViewById(R.id.tvTextWatcher);

		etTweet.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void afterTextChanged(Editable editable) {

				tvTextWatcher.setText(String.valueOf(140 - editable.length()));
			}
		});

		imgBtnTweet.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				if(etTweet.getText().length() > 0) {

					new UpdateTweetTask(UpdateTweetFrag.this, mCtx,mAuth1)
							.execute(CONSTS.TWITTER_API.URL_BASE_UPDATE_TWEET
									, etTweet.getText().toString());
				}
				else
				{
					Toast.makeText(mCtx.getApplicationContext(), "Blank Tweet can't be posted..", Toast.LENGTH_LONG).show();
				}
			}
		});

		return viewLayout;
	}

	@Override
	public void onUpdateTweetTaskComplete(boolean isTResponseSuccessful, MetaUpdateTweet META) {

		Toast.makeText(mCtx.getApplicationContext(), "Your tweet posted successfully !!", Toast.LENGTH_LONG).show();
	}
}