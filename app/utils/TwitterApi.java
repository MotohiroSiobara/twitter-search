package utils;

import play.Play;
//import java.net.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApi {
	private Twitter twitterApi;
	public TwitterApi() {
		ConfigurationBuilder configInfo = new ConfigurationBuilder();
		configInfo.setDebugEnabled(true)
		  .setDebugEnabled(true)
      .setOAuthConsumerKey(Play.application().configuration().getString("twitter.authKey"))
      .setOAuthConsumerSecret(Play.application().configuration().getString("twitter.authSecret"))
      .setOAuthAccessToken(Play.application().configuration().getString("twitter.authToken"))
      .setOAuthAccessTokenSecret(Play.application().configuration().getString("twitter.authSecretToken"));
		TwitterFactory tf = new TwitterFactory(configInfo.build());
		Twitter twitterApi = tf.getInstance();
		this.twitterApi = twitterApi;
//		String urlString = "https://api.twitter.com/1.1/statuses/user_timeline.json";
//		try {
//			URL url = new URL(urlString);
//		  URLConnection uc = url.openConnection();
//		  uc.setRequestProperty("authKey", "@IT java-tips URLConnection");
//		} catch (MalformedURLException e) {
//      System.err.println("Invalid URL format: " + urlString);
//      System.exit(-1);
//    } catch (IOException e) {
//      System.err.println("Can't connect to " + urlString);
//      System.exit(-1);
//    }
  }

	public ResponseList<Status> 	getUserTimeline(String screenName) throws TwitterException {
		Paging paging = new Paging(1, 100);
    ResponseList<Status> result = twitterApi.getUserTimeline(screenName, paging);
    return result;
	}
}
