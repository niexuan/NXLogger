package com.nx.logger;

import android.os.Handler;

import com.nx.logger.diskLog.DiskLogImplement;
import com.nx.logger.androidBase.LogInterface;

import org.junit.Test;

import static com.nx.logger.Logger.DEBUG;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DiskLogImplementTest {

  @Test public void log() {
    Handler handler = mock(Handler.class);
    LogInterface logInterface = new DiskLogImplement(handler);

    logInterface.log(DEBUG, "tag", "message");

    verify(handler).sendMessage(handler.obtainMessage(DEBUG, "message"));
  }

}