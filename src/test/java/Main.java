import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.signature.SignatureMethod;

public class Main {

    public static void main(String[] args) throws Exception {

        OAuthConsumer consumer = new DefaultOAuthConsumer(
        		"593766117661-14u2fi2tmlrdqtke51m885t6ghpgf8tg.apps.googleusercontent.com", 
    			"5zSds8DC0MeYOMV49lZH7EvE",
                SignatureMethod.HMAC_SHA1);    			

        String scope = "https://mail.google.com/ "
    		 + "https://www.googleapis.com/auth/userinfo#email"; 

        OAuthProvider provider = new DefaultOAuthProvider(consumer,
                "https://www.google.com/accounts/OAuthGetRequestToken?scope="
                        + URLEncoder.encode(scope, "utf-8"),
                "https://www.google.com/accounts/OAuthGetAccessToken",
                "https://www.google.com/accounts/OAuthAuthorizeToken?hd=default");

        System.out.println("Fetching request token...");

        String authUrl = provider.retrieveRequestToken(OAuth.OUT_OF_BAND);

        System.out.println("Request token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        System.out.println("Now visit:\n" + authUrl
                + "\n... and grant this app authorization");
        System.out.println("Enter the verification code and hit ENTER when you're done:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String verificationCode = br.readLine();

        System.out.println("Fetching access token...");

        provider.retrieveAccessToken(verificationCode.trim());

        System.out.println("Access token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        URL url = new URL("http://www.blogger.com/feeds/default/blogs");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();

        consumer.sign(request);

        System.out.println("Sending request...");
        request.connect();

        System.out.println("Response: " + request.getResponseCode() + " "
                + request.getResponseMessage());
    }
}
