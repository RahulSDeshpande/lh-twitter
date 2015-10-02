package in.jigyasacodes.lh_twitter.data;

/**
 * Created by rahulsdeshpande on 4/7/15.
 */
public class NavDrawerItem {

	private boolean showNotify;
	private String title;
	private int titlePic;
	public NavDrawerItem() {

	}
	public NavDrawerItem(boolean showNotify, String title) {

		this.showNotify = showNotify;
		this.title = title;
	}

	public boolean isShowNotify() {
		return showNotify;
	}

	public void setShowNotify(boolean showNotify) {
		this.showNotify = showNotify;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTitlePic() {
		return titlePic;
	}

	public void setTitlePic(int titlePic) {
		this.titlePic = titlePic;
	}
}