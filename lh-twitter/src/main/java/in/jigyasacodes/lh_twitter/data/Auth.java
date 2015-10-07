package in.jigyasacodes.lh_twitter.data;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import java.io.Serializable;

import in.jigyasacodes.lh_twitter.data.verify_creds.MetaCreds;

/**
 * Created by rahulsdeshpande on 3/10/15.
 */
public class Auth implements Serializable {

	private static Token mRequestToken;
	private static Token mAccessToken;
	private static OAuthService mOAuthService;
	private static MetaCreds mMetaCreds;

	public static Token getRequestToken() {
		return mRequestToken;
	}

	public static void setRequestToken(Token requestToken) {
		Auth.mRequestToken = requestToken;
	}

	public static Token getAccessToken() {
		return mAccessToken;
	}

	public static void setAccessToken(Token accessToken) {
		Auth.mAccessToken = accessToken;
	}

	public static OAuthService getOAuthService() {
		return mOAuthService;
	}

	public static void setOAuthService(OAuthService oauthService) {
		Auth.mOAuthService = oauthService;
	}

	public static MetaCreds getMetaCreds() {
		return mMetaCreds;
	}

	public static void setMetaCreds(MetaCreds metaCreds) {
		Auth.mMetaCreds = metaCreds;
	}
}