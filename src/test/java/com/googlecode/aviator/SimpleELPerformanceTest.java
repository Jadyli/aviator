package com.googlecode.aviator;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

public class SimpleELPerformanceTest extends TestCase {
  public void test_perf() throws Exception {

    Map<String, Object> ctx = new HashMap<String, Object>();
    ctx.put("${abc}", 3);
    ctx.put("def", 4);
    ctx.put("hi", 5);

    // AviatorEvaluator.setTrace(true);
    for (int i = 0; i < 1; ++i) {
      perf(ctx);
    }
  }

  private void perf(Map<String, Object> ctx) {
    long startMillis = System.currentTimeMillis();
    Expression exp = AviatorEvaluator.compile("(${abc} + def) * hi > 35 ? '哈哈哈' : '呵呵呵'");

    final int COUNT = 1;
    for (int i = 0; i < COUNT; ++i) {
      Object execute = exp.execute(ctx);
      System.out.println(execute);
    }

    long millis = System.currentTimeMillis() - startMillis;

    System.out.println("time : " + NumberFormat.getInstance().format(millis));
  }
}
