package in.jigyasacodes.lh_twitter.data;

import org.scribe.model.Token;

import java.io.Serializable;

import in.jigyasacodes.lh_twitter.data.verify_creds.MetaCreds;

/**
 * Created by rahulsdeshpande on 3/10/15.
 */
public class Auth1 implements Serializable {

	private Token mRequestToken;
	private Token mAccessToken;
	//	private  OAuthService mOAuthService;
	private MetaCreds mMetaCreds;

	public Token getRequestToken() {
		return mRequestToken;
	}

	public void setRequestToken(Token requestToken) {
		this.mRequestToken = requestToken;
	}

	public Token getAccessToken() {
		return mAccessToken;
	}

	public void setAccessToken(Token accessToken) {
		this.mAccessToken = accessToken;
	}

	/*
	public  OAuthService getOAuthService() {
		return mOAuthService;
	}

	public  void setOAuthService(OAuthService oauthService) {
		this.mOAuthService = oauthService;
	}
	*/

	public MetaCreds getMetaCreds() {
		return mMetaCreds;
	}

	public void setMetaCreds(MetaCreds metaCreds) {
		this.mMetaCreds = metaCreds;
	}
}