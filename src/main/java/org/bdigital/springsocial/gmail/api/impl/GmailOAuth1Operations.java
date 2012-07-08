package org.bdigital.springsocial.gmail.api.impl;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.signature.SignatureMethod;

import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuth1Version;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.util.MultiValueMap;

public class GmailOAuth1Operations implements OAuth1Operations {

	private OAuthConsumer mConsumer;
	private OAuthProvider mProvider;
	private String rtrURL;
	
	public GmailOAuth1Operations(String consumerKey, String consumerSecret,
			String reqURL, String accURL, String authURL) {
		
		mConsumer = new DefaultOAuthConsumer(consumerKey, consumerSecret, SignatureMethod.HMAC_SHA1);
		mProvider = new DefaultOAuthProvider(mConsumer, reqURL, accURL, authURL);			
	}
	
	public OAuth1Version getVersion() {
		return OAuth1Version.CORE_10_REVISION_A;
	}

	public OAuthToken fetchRequestToken(String callbackUrl,
			MultiValueMap<String, String> additionalParameters) {
		
		try {
			rtrURL = mProvider.retrieveRequestToken(callbackUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new OAuthToken(mConsumer.getToken(), mConsumer.getTokenSecret());
	}

	public String buildAuthorizeUrl(String requestToken,
			OAuth1Parameters parameters) {

		return rtrURL;
	}

	public String buildAuthenticateUrl(String requestToken,
			OAuth1Parameters parameters) {

		return "";
	}

	public OAuthToken exchangeForAccessToken(
			AuthorizedRequestToken requestToken,
			MultiValueMap<String, String> additionalParameters) {

        try {
			mProvider.retrieveAccessToken(requestToken.getVerifier());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        return new OAuthToken(mConsumer.getToken(), mConsumer.getTokenSecret());
	}

	public OAuthConsumer getmConsumer() {
		return mConsumer;
	}

	public void setmConsumer(OAuthConsumer mConsumer) {
		this.mConsumer = mConsumer;
	}

	public OAuthProvider getmProvider() {
		return mProvider;
	}

	public void setmProvider(OAuthProvider mProvider) {
		this.mProvider = mProvider;
	}

	public String getRtrURL() {
		return rtrURL;
	}

	public void setRtrURL(String rtrURL) {
		this.rtrURL = rtrURL;
	}
}
