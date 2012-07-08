package org.springsocial.gmail;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springsocial.gmail.api.Gmail;

public class GmailAdapter implements ApiAdapter<Gmail> {

	public boolean test(Gmail api) {
		// TODO Auto-generated method stub
		return true;
	}

	public void setConnectionValues(Gmail gmail, ConnectionValues values) {
	
		values.setProviderUserId("");
		values.setDisplayName("test");
		values.setProfileUrl("");
		values.setImageUrl("");
		
	}

	public UserProfile fetchUserProfile(Gmail api) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateStatus(Gmail api, String message) {
		// TODO Auto-generated method stub
		
	}

}
