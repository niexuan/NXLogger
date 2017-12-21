package com.nx.logger;

import com.nx.logger.androidBase.LogAdapterInterface;
import com.nx.logger.printer.PrinterInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoggerTest {

  @Mock
  PrinterInterface printerInterface;

  @Before public void setup() {
    initMocks(this);

    Logger.printer(printerInterface);
  }

  @Test public void log() {
    Throwable throwable = new Throwable();
    Logger.log(Logger.VERBOSE, "tag", "message", throwable);

    verify(printerInterface).log(Logger.VERBOSE, "tag", "message", throwable);
  }

  @Test public void debugLog() {
    Logger.d("message %s", "arg");

    verify(printerInterface).d("message %s", "arg");
  }

  @Test public void verboseLog() {
    Logger.v("message %s", "arg");

    verify(printerInterface).v("message %s", "arg");
  }

  @Test public void warningLog() {
    Logger.w("message %s", "arg");

    verify(printerInterface).w("message %s", "arg");
  }

  @Test public void errorLog() {
    Logger.e("message %s", "arg");

    verify(printerInterface).e((Throwable) null, "message %s", "arg");
  }

  @Test public void errorLogWithThrowable() {
    Throwable throwable = new Throwable("throwable");
    Logger.e(throwable, "message %s", "arg");

    verify(printerInterface).e(throwable, "message %s", "arg");
  }

  @Test public void infoLog() {
    Logger.i("message %s", "arg");

    verify(printerInterface).i("message %s", "arg");
  }

  @Test public void wtfLog() {
    Logger.wtf("message %s", "arg");

    verify(printerInterface).wtf("message %s", "arg");
  }

  @Test public void logObject() {
    Object object = new Object();
    Logger.d(object);

    verify(printerInterface).d(object);
  }

  @Test public void jsonLog() {
    Logger.json("json");

    verify(printerInterface).json("json");
  }

  @Test public void xmlLog() {
    Logger.xml("xml");

    verify(printerInterface).xml("xml");
  }

  @Test public void oneTimeTag() {
    when(printerInterface.t("tag")).thenReturn(printerInterface);

    Logger.t("tag").d("message");

    verify(printerInterface).t("tag");
    verify(printerInterface).d("message");
  }

  @Test public void addAdapter() {
    LogAdapterInterface adapter = mock(LogAdapterInterface.class);
    Logger.addLogAdapter(adapter);

    verify(printerInterface).addAdapter(adapter);
  }

  @Test public void clearLogAdapters() {
    Logger.clearLogAdapters();

    verify(printerInterface).clearLogAdapters();
  }
}
