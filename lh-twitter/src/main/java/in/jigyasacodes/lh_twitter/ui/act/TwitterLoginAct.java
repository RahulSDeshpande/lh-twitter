package in.jigyasacodes.lh_twitter.ui.act;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.CONSTS;

public class TwitterLoginAct extends AppCompatActivity {

	private static String TAG = TwitterLoginAct.class.getSimpleName();

	private ImageView ivTwitterLogo;
	private EditText etUsername, etPassword;

	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login);

		ivTwitterLogo = (ImageView) findViewById(R.id.ivTwitterLogo);

		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);

		btnLogin = (Button) findViewById(R.id.btnLogin);

		btnLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (etUsername.getText().length() > 0) {

					if (etPassword.getText().length() > 0) {

						Toast.makeText(TwitterLoginAct.this,
								"Logging in with Username: '"
										+ etUsername.getText().toString().trim()
										+ "'",
								Toast.LENGTH_LONG)
								.show();

					} else {

						etPassword.setError(CONSTS.LOGIN_ERROR_Password);
					}

				} else {

					etUsername.setError(CONSTS.LOGIN_ERROR_USERNAME);
				}
			}

			void setErrorsToEditTexts(EditText etAny, final String ERROR_MSG) {
				etAny.setError(ERROR_MSG);
			}
		});
	}
}