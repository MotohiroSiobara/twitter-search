package controllers;

import models.SearchWord;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.api.libs.json.Json;
import twitter4j.TwitterException;
import twitter4j.Status;
import utils.TwitterApi;

import java.util.List;
import java.util.Map;
import java.util.*;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class ApplicationController extends Controller {
  public Result search() throws TwitterException {
  	  Map<String, String> postData = Form.form(SearchWord.class).bindFromRequest().data();
  	  TwitterApi api = new TwitterApi();
  	  List<Status> searchResult = api.getUserTimeline(postData.get("searchWord"));
    ArrayList<String> textArray = api.getText(searchResult);
    return ok(views.html.tweetList.render(textArray));
  }

  public Result index() {
  	  return ok(views.html.index.render());
  }
}
