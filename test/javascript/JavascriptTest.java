package javascript;
import java.util.Date;
import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class JavascriptTest {
  public static void main(String[] args) throws Exception {
    // 対応しているScriptEngineの情報を出力
    ScriptEngineManager manager = new ScriptEngineManager();
    List<ScriptEngineFactory> engineFactories = manager.getEngineFactories();
    for (ScriptEngineFactory factory : engineFactories) {
    	  System.out.println("Engine:" + factory.getEngineName()
    	  		+ ", Version: " + factory.getEngineVersion());
    	  System.out.println("Language: " + factory.getLanguageName()
    	    + ", Version: " + factory.getLanguageVersion());
    	  System.out.println("Extensions: " + factory.getExtensions());
    	  System.out.println("MimeTypes: " + factory.getMimeTypes());
    	  System.out.println("Names: " + factory.getNames());
    }
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    // Javascriptの実行
    engine.eval("var message = 'Hello, I am Javascript. '");
    engine.eval("print('#1: ' + message)");

    // JavaのオブジェクトをJavascriptに渡してみる
    engine.put("message2", "Hello, I am Java8");
    engine.put("today", new Date());
    engine.eval("print('#2: ' + message2 + ' now : ' + today.toString())");

    // Javascriptからjavaのメソッドを使用
    engine.eval("var obj = Java.type('javascript.JavascriptTest');"
    		+ "print('#3: ' + obj.getMessage());");
    engine.eval("obj.printMessage('Good afternoon.');");

    // JavaからJavascriptの値を取得
    engine.eval("var message3 = 'Good evening.';");
    Object message3 = engine.get("message3");
    System.out.println("#5: " + message3);

    // JavaからJavascriptの関数を呼び出し
    if (engine instanceof Invocable) {
    	  Invocable invocable = (Invocable) engine;
    	  engine.eval("var func = function(arg) { print('#6: ' + arg);}");
    	  invocable.invokeFunction("func", "Good night");
    }
  }

  // メッセージを返す
  public static String getMessage() {
  	  return "Good morning.";
  }

  // メッセージを出力
  public static void printMessage(String message) {
  	  System.out.println("#4: " + message);
  }
}
