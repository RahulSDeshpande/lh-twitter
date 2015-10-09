package in.jigyasacodes.lh_twitter.ui.frag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.adap.NavDrawerAdapter;
import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.data.NavDrawerItem;

/**
 * Created by rahulsdeshpande on 5/7/15.
 */
public class NavDrawerFrag extends Fragment {

	private static String[] strArrTitles = null;
	private static int[] intArrTitlePics = null;
	private Context mCtx;
	private RecyclerView recyclerView;
	private ActionBarDrawerToggle abdToggle;
	private DrawerLayout drawerLayout;
	private NavDrawerAdapter navDrawerAdap;
	private FragmentDrawerListener fragDrawerListener;

	private View containerView;

	//	private MetaCreds META_CREDS;

	private TextView tvTwitterUserHandle;
	private ImageView ivProfilePic;

	private Auth1 mAuth1;

	public NavDrawerFrag() {

	}

	public static List<NavDrawerItem> getData() {

		List<NavDrawerItem> navDrawerItemList = new ArrayList<>();

		for (int i = 0; i < strArrTitles.length; ++i) {

			NavDrawerItem navDrawerItem = new NavDrawerItem();
			navDrawerItem.setTitle(strArrTitles[i]);
			navDrawerItem.setTitlePic(intArrTitlePics[i]);

			navDrawerItemList.add(navDrawerItem);
		}

		return navDrawerItemList;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		//	mAuth1 = (Auth1) getArguments().getSerializable("AUTH1_SERIALIZABLE");

		strArrTitles = getActivity().getResources().getStringArray(R.array.nav_drawer_labels);
		intArrTitlePics = getActivity().getResources().getIntArray(R.array.nav_drawer_label_pics);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.frag_nav_drawer, container, false);

		tvTwitterUserHandle = (TextView) layout.findViewById(R.id.tvTwitterUserHandle);
		ivProfilePic = (ImageView) layout.findViewById(R.id.ivProfilePic);

		/*
		Toast.makeText(mCtx,"Profile Image URL: "+mAuth1.getMetaCreds().getProfileImageUrl(), Toast.LENGTH_LONG).show();

		tvTwitterUserHandle.setText("@"+mAuth1.getMetaCreds().getScreenName());
		Picasso.with(mCtx).load(mAuth1.getMetaCreds().getProfileImageUrl())
				.error(R.drawable.dp)
				.placeholder(R.drawable.dp)
				.into(ivProfilePic);
		*/

		recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

		navDrawerAdap = new NavDrawerAdapter(getActivity(), getData());
		recyclerView.setAdapter(navDrawerAdap);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		recyclerView.addOnItemTouchListener
				(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {

			@Override
			public void onClick(View view, int position) {
				fragDrawerListener.onDrawerItemSelected(view, position);
				drawerLayout.closeDrawer(containerView);
			}

			@Override
			public void onLongClick(View view, int position) {

			}
		}));

		return layout;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

		this.mCtx = activity.getApplicationContext();

		//	mAuth1 = (Auth1) activity.getIntent().getSerializableExtra("AUTH1_SERIALIZABLE");
	}

	public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {

		containerView = getActivity().findViewById(fragmentId);
		this.drawerLayout = drawerLayout;
		abdToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActivity().invalidateOptionsMenu();
			}

			@Override
			public void onDrawerSlide(View drawerView, float slideOffset) {
				super.onDrawerSlide(drawerView, slideOffset);
				toolbar.setAlpha(1 - slideOffset / 2);
			}
		};

		drawerLayout.setDrawerListener(abdToggle);
		drawerLayout.post(new Runnable() {
			@Override
			public void run() {
				abdToggle.syncState();
			}
		});

	}

	public void setFragDrawerListener(FragmentDrawerListener fragDrawerListener) {
		this.fragDrawerListener = fragDrawerListener;
	}

	public interface ClickListener {

		void onClick(View view, int position);

		void onLongClick(View view, int position);
	}

	public interface FragmentDrawerListener {

		void onDrawerItemSelected(View view, int position);
	}

	class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

		private GestureDetector gestureDetector;
		private ClickListener clickListener;

		public RecyclerTouchListener(FragmentActivity context, final RecyclerView recyclerView, final ClickListener clickListener) {
			this.clickListener = clickListener;
			gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
				@Override
				public boolean onSingleTapUp(MotionEvent e) {
					return true;
				}

				@Override
				public void onLongPress(MotionEvent e) {
					View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
					if (child != null && clickListener != null) {
						clickListener.onLongClick(child, recyclerView.getChildPosition(child));
					}
				}
			});
		}

		@Override
		public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

			View child = rv.findChildViewUnder(e.getX(), e.getY());
			if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
				clickListener.onClick(child, rv.getChildPosition(child));
			}
			return false;
		}

		@Override
		public void onTouchEvent(RecyclerView rv, MotionEvent e) {
		}

		@Override
		public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

		}
	}
}