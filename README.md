# BubbleTextView
BubbleTextView

## Snapshot
![BubbleTextView](http://7d9pic.com1.z0.glb.clouddn.com/bubblew_view.png)
## Custom Attribute
```xml
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

        <!-- direction for arrow. -->
        <attr name="bubbleArrowDirection">
            <enum name="left" value="1"/>
            <enum name="top" value="2"/>
            <enum name="right" value="3"/>
            <enum name="bottom" value="4"/>
        </attr>

        <!-- direction for arrow. -->
        <attr name = "relativePosition" format = "fraction" />

    </declare-styleable>
```

## Custom Style
```xml
    <style name="LeBubbleTextView">
        <item name="bubbleCornerRadius">@dimen/bubbleView_default_radius</item>
        <item name="bubbleTextSize">@dimen/bubbleView_default_text_size</item>
    </style>

    <style name="LeBubbleTextView.Light">
        <item name="bubbleBackgroundColor">@color/bubbleView_light_background</item>
        <item name="bubbleTextColor">@color/bubbleView_light_text_color</item>
    </style>
    <style name="LeBubbleTextView.Dark">
        <item name="bubbleBackgroundColor">@color/bubbleView_dark_background</item>
        <item name="bubbleTextColor">@color/bubbleView_dark_text_color</item>
    </style>
```

## License

    Copyright 2015

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
