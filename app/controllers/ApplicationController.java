package controllers;

import models.SearchWord;

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

public class ApplicationController extends Controller {
  public Result search() throws TwitterException {
  	  Map<String, String> postData = Form.form(SearchWord.class).bindFromRequest().data();
  	  TwitterApi api = new TwitterApi();
//  	  ArrayList<List> resultArray = new ArrayList<List>();
//  	  for (int page = 1; page < 10; page++) {
//  	  	  List<Status> result = api.getUserTimeline(postData.get("searchWord"), page);
//  	  	  resultArray.add(result);
//  		}
  	  ArrayList<List> resultArray = api.roopGetUserTimeline(postData.get("searchWord"), 10);
    ArrayList<String> textArray = api.getText(resultArray);
    return ok(views.html.tweetList.render(textArray));
  }

  public Result index() {
  	  return ok(views.html.index.render());
  }
}
