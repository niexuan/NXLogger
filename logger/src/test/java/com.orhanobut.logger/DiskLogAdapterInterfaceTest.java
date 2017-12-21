package com.nx.logger;

import com.nx.logger.diskLog.DiskLogAdapter;
import com.nx.logger.androidBase.FormatInterface;
import com.nx.logger.androidBase.LogAdapterInterface;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class DiskLogAdapterInterfaceTest {

  @Mock
  FormatInterface formatInterface;

  @Before public void setup() {
    initMocks(this);
  }

  @Test public void isLoggableTrue() throws Exception {
    LogAdapterInterface logAdapterInterface = new DiskLogAdapter(formatInterface);

    assertThat(logAdapterInterface.isLoggable(Logger.VERBOSE, "tag")).isTrue();
  }

  @Test public void isLoggableFalse() throws Exception {
    LogAdapterInterface logAdapterInterface = new DiskLogAdapter(formatInterface) {
      @Override public boolean isLoggable(int priority, String tag) {
        return false;
      }
    };

    assertThat(logAdapterInterface.isLoggable(Logger.VERBOSE, "tag")).isFalse();
  }

  @Test public void log() throws Exception {
    LogAdapterInterface logAdapterInterface = new DiskLogAdapter(formatInterface);

    logAdapterInterface.log(Logger.VERBOSE, "tag", "message");

    verify(formatInterface).log(Logger.VERBOSE, "tag", "message");
  }

}