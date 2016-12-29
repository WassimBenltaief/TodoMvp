package com.beltaief.todo.utils;

import timber.log.Timber;

/**
 * Created by wassim on 12/13/16.
 */

public class DebugTree extends Timber.DebugTree {

  @Override protected String createStackElementTag(StackTraceElement element) {
    return super.createStackElementTag(element) + ":" + element.getLineNumber();
  }
}
