Mini Equalizer Library for Android
==================================

This Android Library project is created to let you use a animated equalizer inside your music related apps.

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Mini%20Equalizer%20Library-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1558)

**Supported Android versions**: Android 4.0+

How to use it
----------------------

Add this to your dependencies:

```groovy
compile 'com.github.claucookie.miniequalizer:library:1.0.0'
```

## Layout

````xml
<es.claucookie.miniequalizerlibrary.EqualizerView
    xmlns:custom="http://schemas.android.com/apk/res-auto"
    android:id="@+id/equalizer_view"
    android:layout_width="30dp"
    android:layout_height="30dp"
    custom:foregroundColor="@color/link_text_material_light"
    custom:animDuration="3500"/>
````

### Attributes

There is some custom attributes you can adjust from the xml:
 - foregroundColor : the equalizer bars color (default is black)
 - animDuration : (millisecs) the animation follows a pattern and the number of loops is infinite. To set the duration of each loop, use this attribute.

## Activity

### Initialization + animation

To start animating the equalizer you should add:

````java
EqualizerView equalizer = (EqualizerView) findViewById(R.id.equalizer_view);
equalizer.animateBars(); // Whenever you want to tart the animation
equalizer.stopBars(); // When you want equalizer stops animating
````
And... that's it!, so simple.

![Example of and app using the equalizer](/screenshots/minieq.png)


Contribution
------------
Every idea, fork and suggestion is very welcome.

Author
--------
Claudia Luque Fernández @claucookie

License
-------
http://opensource.org/licenses/MIT

