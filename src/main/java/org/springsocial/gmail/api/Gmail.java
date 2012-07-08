package org.springsocial.gmail.api;

import org.springframework.social.ApiBinding;

import com.sun.mail.imap.IMAPSSLStore;
import com.sun.mail.smtp.SMTPTransport;

public interface Gmail extends ApiBinding {
	
	SMTPTransport getConnectionToSmtp(String userMail) throws Exception;
	
	IMAPSSLStore getConnectionToImap(String userMail) throws Exception;

}
