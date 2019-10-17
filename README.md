# Android 自定义View
## 为什么要自定义控件
1.特定的显示风格
    显示一个非常炫酷的进度条的效果
2.处理特有的用户交互
    app中有一个特殊的拖拽,排序
3.优化我们的布局
    比如我们现在有一个listview,listview中有一个imageview,底部有一个textview对它进行介绍
4.封装等...
    比如我们页面上有一个titlebar,抽取后,便于以后的复用
## 如何自定义view
自定义控件有一个明确定的步骤,所以实现起来并没有想像的那么难.
1.自定义属性的声明和获取
一般情况我们看到一个自定义控件,我们先考虑它的哪些属性是可以抽取的,抽取的目的是便于以后复用,
比如说字体的颜色,字体的大小,我们可以在布局文件中去设置它,所以这个一般叫做自定义view的属性的声明和获取,
就是抽取一些可定制的属性.
2.测量onMeasure
那么属性抽取以后,我们要考虑这个view在页面占居的宽和高,那么这个步骤是通过测量来实现的.
3.绘制onDraw
那么知道了它占居的宽高后,我们就要考虑它的一个样子,它的样子通过onDraw绘制
4.状态的存储与恢复
除此之外就是要观注,view的状态存储与恢复,什么叫状态的存储和恢复呢,
我们知道我们的activity是有可能被置于后台的,置于后台就有一个风险它有可能被系统杀死.
杀死之后,当用户回到我们的activity,activity会进行重建,那么重建就涉及到一个问题:比如用户在
编辑一篇文章,比如说是通过editText进行编辑,不幸的是当用户在进行编辑时来了一个电话
系统由于内存吃紧,将activity回收了,当我们用户重新回到app时,activtiy会重建,但是
如果没有view的存储和恢复机制的话,用户正在编辑的文章就不见了,这将是一个非常差的用户体验.

所以我们系统中的控件都有一些存储与恢复的机制,比如你在EditText中写一些文本,然后你去旋转手机测试它
,当你的activity重建以后,你的文本是不会消失的.

把握好以上四个步骤,一般情况下我们自定义view就没有太大的问题.