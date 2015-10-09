package in.jigyasacodes.lh_twitter.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FontUtils {

	private static final String JCLH_FONT_ASSETS_URL = "fonts/";

	public static final String JCLH_FONT_PACIFICO = JCLH_FONT_ASSETS_URL
			+ "Pacifico.ttf";

	public static final String JCLH_FONT_DOSIS_SEMIBOLD = JCLH_FONT_ASSETS_URL
			+ "Dosis-SemiBold.ttf";

	public static View typeFaceApplier(Context ctx, View view, final String FONT_NAME) {

		Typeface tf = Typeface.createFromAsset(ctx.getAssets(), FONT_NAME);

		if (view instanceof Button) {

			((Button) view).setTypeface(tf);

		} else {

			((TextView) view).setTypeface(tf);
		}

		return view;
	}
}