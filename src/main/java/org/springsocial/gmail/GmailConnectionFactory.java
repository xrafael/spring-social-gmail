package org.springsocial.gmail;

import java.io.UnsupportedEncodingException;

import org.springframework.social.connect.support.OAuth1ConnectionFactory;
import org.springsocial.gmail.api.Gmail;

public class GmailConnectionFactory extends OAuth1ConnectionFactory<Gmail> { 

	public GmailConnectionFactory(String consumerKey, String consumerSecret) throws UnsupportedEncodingException {
		super("gmail", new GmailServiceProvider(consumerKey, consumerSecret), new GmailAdapter());
	}

}
