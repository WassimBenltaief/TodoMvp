package com.beltaief.todo;

import android.app.Application;
import com.beltaief.todo.utils.DebugTree;
import com.beltaief.todo.utils.ReleaseTree;
import timber.log.Timber;

/**
 * Created by wassim on 12/13/16.
 */

public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      Timber.plant(new ReleaseTree());
    }
  }
}
