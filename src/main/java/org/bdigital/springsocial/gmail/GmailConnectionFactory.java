package org.bdigital.springsocial.gmail;

import java.io.UnsupportedEncodingException;

import org.bdigital.springsocial.gmail.api.Gmail;
import org.springframework.social.connect.support.OAuth1ConnectionFactory;

public class GmailConnectionFactory extends OAuth1ConnectionFactory<Gmail> { 

	public GmailConnectionFactory(String consumerKey, String consumerSecret) throws UnsupportedEncodingException {
		super("gmail", new GmailServiceProvider(consumerKey, consumerSecret), new GmailAdapter());
	}

}
