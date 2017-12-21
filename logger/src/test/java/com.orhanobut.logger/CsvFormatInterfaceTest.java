package com.nx.logger;

import com.nx.logger.diskLog.DiskLogFormatter;
import com.nx.logger.androidBase.FormatInterface;
import com.nx.logger.androidBase.LogInterface;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class CsvFormatInterfaceTest {

  @Test public void log() {
    FormatInterface formatInterface = DiskLogFormatter.newBuilder()
        .logStrategy(new LogInterface() {
          @Override public void log(int priority, String tag, String message) {
            assertThat(tag).isEqualTo("PRETTY_LOGGER-tag");
            assertThat(priority).isEqualTo(Logger.VERBOSE);
            assertThat(message).contains("VERBOSE,PRETTY_LOGGER-tag,message");
          }
        })
        .build();

    formatInterface.log(Logger.VERBOSE, "tag", "message");
  }

  @Test public void defaultTag() {
    FormatInterface formatInterface = DiskLogFormatter.newBuilder()
        .logStrategy(new LogInterface() {
          @Override public void log(int priority, String tag, String message) {
            assertThat(tag).isEqualTo("PRETTY_LOGGER");
          }
        })
        .build();

    formatInterface.log(Logger.VERBOSE, null, "message");
  }

  @Test public void customTag() {
    FormatInterface formatInterface = DiskLogFormatter.newBuilder()
        .tag("custom")
        .logStrategy(new LogInterface() {
          @Override public void log(int priority, String tag, String message) {
            assertThat(tag).isEqualTo("custom");
          }
        })
        .build();

    formatInterface.log(Logger.VERBOSE, null, "message");
  }
}