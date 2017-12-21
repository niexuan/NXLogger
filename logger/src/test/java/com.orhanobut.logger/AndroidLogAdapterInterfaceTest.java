package com.nx.logger;

import com.nx.logger.androidLog.AndroidLogAdapter;
import com.nx.logger.androidBase.FormatInterface;
import com.nx.logger.androidBase.LogAdapterInterface;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.nx.logger.Logger.DEBUG;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AndroidLogAdapterInterfaceTest {

  @Test public void isLoggable() {
    LogAdapterInterface logAdapterInterface = new AndroidLogAdapter();

    assertThat(logAdapterInterface.isLoggable(DEBUG, "tag")).isTrue();
  }

  @Test public void log() {
    FormatInterface formatInterface = mock(FormatInterface.class);
    LogAdapterInterface logAdapterInterface = new AndroidLogAdapter(formatInterface);

    logAdapterInterface.log(DEBUG, null, "message");

    verify(formatInterface).log(DEBUG, null, "message");
  }

}