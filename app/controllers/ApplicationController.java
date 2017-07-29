package controllers;

import models.*;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.libs.json.Json;
import twitter4j.TwitterException;
import twitter4j.Status;

import utils.TwitterApi;

import java.util.*;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.ExpressionList;

public class ApplicationController extends Controller {
  public Result search() throws TwitterException {
  	  Map<String, String> postData = Form.form(SearchWord.class).bindFromRequest().data();
  	  TwitterApi api = new TwitterApi();
  	  List<List<Status>> resultArray = api.loopGetUserTimeline(postData.get("screenName"), 10);
    return ok(views.html.tweetList.render(resultArray));
  }

  public Result index() {
  	  List<User> users = User.find.all();
  	  System.out.print(users);
  	  return ok(views.html.index.render(users));
  }

  public Result create() throws TwitterException {
  	  TwitterApi api = new TwitterApi();
  	  Map<String, String> postData = Form.form(SearchWord.class).bindFromRequest().data();
  	  String screenName = postData.get("screenName");
  	  twitter4j.ResponseList<twitter4j.User> userDates = api.lookupUsers(screenName);
  	  User user = new User(postData.get("screenName"));
  	  for (twitter4j.User data : userDates) {
  	    user.name = data.getName().replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
  	    user.image_url = data.getOriginalProfileImageURL();
  	    Ebean.execute(()->{
      	  user.save();
    	  });
  	  }
  	  return redirect("/");
  }

  public Result fetch(Long id) throws TwitterException {
  	  TwitterApi api = new TwitterApi();
  	  Map<String, String> postData = Form.form(SearchWord.class).bindFromRequest().data();
  	  String screenName = postData.get("screenName");
  	  twitter4j.ResponseList<twitter4j.User> userDates = api.lookupUsers(screenName);
  	  User user = User.finder.byId(id);
  	  for (twitter4j.User data : userDates) {
  	    user.name = data.getName().replaceAll("[^\\u0000-\\uFFFF]", "\uFFFD");
  	    user.image_url = data.getOriginalProfileImageURL();
  	    Ebean.execute(()->{
      	  user.save();
    	  });
  	  }
  	  return redirect("/");
  }
}
