package org.bdigital.springsocial.gmail.api.impl;

import java.util.Properties;

import net.oauth.OAuthConsumer;
import net.oauth.OAuthServiceProvider;

import org.bdigital.springsocial.gmail.api.Gmail;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.social.oauth1.OAuth1Operations;

import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.smtp.SMTPTransport;


public class GmailTemplate extends AbstractOAuth1ApiBinding implements Gmail {

	private Properties configProp = new Properties();
	private String accessToken;
	private String accessTokenSecret; 
	private GmailOAuth1Operations OAuthOp;
	
	public GmailTemplate() {
		
	}
	
	public GmailTemplate(OAuth1Operations oAuth1Operations, String consumerKey, String consumerSecret,
			String accessToken, String accessTokenSecret) {		
		super(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		
		this.OAuthOp =(GmailOAuth1Operations) oAuth1Operations;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;	
		
		initialise();
	}

	private void initialise() {             
        configProp = PropertyLoader.loadProperties("gmailConfig.properties"); 
	}
	
	public SMTPTransport getConnectionToSmtp(String userEmail) throws Exception {  
    	OAuthServiceProvider ssProvider = new OAuthServiceProvider(
    			OAuthOp.getmProvider().getRequestTokenEndpointUrl(),
    			OAuthOp.getmProvider().getAccessTokenEndpointUrl(),
    			OAuthOp.getmProvider().getAuthorizationWebsiteUrl());    	
    	
    	OAuthConsumer mmConsumer = new OAuthConsumer("",
				OAuthOp.getmConsumer().getConsumerKey(), 
				OAuthOp.getmConsumer().getConsumerSecret(), 
    			ssProvider);
    	
    	XoauthAuthenticator.initialize();
    	
		return XoauthAuthenticator.connectToSmtp(configProp.getProperty("smtp.host"), 
				new Integer(configProp.getProperty("smtp.port")), 
				userEmail, 
				accessToken, 
				accessTokenSecret, 
				mmConsumer,				
				true);	
	}

	 public IMAPSSLStore getConnectionToImap(String userEmail) throws Exception {
	    	OAuthServiceProvider ssProvider = new OAuthServiceProvider(
	    			OAuthOp.getmProvider().getRequestTokenEndpointUrl(),
	    			OAuthOp.getmProvider().getAccessTokenEndpointUrl(),
	    			OAuthOp.getmProvider().getAuthorizationWebsiteUrl());    	
	    	
	    	OAuthConsumer mmConsumer = new OAuthConsumer("",
					OAuthOp.getmConsumer().getConsumerKey(), 
					OAuthOp.getmConsumer().getConsumerSecret(), 
	    			ssProvider);
	    	
	    	XoauthAuthenticator.initialize();
	    	
	    return XoauthAuthenticator.connectToImap(configProp.getProperty("imap.host"), 
				new Integer(configProp.getProperty("imap.port")),
				userEmail, 
				accessToken, 
				accessTokenSecret, 
				mmConsumer, 
				true);
	 }

}
