// cordova-plugin-native-spinner
package com.greybax.spinnerdialog;

import java.util.Stack;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.ProgressBar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class SpinnerDialog extends CordovaPlugin {

  public Stack<ProgressDialog> spinnerDialogStack = new Stack<ProgressDialog>();

  public SpinnerDialog() {
  }

  public boolean execute(String action, String args, final CallbackContext callbackContext) throws JSONException {
    if (action.equals("show")) {

      JSONArray argsArr = new JSONArray(args);
      JSONObject argsObj = new JSONObject(args);
      final String title = "null".equals(argsArr.getString(0)) ? null : argsArr.getString(0);
      final String message = "null".equals(argsArr.getString(1)) ? null : argsArr.getString(1);
      final boolean isFixed = argsArr.getBoolean(2);
                
      final CordovaInterface cordova = this.cordova;
      Runnable runnable = new Runnable() {
        public void run() {
          
          DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
              if (!isFixed) {
                while (!SpinnerDialog.this.spinnerDialogStack.empty()) {
                  SpinnerDialog.this.spinnerDialogStack.pop().dismiss();
                  callbackContext.success();
                }
              }
            }
          };

          int theme = 5; // ProgressDialog.THEME_DEVICE_DEFAULT_LIGHT
          if (argsObj.has("theme")) {
            String themeArg = null;
            try {
              themeArg = argsObj.getString("theme");
            } catch (JSONException e) {
              // e.printStackTrace();
            }
            if ("TRADITIONAL".equals(themeArg)) {
              theme = 1; // ProgressDialog.THEME_TRADITIONAL
            } else if ("DEVICE_DARK".equals(themeArg)) {
              theme = 4; // ProgressDialog.THEME_DEVICE_DEFAULT_DARK
            }
            if ("HOLO_DARK".equals(themeArg)) {
              theme = 2; // ProgressDialog.THEME_HOLO_DARK
            }
            if ("HOLO_LIGHT".equals(themeArg)) {
              theme = 3; // ProgressDialog.THEME_HOLO_LIGHT
            }
          }

          int style = ProgressDialog.STYLE_SPINNER;
          if (argsObj.has("progressStyle")) {
            try {
              if ("HORIZONTAL".equals(argsObj.getString("progressStyle"))) {
                style = ProgressDialog.STYLE_HORIZONTAL;
              }
            } catch (JSONException e) {
              // e.printStackTrace();
            }
          }
          
          ProgressDialog dialog;
          if (isFixed) {
            //If there is a progressDialog yet change the text
            if (!SpinnerDialog.this.spinnerDialogStack.empty()) {
              dialog = SpinnerDialog.this.spinnerDialogStack.peek(); 
              if (title != null) {
                dialog.setTitle(title);	
              }
              if (message!=null) {
                dialog.setMessage(message);	
              }
            }
            else{
              dialog = CallbackProgressDialog.show(cordova.getActivity(), theme, style, title, message, true, false, null, callbackContext);
              SpinnerDialog.this.spinnerDialogStack.push(dialog);
            }
          } else {
            //If there is a progressDialog yet change the text
            if (!SpinnerDialog.this.spinnerDialogStack.empty()) {
              dialog = SpinnerDialog.this.spinnerDialogStack.peek(); 
              if (title != null) {
                dialog.setTitle(title);	
              }
              if (message!=null) {
                dialog.setMessage(message);	
              }	
            }
            else{
              dialog = ProgressDialog.show(cordova.getActivity(), title, message, true, true, onCancelListener);
              SpinnerDialog.this.spinnerDialogStack.push(dialog);
            }
          }
          
          if (title == null && message == null) {
            dialog.setContentView(new ProgressBar(cordova.getActivity()));
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
          }
        }
      };
      this.cordova.getActivity().runOnUiThread(runnable);

    } else if (action.equals("hide")) {
      Runnable runnable = new Runnable() {
        public void run() {
          if (!SpinnerDialog.this.spinnerDialogStack.empty()) {
            SpinnerDialog.this.spinnerDialogStack.pop().dismiss();
          }
        }
      };
      this.cordova.getActivity().runOnUiThread(runnable);
    }
    
    return true;
  }
}
