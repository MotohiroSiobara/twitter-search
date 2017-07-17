package utils;

import java.util.ArrayList;

//import controllers.twitter4j;
//import controllers.twitter4j;
import play.Play;
//import java.net.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.List;

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
  }

	public ResponseList<Status> 	getUserTimeline(String screenName) throws TwitterException {
		Paging paging = new Paging(1, 200);
		ResponseList<Status> result = twitterApi.getUserTimeline(screenName, paging);
    return result;
	}

	// ResponseList<Status> getUserTimelineのtextを返すメソッド
	// RT, リプライを除く
	public ArrayList<String> getText(List<Status> result) {
    ArrayList<String> textArray = new ArrayList<String>();
    for(Status tweet : result) {
    	  if (checkRetweetAndReply(tweet)) {
      	  continue;
      }
      textArray.add(tweet.getText());
    }
    return textArray;
	}

	private boolean checkRetweetAndReply(Status tweet) {
	  if (tweet.isRetweet() || tweet.getInReplyToUserId() > 0) {
	  	  return true;
	  } else {
	  	  return false;
	  }
	}
}
