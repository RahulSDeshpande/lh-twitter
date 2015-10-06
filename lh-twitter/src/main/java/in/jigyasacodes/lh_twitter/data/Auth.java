package in.jigyasacodes.lh_twitter.data;

import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * Created by rahulsdeshpande on 3/10/15.
 */
public class Auth {

	private static Token mToken;
	private static OAuthService mOAuthService;

	public Token getToken() {
		return mToken;
	}

	public void setToken(Token token) {
		Auth.mToken = token;
	}

	public OAuthService getOAuthService() {
		return mOAuthService;
	}

	public void setOAuthService(OAuthService oauthService) {
		Auth.mOAuthService = oauthService;
	}
}