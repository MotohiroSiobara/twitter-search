package controllers;

import models.SearchWord;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.libs.json.Json;
import twitter4j.TwitterException;
import utils.TwitterApi;

import java.util.List;
import java.util.Map;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class ApplicationController extends Controller {
  public Result search() throws TwitterException {
  	  Map<String, String> postData = Form.form(SearchWord.class).bindFromRequest().data();
  	  TwitterApi api = new TwitterApi();
    List<twitter4j.Status> searchResult = api.getUserTimeline(postData.get("searchWord"));
    return ok(views.html.tweetList.render(searchResult));
  }

  public Result index() {
  	  return ok(views.html.index.render());
  }
}
