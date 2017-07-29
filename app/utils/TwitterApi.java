package utils;

import java.util.ArrayList;
import play.Play;
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

	public List<List<Status>> loopGetUserTimeline(String screenName, int maxPage) throws TwitterException {
		List<List<Status>> resultArray = new ArrayList();
	  for (int page = 1; page < maxPage; page++) {
	  	  ResponseList<Status> response = getUserTimeline(screenName, page);
	  	  List<Status> result = exceptRtAndReplyTweets(response);
	  	  resultArray.add(result);
		}
    return resultArray;
	}

	public ResponseList<Status> getUserTimeline(String screenName, int page) throws TwitterException {
		Paging paging = new Paging(page, 200);
		ResponseList<Status> result = twitterApi.getUserTimeline(screenName, paging);
		return result;
	}

//	public User showUser(String screenName) throws TwitterException {
//		User result = twitterApi.showUser(screenName);
//		return result;
//	}

	// RT・Replyを取り除く
	private List<Status> exceptRtAndReplyTweets(ResponseList<Status> tweetList) {
		List<Status> resultTweetList = new ArrayList();
		for (Status tweet : tweetList) {
			if (checkRetweetAndReply(tweet)) {
    	    continue;
      }
			resultTweetList.add(tweet);
		}
		return resultTweetList;
	}

	private boolean checkRetweetAndReply(Status tweet) {
	  if (tweet.isRetweet() || tweet.getInReplyToUserId() > 0) {
	  	  return true;
	  } else {
	  	  return false;
	  }
	}
//
//	public ArrayList<String> getText(ArrayList<List> resultArray) {
//    ArrayList<String> textArray = new ArrayList<String>();
//    for(List<Status> result : resultArray) {
//    	  for (Status tweet : result) {
//    	  		if (checkRetweetAndReply(tweet)) {
//        	  continue;
//        }
//        textArray.add(tweet.getText());
//    	  }
//    }
//    return textArray;
//	}
}
