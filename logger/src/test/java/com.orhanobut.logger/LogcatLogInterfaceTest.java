package com.nx.logger;

import com.nx.logger.androidLog.AndroidLogImplement;
import com.nx.logger.androidBase.LogInterface;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.nx.logger.Logger.DEBUG;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LogcatLogInterfaceTest {

  @Test public void log() {
    LogInterface logInterface = new AndroidLogImplement();

    logInterface.log(DEBUG, "tag", "message");

    List<ShadowLog.LogItem> logItems = ShadowLog.getLogs();
    assertThat(logItems.get(0).type).isEqualTo(DEBUG);
    assertThat(logItems.get(0).msg).isEqualTo("message");
    assertThat(logItems.get(0).tag).isEqualTo("tag");
  }

}