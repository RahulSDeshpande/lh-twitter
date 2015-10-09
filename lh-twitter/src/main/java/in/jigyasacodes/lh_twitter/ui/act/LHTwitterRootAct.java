package in.jigyasacodes.lh_twitter.ui.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.CONSTS;
import in.jigyasacodes.lh_twitter.ui.act.trial.OAuthActivity;
import in.jigyasacodes.lh_twitter.ui.dialog.Dialogs;
import in.jigyasacodes.lh_twitter.util.Net;

public class LHTwitterRootAct extends AppCompatActivity implements  Dialogs.OnNetIssueADYesClickListener{

	private static String TAG = LHTwitterRootAct.class.getSimpleName();

	private ImageView ivTwitterLogo;

	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		ivTwitterLogo = (ImageView) findViewById(R.id.ivTwitterLogo);

		btnLogin = (Button) findViewById(R.id.btnLogin);

		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (Net.isNetworkAvailable(LHTwitterRootAct.this)) {

					startActivity(new Intent(LHTwitterRootAct.this, OAuthActivity.class));

				} else {

					isNetworkAvailable(LHTwitterRootAct.this);
				}
			}
		});
	}

	private boolean isNetworkAvailable(Context ctx) {

		if (!Net.isNetworkAvailable(ctx)) {

			Dialogs dialogs = new Dialogs(this);
			dialogs.showNetworkIssueAD(CONSTS.NET_ISSUE_AD_TITLE,
					CONSTS.NET_ISSUE_AD_MESSAGE);

			return false;
		}

		return true;
	}

	@Override
	public void onNetIssueADYesClickListener(String INTENT_SETTINGS_STRING) {

		if (INTENT_SETTINGS_STRING != null) {

			startActivity(new Intent(INTENT_SETTINGS_STRING));

		} else {

			Toast.makeText(this, "Alas.. Exiting..", Toast.LENGTH_LONG).show();
			this.finish();
		}
	}
}