var exec = require('cordova/exec');

module.exports = {
  show: function (title, message, cancelCallback, options) {
    if (cancelCallback == true && typeof cancelCallback !== "function") {
      cancelCallback = function () { };
    }
    var isPlatformIos     = (navigator.userAgent.match(/iPad/i)) == "iPad" || (navigator.userAgent.match(/iPhone/i)) == "iPhone" ? true : false;
    var isPlatformAndroid = (navigator.userAgent.match(/Android/i)) == "Android" ? true : false;
    
    var params = [title, message, !!cancelCallback];
    
    if (isPlatformIos) {
      if (typeof options != "object") {
        options = { overlayOpacity: 0.35, textColorRed: 1, textColorGreen: 1, textColorBlue: 1 }
      }
      params = params.concat([(options.overlayOpacity || 0.35), (options.textColorRed || 1), (options.textColorGreen || 1), (options.textColorBlue || 1)])
    }
    
    if (isPlatformAndroid) {
      if (typeof options != "object") {
        options = { theme: 'DEVICE_LIGHT', progressStyle: 'SPINNER' }
      }
      params = params.concat([(options.theme || 'DEVICE_LIGHT'), (options.progressStyle || 'SPINNER')])
    }
    
    cordova.exec(cancelCallback, null, 'SpinnerDialog', 'show', params);
  },
  hide: function (success, fail) {
    cordova.exec(success, fail, 'SpinnerDialog', 'hide', ["", ""]);
  }
};