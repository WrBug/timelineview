# TimeLineView
一款仿外卖订单状态的开源控件，支持自定义文字，颜色，字体大小等功能

# 显示效果
![][1]
![][2]

# USAGE
### Gradle

``` gradle
compile 'com.wrbug:timelineview:1.0.0'
```
### Maven

``` vbscript-html
<dependency>
  <groupId>com.wrbug</groupId>
  <artifactId>timelineview</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```


### 布局中添加

``` xml
    <com.wrbug.opensources.TimeLineView
        android:id="@+id/timeLineView3"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:layout_margin="20dp"
        app:preCircleColor="#0044ff"
        app:preLineColor="#333333"
        app:startedCircleColor="#880000"
        app:startedLineColor="#ff0000"
        app:textSize="12sp"/>
```
### Activity添加
``` java
TimeLineView mView=(TimeLineView) findViewById(R.id.timeLineView3);
List<String> data = new ArrayList<>();
data.add("等候支付");
data.add("等候商家接单");
data.add("等候配送");
data.add("等候送达");
mView.setPointStrings(data, 2);
```
### 添加更多设置

``` java
TimeLineView mView=(TimeLineView) findViewById(R.id.timeLineView3);
mView.builder()
	.pointStrings(txts, 1)
	.startedCircleColor(Color.BLUE)
	.underwayCircleColor(Color.BLUE)
	.preCircleColor(Color.GRAY)
	.startedLineColor(Color.BLUE)
	.preLineColor(Color.GRAY)
	.startedStringColor(Color.BLUE)
	.underwayStringColor(Color.BLUE)
	.preStringColor(Color.GRAY)
	.load();   //开始绘制
```

# 方法/字段说明

|   方法/字段  |   类型  |   说明  |
| --- | --- | --- |
|  pointStrings   |  List<String>/String[]   |  状态文本   |
|  textSize   |   float  |  文本大小   |
|  preLineColor   |   int  |   未开始状态线条颜色  |
| startedLineColor    |   int  |  已进行状态线条颜色   |
|  preCircleColor   |   int  |  未开始状态圆颜色   |
|  underwayCircleColor   |   int  |  进行中状态圆颜色   |
|  startedCircleColor   |   int  |  已进行状态圆颜色   |
|  preStringColor   |  int   |  未开始状态文本颜色   |
|   underwayStringColor  |   int  |  进行中状态文本颜色   |
|  startedStringColor   |  int   |  已进行状态文本颜色   |
|  radius   |  int   |  圆半径   |
|   lineWidth  |  float   |  线条宽度   |


# License

	Copyright 2017, Yalantis

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

	   http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.


  [1]: /1.png
  [2]: /1.gif