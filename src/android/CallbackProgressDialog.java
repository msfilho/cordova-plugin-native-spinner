// cordova-plugin-native-spinner
package com.greybax.spinnerdialog;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.MotionEvent;

public class CallbackProgressDialog extends ProgressDialog {

  public static CallbackContext callbackContext;

  public CallbackProgressDialog(Context context, int theme) {
    super(context);
  }

  public static CallbackProgressDialog show(Context context, int theme, int style,
      CharSequence title, CharSequence message, boolean indeterminate,
      boolean cancelable, OnCancelListener cancelListener,
      CallbackContext callbackContext) {
    CallbackProgressDialog.callbackContext = callbackContext;
    CallbackProgressDialog dialog = new CallbackProgressDialog(context, theme);
    dialog.setTitle(title);
    dialog.setMessage(message);
    dialog.setIndeterminate(indeterminate);
    dialog.setProgressStyle(style);
    dialog.setCancelable(cancelable);
    dialog.setOnCancelListener(cancelListener);
    dialog.show();
    return dialog;
  }

  private void sendCallback() {
    PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
    pluginResult.setKeepCallback(true);
    callbackContext.sendPluginResult(pluginResult);
  }

  @Override
  public void onBackPressed() {
    sendCallback();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      sendCallback();
      return true;
    }
    return false;
  }

}