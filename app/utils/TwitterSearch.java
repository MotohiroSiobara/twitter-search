package utils;

import play.Play;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSearch {
  public static ResponseList<Status> searchTweet(String searchWord) throws TwitterException {
  	  ConfigurationBuilder configInfo = new ConfigurationBuilder();
  	  // application.confからTwitterの設定を読み込む
  	  configInfo.setDebugEnabled(true)
  	    .setOAuthConsumerKey(Play.application().configuration().getString("twitter.authKey"))
  	    .setOAuthConsumerSecret(Play.application().configuration().getString("twitter.authSecret"))
  	    .setOAuthAccessToken(Play.application().configuration().getString("twitter.authToken"))
  	    .setOAuthAccessTokenSecret(Play.application().configuration().getString("twitter.authSecretToken"));
  	  TwitterFactory tf = new TwitterFactory(configInfo.build());
  	  Twitter twitter = tf.getInstance();
  	  Query query = new Query();
  	  query.setQuery(searchWord);
  	  ResponseList<Status> result = twitter.getUserTimeline(searchWord);
  	  return result;
  }
}
