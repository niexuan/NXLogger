package com.nx.logger;

import com.nx.logger.androidLog.AndroidLogFormatter;
import com.nx.logger.androidBase.FormatInterface;
import com.nx.logger.androidBase.LogInterface;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.nx.logger.Logger.DEBUG;

public class PrettyFormatInterfaceTest {

  private final String threadName = Thread.currentThread().getName();
  private final MockLogInterface logStrategy = new MockLogInterface();
  private final AndroidLogFormatter.Builder builder =
      AndroidLogFormatter.newBuilder().logStrategy(logStrategy);

  //TODO: Check the actual methodCount info
  @Test public void defaultLog() {
    FormatInterface formatInterface = builder.build();

    formatInterface.log(DEBUG, null, "message");

    assertLog(DEBUG)
        .hasTopBorder()
        .hasThread(threadName)
        .hasMiddleBorder()
        .skip()
        .skip()
        .hasMiddleBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  @Test public void logWithoutThreadInfo() {
    FormatInterface formatInterface = builder.showThreadInfo(false).build();

    formatInterface.log(DEBUG, null, "message");

    assertLog(DEBUG)
        .hasTopBorder()
        .skip()
        .skip()
        .hasMiddleBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  @Test public void logWithoutMethodInfo() {
    FormatInterface formatInterface = builder.methodCount(0).build();

    formatInterface.log(DEBUG, null, "message");

    assertLog(DEBUG)
        .hasTopBorder()
        .hasThread(threadName)
        .hasMiddleBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  @Test public void logWithOnlyMessage() {
    FormatInterface formatInterface = builder
        .methodCount(0)
        .showThreadInfo(false)
        .build();

    formatInterface.log(DEBUG, null, "message");

    assertLog(DEBUG)
        .hasTopBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  //TODO: Check the actual methodCount info
  @Test public void logWithCustomMethodOffset() {
    FormatInterface formatInterface = builder
        .methodOffset(2)
        .showThreadInfo(false)
        .build();

    formatInterface.log(DEBUG, null, "message");

    assertLog(DEBUG)
        .hasTopBorder()
        .skip()
        .skip()
        .hasMiddleBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  @Test public void logWithCustomTag() {
    FormatInterface formatInterface = builder
        .tag("custom")
        .build();

    formatInterface.log(DEBUG, null, "message");

    assertLog("custom", DEBUG)
        .hasTopBorder()
        .hasThread(threadName)
        .hasMiddleBorder()
        .skip()
        .skip()
        .hasMiddleBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  @Test public void logWithOneTimeTag() {
    FormatInterface formatInterface = builder
        .tag("custom")
        .build();

    formatInterface.log(DEBUG, "tag", "message");

    assertLog("custom-tag", DEBUG)
        .hasTopBorder()
        .hasThread(threadName)
        .hasMiddleBorder()
        .skip()
        .skip()
        .hasMiddleBorder()
        .hasMessage("message")
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  // TODO: assert values, for now this checks that Logger doesn't crash
  @Test public void logWithExceedingMethodCount() {
    FormatInterface formatInterface = builder
        .methodCount(50)
        .build();

    formatInterface.log(DEBUG, null, "message");
  }

  @Test public void logWithBigChunk() {
    FormatInterface formatInterface = builder.build();

    StringBuilder chunk1 = new StringBuilder();
    for (int i = 0; i < 400; i++) {
      chunk1.append("1234567890");
    }
    StringBuilder chunk2 = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      chunk2.append("ABCDEFGD");
    }

    formatInterface.log(DEBUG, null, chunk1.toString() + chunk2.toString());

    assertLog(DEBUG)
        .hasTopBorder()
        .hasThread(threadName)
        .hasMiddleBorder()
        .skip()
        .skip()
        .hasMiddleBorder()
        .hasMessage(chunk1.toString())
        .hasMessage(chunk2.toString())
        .hasBottomBorder()
        .hasNoMoreMessages();
  }

  private static class MockLogInterface implements LogInterface {
    List<LogAssert.LogItem> logItems = new ArrayList<>();

    @Override public void log(int priority, String tag, String message) {
      logItems.add(new LogAssert.LogItem(priority, tag, message));
    }
  }

  private LogAssert assertLog(int priority) {
    return assertLog(null, priority);
  }

  private LogAssert assertLog(String tag, int priority) {
    return new LogAssert(logStrategy.logItems, tag, priority);
  }
}
