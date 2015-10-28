# BubbleTextView

- Custom arrow position
- Custom fillet radius
- Custom background color
- Can be placed anywhere
- Two default style
- Two default theme

## Snapshot
![BubbleTextView 2.0](http://7d9pic.com1.z0.glb.clouddn.com/bubblew_view3.png)
![BubbleTextView 1.0](http://7d9pic.com1.z0.glb.clouddn.com/bubblew_view.png)
## Custom Attribute
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="LeBubbleTextView">
        <!-- Corner radius for LeBubbleTextView. -->
        <attr name="bubbleCornerRadius" format="dimension"/>
        <!-- Background color for LeBubbleTextView. -->
        <attr name="bubbleBackgroundColor" format="color"/>
        <!-- text size for LeBubbleTextView. -->
        <attr name="bubbleTextSize" format="dimension"/>
        <!-- text color for LeBubbleTextView. -->
        <attr name="bubbleTextColor" format="color"/>
        <!-- text for LeBubbleTextView. -->
        <attr name="bubbleText" format="string"/>
        <!-- text for LeBubbleTitleView. -->
        <attr name="bubbleTitleText" format="string"/>
        <!-- direction for arrow. -->
        <attr name="bubbleArrowDirection">
            <enum name="left" value="1"/>
            <enum name="top" value="2"/>
            <enum name="right" value="3"/>
            <enum name="bottom" value="4"/>
        </attr>
        <!-- direction for arrow. -->
        <attr name = "relativePosition" format = "fraction" />
        <!-- Press state Background color for LeBubbleTextView. -->
        <attr name="bubbleBackgroundPressColor" format="color"/>
    </declare-styleable>
</resources>
```

## Custom Style
```xml
    <style name="LeBubbleTextView">
        <item name="bubbleBackgroundColor">@color/bubbleView_dark_background</item>
        <item name="bubbleTextColor">@color/bubbleView_dark_text_color</item>
        <item name="bubbleCornerRadius">@dimen/bubbleView_default_radius</item>
        <item name="bubbleTextSize">@dimen/bubbleView_default_text_size</item>
        <item name="bubbleBackgroundPressColor">@color/bubbleView_dark_press_background</item>
    </style>

    <style name="LeBubbleTextView.Light">
        <item name="bubbleBackgroundColor">@color/bubbleView_light_background</item>
        <item name="bubbleTextColor">@color/bubbleView_light_text_color</item>
        <item name="bubbleBackgroundPressColor">@color/bubbleView_light_press_background</item>
    </style>
    <style name="LeBubbleTextView.Dark">
        <item name="bubbleBackgroundColor">@color/bubbleView_dark_background</item>
        <item name="bubbleTextColor">@color/bubbleView_dark_text_color</item>
        <item name="bubbleBackgroundPressColor">@color/bubbleView_dark_press_background</item>
    </style>
```

## License

   Copyright (C) 2015 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
