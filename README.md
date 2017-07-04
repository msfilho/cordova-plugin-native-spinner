# msfilho/cordova-plugin-native-spinner-extra

## Platforms
| <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Android_robot.svg/511px-Android_robot.svg.png" width="48px" height="48px" alt="Android logo"> | <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/2000px-Apple_logo_black.svg.png" width="48px" height="48px" alt="iOS logo"> | <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Windows_logo_-_2012.svg/2000px-Windows_logo_-_2012.svg.png" width="48px" height="48px" alt="Windows logo"> | <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Windows_logo_-_2012.svg/2000px-Windows_logo_-_2012.svg.png" width="48px" height="48px" alt="Windows logo">
|:---:|:---:|:---:|:---:|
| 4.1+ ✔ | 10+ ✔ | Windows Phone 8.1+ ✔ | 10+ ✔ |

## Installation

**Latest stable release**: 

* Ionic - `ionic plugin add https://github.com/msfilho/cordova-plugin-native-spinner-extra.git`
* Cordova - `cordova plugin add https://github.com/msfilho/cordova-plugin-native-spinner-extra.git`
* PhoneGap - `phonegap local plugin add https://github.com/msfilho/cordova-plugin-native-spinner-extra.git`

```
cordova plugin add https://github.com/msfilho/cordova-plugin-native-spinner-extra.git
cordova prepare
```

## Includes PR's improvements from greybax/cordova-plugin-native-spinner:

* Themes and Style options

## Methods
- `SpinnerDialog.show`
- `SpinnerDialog.hide`

#### SpinnerDialog.show
    SpinnerDialog.show([title], [message], [cancelCallback])

- __title__: Spinner title (Android only). Optional. _(String)_
- __message__: Spinner message. Optional. _(String)_
- __cancelCallback__: Callback to invoke when spinner cancel event fired (tap or Android hardware back button event). If set, spinner dialog will be fixed, you should explicitly call `SpinnerDialog.hide`. Due to legacy reasons you can provide boolean value (true/false) to set spinner not cancelable. Optional, defaults to `false`. _(Function/Boolean)_
- __options__: `Android` and `iOS` specific options. In `Android` you can use `theme` and `progressStyle`. In `iOS` you can use `overlayOpacity`,  `textColorRed`, `textColorGreen` and `textColorBlue`. _(Object)_

#### SpinnerDialog.hide
    SpinnerDialog.hide();

## Usage

```
// Show spinner dialog
SpinnerDialog.show();

// Show spinner dialog with message
// Note: spinner dialog is cancelable by default
SpinnerDialog.show(null, "message");

// Set spinner dialog fixed
SpinnerDialog.show(null, null, true);

// Set spinner dialog fixed with callback
// Note: callback fires on tap events and Android hardware back button click event
SpinnerDialog.show(null, null, function () {console.log("callback");});

// Set spinner dialog fixed with Theme and Style options (Android only)
SpinnerDialog.show("title", "message", true, { theme: 'DEVICE_LIGHT', progressStyle: 'SPINNER' });

    `theme`: can be one of the following: `TRADITIONAL`, `DEVICE_DARK`, `DEVICE_LIGHT` (default), `HOLO_DARK`, `HOLO_LIGHT`
    `progressStyle`: can be one of the following: `SPINNER` (default), `HORIZONTAL`


// Show spinner dialog with title and message (Android only)
SpinnerDialog.show("title","message");

// Set spinner dialog fixed (cannot be canceled with screen touch or Android hardware button)
SpinnerDialog.show("title","message", true);

// Overlay opacity and text color options (IOS only)
SpinnerDialog.show(null,"Message",true, {overlayOpacity: 0.35,  textColorRed: 1, textColorGreen: 1, textColorBlue: 1}); 

// Change only overlay opacity (IOS only)
SpinnerDialog.show(null,"Message",true,{overlayOpacity:0.70});

// Change only text color (IOS only)
SpinnerDialog.show(null,"message",true, { textColorRed: 0.1, textColorGreen: 0.1, textColorBlue: 1});

// Hide spinner dialog
SpinnerDialog.hide();
```

## Quirks
* Cordova 5.0 or higher is required for Windows 10 support.
* Windows 10 Mobile or Windows Phone 8.1 is required as desktop doesn't support StatusBar.

## License
See "LICENSE".
Based on https://github.com/Paldom/SpinnerDialog and greybax/cordova-plugin-native-spinner with extra options.