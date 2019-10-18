# Android 自定义View
## 一.为什么要自定义控件
1.特定的显示风格

2.处理特有的用户交互

3.优化我们的布局

4.封装等...


## 二.如何自定义view
1.自定义属性的声明和获取

2.测量onMeasure

(1)测量的三种模式:

    Exactly(准确的):精确模式,尺寸的值是多少,那么这个组件的长或宽就是多少.

    At_most(最大):最大模,同时父控件给出一个最大的空间,不能超过此值.

    unspecified(未指定):未指定模式,当前组件,可得到的空间不受限制.

(2)MeasureSpec 可以拿到传入的模式以及传入的值的大小

(3)setMeasuredDimension 测试后为view高宽

(4)requestLayout 当view中的字体大小改变后强制重新测量以及布局


3.绘制onDraw
    
    Paint.Style.FILL设置只绘制图形内容 
    Paint.Style.STROKE设置只绘制图形的边 
    Paint.Style.FILL_AND_STROKE设置都绘制
![画笔的三种样式](https://github.com/747273183/custom_view/blob/a1.png)

4.状态的存储与恢复

## 三.自定义属性声明与获取

1.分析需要的的自定义属性

2.在res/values/attr.xml定义声明

3.layout.xml文件中进行使用

4.在view构造方法中获取






