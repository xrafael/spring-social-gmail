package org.springsocial.gmail;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springsocial.gmail.api.Gmail;
import org.springsocial.gmail.api.impl.GmailOAuth1Operations;
import org.springsocial.gmail.api.impl.GmailTemplate;

public class GmailServiceProvider extends AbstractOAuth1ServiceProvider<Gmail> {

    private static String scope = "https://mail.google.com/ https://www.googleapis.com/auth/userinfo#email";
    
	public GmailServiceProvider(String consumerKey, String consumerSecret) throws UnsupportedEncodingException {
		super(consumerKey, consumerSecret, 
				new GmailOAuth1Operations(consumerKey, consumerSecret,
						"https://www.google.com/accounts/OAuthGetRequestToken?scope=" + URLEncoder.encode(scope, "utf-8"),
						"https://www.google.com/accounts/OAuthGetAccessToken",
						"https://www.google.com/accounts/OAuthAuthorizeToken?hd=default"));
	}

	@Override
	public Gmail getApi(String accessToken, String secret) { 

		return new GmailTemplate(getOAuthOperations(), getConsumerKey(), getConsumerSecret(), accessToken, secret);
	}

}
