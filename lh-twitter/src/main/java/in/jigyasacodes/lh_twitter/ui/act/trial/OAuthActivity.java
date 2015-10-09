package in.jigyasacodes.lh_twitter.ui.act.trial;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import in.jigyasacodes.lh_twitter.R;
import in.jigyasacodes.lh_twitter.data.Auth1;
import in.jigyasacodes.lh_twitter.ui.act.LHTwitterAccountAct;
import in.jigyasacodes.lh_twitter.util.JSONUtils;

//	↗
public class OAuthActivity extends AppCompatActivity {

	private static final String CALLBACK_URL = "http://jigyasacodes.in";
	private static final String TWITTER_API_URL = "https://api.twitter.com/";
	private static final String TWITTER_VERIFY_CREDENTIALS_URL
			= "https://api.twitter.com/1.1/account/verify_credentials.json";

	private ProgressDialog mProgressDialog = null;
	private String mStrAccessToken = "";
	private WebView mWebView;
	private OAuthService mOauthService;
	private Token mRequestToken, mAccessToken;
	private Response response;
	private String respBody;

	private WebViewClient mWebViewClient = new WebViewClient() {

		@Override
		public void onPageFinished(WebView view, String url) {

			if ((url != null) && (url.startsWith(TWITTER_API_URL))) {

				mWebView.setVisibility(View.VISIBLE);
				mProgressDialog.dismiss();

			} else {

				super.onPageFinished(view, url);
			}
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {

			Log.e("----------------------", "11==============================================");

			if ((url != null) && (url.startsWith(CALLBACK_URL))) {

				mWebView.stopLoading();
				mWebView.setVisibility(View.GONE);

				mProgressDialog.setMessage("Authenticating your credentials with Twitter..");
				mProgressDialog.show();

				Uri uri = Uri.parse(url);

				Log.e("----------------------", "1==============================================");

				final Verifier verifier = new Verifier(uri.getQueryParameter("oauth_verifier"));

				Log.e("----------------------", "2==============================================");

				(new AsyncTask<Void, Void, Token>() {

					@Override
					protected void onPreExecute() {
						super.onPreExecute();

						//	mProgressDialog.setMessage("Authenticating your credentials with Twitter..");
						//	mProgressDialog.show();
					}

					@Override
					protected Token doInBackground(Void... params) {

						Log.e("----------------------", "3==============================================");

						mAccessToken = mOauthService.getAccessToken(mRequestToken, verifier);

						oauthRequest();

						return mAccessToken;
					}

					@Override
					protected void onPostExecute(Token accessToken) {

						mStrAccessToken = accessToken.getToken().trim();

						Log.e("----------------------", "==============================================");
						Log.e("-----ACCESS_TOKEN-----", mStrAccessToken);
						Log.e("----------------------", "==============================================");

						saveTokens(accessToken, mRequestToken);

						//  finish();
						mProgressDialog.dismiss();
					}
				}).execute();

				//  Toast.makeText(OAuthActivity.this, "ACCESS_TOKEN:\n\n" + mAccessToken, Toast.LENGTH_LONG).show();
			} else {

				super.onPageStarted(view, url, favicon);
			}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_login_1);

		mOauthService = new ServiceBuilder()
				.provider(TwitterApi.class)
				.apiKey(this.getString(R.string.api_key))
				.apiSecret(this.getString(R.string.api_secret))
				.callback("http://jigyasacodes.in")
				.build();

		mWebView = (WebView) findViewById(R.id.wvTwitterOAuth);
		mWebView.clearCache(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.getSettings().setDisplayZoomControls(false);
		mWebView.setWebViewClient(mWebViewClient);
		mWebView.setWebChromeClient(new WebChromeClient());

		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setCanceledOnTouchOutside(false);
		mProgressDialog.setCancelable(false);
		//  progressDialog.show();

		startAuthorize();
	}

	private void startAuthorize() {

		(new AsyncTask<Void, Void, String>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();

				mProgressDialog.setMessage("Loading Twitter Login Page..");
				mProgressDialog.show();
			}

			@Override
			protected String doInBackground(Void... params) {

				mRequestToken = mOauthService.getRequestToken();
				return mOauthService.getAuthorizationUrl(mRequestToken);
			}

			@Override
			protected void onPostExecute(String url) {

				mWebView.loadUrl(url);
				//  mProgressDialog.dismiss();
			}
		}).execute();
	}

	private void saveTokens(final Token REQUEST_TOKEN, final Token ACCESS_TOKEN) {

		SharedPreferences sharedPrefs = getSharedPreferences("lh-twitter-tokens", 0);
		SharedPreferences.Editor spEditor = sharedPrefs.edit();

		spEditor.putString("lh-twitter-tokens-api-key", this.getString(R.string.api_key));
		spEditor.putString("lh-twitter-tokens-api-secret", this.getString(R.string.api_secret));

		spEditor.putString("lh-twitter-tokens-request-key", REQUEST_TOKEN.getToken());
		spEditor.putString("lh-twitter-tokens-request-secret", REQUEST_TOKEN.getSecret());

		spEditor.putString("lh-twitter-tokens-access-key", ACCESS_TOKEN.getToken());
		spEditor.putString("lh-twitter-tokens-access-secret", ACCESS_TOKEN.getSecret());

		spEditor.apply();

		//  this.oauthRequest();

		//	Auth.setRequestToken(mRequestToken);
		//	Auth.setAccessToken(mAccessToken);
		//	Auth.setOAuthService(mOauthService);

		Auth1 auth1 = new Auth1();
		auth1.setRequestToken(mRequestToken);
		auth1.setAccessToken(mAccessToken);
		//	auth1.setOAuthService(mOauthService);

		//try {

		//	Auth.setMetaCreds(new Gson().fromJson(respBody, MetaCreds.class));

		//	Gson gson = new Gson();
		//	auth1.setMetaCreds(gson.fromJson(respBody, MetaCreds.class));

		JSONObject jsonObject = JSONUtils.verifyJSONObject(respBody);

		if (jsonObject != null) {

			auth1.setMetaCreds(JSONUtils.parseVerifyCredsJSON(jsonObject));
			Log.e(getClass().getSimpleName() + " -> saveTokens()"
					, "auth1.setMetaCreds() SUCCESSFUL !!\nID :::: " + auth1.getMetaCreds().getId());

		} else {

			Toast.makeText
					(this, "Failed to authorize you with your Twitter credentials..\n\nPlease try again.."
							, Toast.LENGTH_LONG).show();
			finish();
		}

		completeLoginProcess(LHTwitterAccountAct.class, auth1);
	}

	private void completeLoginProcess(Class destinationClass, Auth1 serializableAuth1) {

		Intent i = new Intent(this, destinationClass);

		Bundle b = new Bundle();
		b.putSerializable("AUTH1_SERIALIZABLE", serializableAuth1);

		i.putExtras(b);

		startActivity(i);
	}

	private void oauthRequest() {
		Log.e("----------------------", "1==============================================");

		OAuthRequest request = new OAuthRequest(Verb.GET, TWITTER_VERIFY_CREDENTIALS_URL);

		Log.e("----------------------", "2==============================================");

		mOauthService.signRequest(mAccessToken, request);

		Log.e("----------------------", "3==============================================");

		response = request.send();

		Log.e("----------------------", "4==============================================");
		Log.e("-----RESPONSE-----", respBody = response.getBody());
		Log.e("----------------------", "5==============================================");

		new AsyncTask<Void, Void, String>() {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();

				mProgressDialog.setMessage("Verifying your credentials with Twitter..");
				mProgressDialog.show();
			}

			@Override
			protected String doInBackground(Void... params) {

				Log.e("----------------------", "1==============================================");

				OAuthRequest request = new OAuthRequest(Verb.GET, TWITTER_VERIFY_CREDENTIALS_URL);

				Log.e("----------------------", "2==============================================");

				mOauthService.signRequest(mAccessToken, request);

				Log.e("----------------------", "3==============================================");

				response = request.send();

				return response.getBody();
			}

			@Override
			protected void onPostExecute(final String RESP_BODY) {

				respBody = RESP_BODY;

				Log.e("----------------------", "4==============================================");
				Log.e("-----RESPONSE-----", RESP_BODY);
				Log.e("----------------------", "5==============================================");

				mProgressDialog.dismiss();

				Log.e("----------------------",
						"========================\n"
								+ RESP_BODY + "\n======================");

				/*
				runOnUiThread(new Runnable() {

					public void run() {

						Toast.makeText(OAuthActivity.this,
								"RESPONSE_BODY:\n\n" + RESP_BODY,
								Toast.LENGTH_SHORT)
								.show();

					}
				});
				*/
			}
		};//.execute();
	}

	/*
	private interface OnAccessTokenFetchedListener{

		private void OnAccessTokenFetched(final Token TOKEN);
	}
	*/
}