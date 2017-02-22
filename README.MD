# TimeLineView
一款仿外卖订单状态的开源控件，支持自定义文字，颜色，字体大小等功能

## 显示效果
![][1]
![][2]

## USAGR
布局中添加
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
Activity添加
``` java
TimeLineView mView3=(TimeLineView) findViewById(R.id.timeLineView3);
List<String> data = new ArrayList<>();
data.add("等候支付");
data.add("等候商家接单");
data.add("等候配送");
data.add("等候送达");
mView2.setPointStrings(data, 2);
```



  [1]: /1.png
  [2]: /1.gif