package com.beltaief.todo.utils;

import android.util.Log;
import timber.log.Timber;

/**
 * Created by wassim on 12/13/16.
 */

public class ReleaseTree extends Timber.Tree {

  private static final int MAX_LOG_LENGTH = 4000;

  @Override protected boolean isLoggable(String tag, int priority) {
    return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO);
  }

  @Override protected void log(int priority, String tag, String message, Throwable t) {
    if (isLoggable(tag, priority)) {

      // Report caught exceptions to Crashlytics
      if (priority == Log.ERROR && t != null) {
        //Crashlytics.log(t);
      }

      // Message is short emough, does not need to be broken into chunks
      if (message.length() < MAX_LOG_LENGTH) {
        if (priority == Log.ASSERT) {
          Log.wtf(tag, message);
        } else {
          Log.println(priority, tag, message);
        }
        return;
      }

      for (int i = 0, length = message.length(); i < length; i++) {
        int newLine = message.indexOf('\n', i);
        newLine = newLine != -1 ? newLine : length;
        do {
          int end = Math.min(newLine, i + MAX_LOG_LENGTH);
          String part = message.substring(i, end);
          if (priority == Log.ASSERT) {
            Log.wtf(tag, part);
          } else {
            Log.println(priority, tag, part);
          }
          i = end;
        } while (i < newLine);
      }
    }
  }
}
