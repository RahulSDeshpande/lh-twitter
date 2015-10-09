package in.jigyasacodes.lh_twitter.adap;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.NavDrawerItem;

/**
 * Created by rahulsdeshpande on 5/7/15.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder> {

	// Vitthal trial
	private List<NavDrawerItem> listNavDrawer = Collections.emptyList();
	private LayoutInflater layoutInflater;
	private Context ctx;

	public NavDrawerAdapter(Context context, List<NavDrawerItem> data) {

		this.ctx = context;
		layoutInflater = LayoutInflater.from(this.ctx);
		this.listNavDrawer = data;
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View view = layoutInflater.inflate(R.layout.nav_drawer_row_1, parent, false);
		//  View view = layoutInflater.inflate(R.layout.nav_drawer_row, parent, false);
		//  MyViewHolder holder = new MyViewHolder(view);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {

		NavDrawerItem currentNavDrawerItem = listNavDrawer.get(position);

		holder.title.setText(currentNavDrawerItem.getTitle());
		holder.ivTitlePic.setImageResource(currentNavDrawerItem.getTitlePic());
	}

	@Override
	public int getItemCount() {

		return listNavDrawer.size();
	}

	public void delete(int position) {

		listNavDrawer.remove(position);
		notifyItemRemoved(position);
	}

	class MyViewHolder extends RecyclerView.ViewHolder {

		TextView title;
		ImageView ivTitlePic;


		public MyViewHolder(View itemView) {

			super(itemView);

			title = (TextView) itemView.findViewById(R.id.title);
			ivTitlePic = (ImageView) itemView.findViewById(R.id.ivTitlePic);
		}
	}
}