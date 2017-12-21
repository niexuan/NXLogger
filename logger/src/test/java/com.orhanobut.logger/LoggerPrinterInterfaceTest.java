package com.nx.logger;

import com.nx.logger.androidBase.LogAdapterInterface;
import com.nx.logger.printer.LoggerPrinter;
import com.nx.logger.printer.PrinterInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.nx.logger.Logger.ASSERT;
import static com.nx.logger.Logger.DEBUG;
import static com.nx.logger.Logger.ERROR;
import static com.nx.logger.Logger.INFO;
import static com.nx.logger.Logger.VERBOSE;
import static com.nx.logger.Logger.WARN;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.contains;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoggerPrinterInterfaceTest {

  private final PrinterInterface printerInterface = new LoggerPrinter();

  @Mock
  LogAdapterInterface adapter;

  @Before public void setup() {
    initMocks(this);
    when(adapter.isLoggable(any(Integer.class), any(String.class))).thenReturn(true);
    printerInterface.addAdapter(adapter);
  }

  @Test public void logDebug() {
    printerInterface.d("message %s", "sent");

    verify(adapter).log(DEBUG, null, "message sent");
  }

  @Test public void logError() {
    printerInterface.e("message %s", "sent");

    verify(adapter).log(ERROR, null, "message sent");
  }

  @Test public void logErrorWithThrowable() {
    Throwable throwable = new Throwable("exception");

    printerInterface.e(throwable, "message %s", "sent");

    verify(adapter).log(eq(ERROR), isNull(String.class), contains("message sent : java.lang.Throwable: exception"));
  }

  @Test public void logWarning() {
    printerInterface.w("message %s", "sent");

    verify(adapter).log(WARN, null, "message sent");
  }

  @Test public void logInfo() {
    printerInterface.i("message %s", "sent");

    verify(adapter).log(INFO, null, "message sent");
  }

  @Test public void logWtf() {
    printerInterface.wtf("message %s", "sent");

    verify(adapter).log(ASSERT, null, "message sent");
  }

  @Test public void logVerbose() {
    printerInterface.v("message %s", "sent");

    verify(adapter).log(VERBOSE, null, "message sent");
  }

  @Test public void oneTimeTag() {
    printerInterface.t("tag").d("message");

    verify(adapter).log(DEBUG, "tag", "message");
  }

  @Test public void logObject() {
    Object object = "Test";

    printerInterface.d(object);

    verify(adapter).log(DEBUG, null, "Test");
  }

  @Test public void logArray() {
    Object object = new int[]{1, 6, 7, 30, 33};

    printerInterface.d(object);

    verify(adapter).log(DEBUG, null, "[1, 6, 7, 30, 33]");
  }

  @Test public void logStringArray() {
    Object object = new String[]{"a", "b", "c"};

    printerInterface.d(object);

    verify(adapter).log(DEBUG, null, "[a, b, c]");
  }

  @Test public void logMultiDimensionArray() {
    double[][] doubles = {
        {1, 6},
        {1.2, 33},
    };

    printerInterface.d(doubles);

    verify(adapter).log(DEBUG, null, "[[1.0, 6.0], [1.2, 33.0]]");
  }

  @Test public void logList() {
    List<String> list = Arrays.asList("foo", "bar");
    printerInterface.d(list);

    verify(adapter).log(DEBUG, null, list.toString());
  }

  @Test public void logMap() {
    Map<String, String> map = new HashMap<>();
    map.put("key", "value");
    map.put("key2", "value2");

    printerInterface.d(map);

    verify(adapter).log(DEBUG, null, map.toString());
  }

  @Test public void logSet() {
    Set<String> set = new HashSet<>();
    set.add("key");
    set.add("key1");

    printerInterface.d(set);

    verify(adapter).log(DEBUG, null, set.toString());
  }

  @Test public void logJsonObject() {
    printerInterface.json("  {\"key\":3}");

    verify(adapter).log(DEBUG, null, "{\"key\": 3}");
  }

  @Test public void logJsonArray() {
    printerInterface.json("[{\"key\":3}]");

    verify(adapter).log(DEBUG, null, "[{\"key\": 3}]");
  }


  @Test public void logInvalidJsonObject() {
    printerInterface.json("no json");
    printerInterface.json("{ missing end");

    verify(adapter, times(2)).log(ERROR, null, "Invalid Json");
  }

  @Test public void jsonLogEmptyOrNull() {
    printerInterface.json(null);
    printerInterface.json("");

    verify(adapter, times(2)).log(DEBUG, null, "Empty/Null json content");
  }

  @Test public void xmlLog() {
    printerInterface.xml("<xml>Test</xml>");

    verify(adapter).log(DEBUG, null,
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<xml>Test</xml>\n");
  }

  @Test public void invalidXmlLog() {
    printerInterface.xml("xml>Test</xml>");

    verify(adapter).log(ERROR, null, "Invalid xml");
  }

  @Test public void xmlLogNullOrEmpty() {
    printerInterface.xml(null);
    printerInterface.xml("");

    verify(adapter, times(2)).log(DEBUG, null, "Empty/Null xml content");
  }

  @Test public void clearLogAdapters() {
    printerInterface.clearLogAdapters();

    printerInterface.d("");

    verifyZeroInteractions(adapter);
  }

  @Test public void addAdapter() {
    printerInterface.clearLogAdapters();
    LogAdapterInterface adapter1 = mock(LogAdapterInterface.class);
    LogAdapterInterface adapter2 = mock(LogAdapterInterface.class);

    printerInterface.addAdapter(adapter1);
    printerInterface.addAdapter(adapter2);

    printerInterface.d("message");

    verify(adapter1).isLoggable(DEBUG, null);
    verify(adapter2).isLoggable(DEBUG, null);
  }

  @Test public void doNotLogIfNotLoggable() {
    printerInterface.clearLogAdapters();
    LogAdapterInterface adapter1 = mock(LogAdapterInterface.class);
    when(adapter1.isLoggable(DEBUG, null)).thenReturn(false);

    LogAdapterInterface adapter2 = mock(LogAdapterInterface.class);
    when(adapter2.isLoggable(DEBUG, null)).thenReturn(true);

    printerInterface.addAdapter(adapter1);
    printerInterface.addAdapter(adapter2);

    printerInterface.d("message");

    verify(adapter1, never()).log(DEBUG, null, "message");
    verify(adapter2).log(DEBUG, null, "message");
  }

  @Test public void logWithoutMessageAndThrowable() {
    printerInterface.log(DEBUG, null, null, null);

    verify(adapter).log(DEBUG, null, "Empty/NULL log message");
  }

  @Test public void logWithOnlyThrowableWithoutMessage() {
    Throwable throwable = new Throwable("exception");
    printerInterface.log(DEBUG, null, null, throwable);

    verify(adapter).log(eq(DEBUG), isNull(String.class), contains("java.lang.Throwable: exception"));
  }
}