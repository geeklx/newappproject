# 梁肖51CTO博客：http://blog.51cto.com/liangxiao
# Android业务组件库：http://blog.51cto.com/liangxiao/2146536
# APP框架
多渠道打包和闭包，支持分包安装，UI独立，网络请求独立
此项目还有一些不完善的地方，可以留言或如有疑问请看地址：https://blog.csdn.net/qibin0506/article/details/71307301


### 此类放置自定义View和第三方控件集合：（按顺序）

## anroomcrash：验证APP崩溃和内存溢出的方法

## assetsfitandroid：1.拍照上传
                  2.复制assets目录到本地缓存cache目录文件
                  3.访问assets/cache中的文件方法
                  4.缓存购物车写法 写入txt到缓存

## bannerview：防止OOM写法的bannerview

## bannerviewquan：市面大部分bannerview写法

## baseactivitys：加了堆栈管理和678android权限的base类

## cacheutil：清除缓存方法

## caranimation：购物车添加动画（防止多次点击的OOM）

## changelanguage：切换语言

## common：BaseApp BaseAppManager BaseViewHelper

## emptyview：正在加载 暂无数据 有数据 无网络 布局切换

## expandableview：非常好用的Expandableview

## fileprovider：鸿洋的6.0 7.0 8.0 权限管理类

## flowlayout：鸿洋的FlowLayout（支持标签布局选中）

## glidenetpicpressnormal：支持后台返回两张图片 touch显示不同的图片效果

## handleralluse：面试必会的handler所有用法

## likebutton：点赞效果

## loading：加载中（防止OOM）

## musicutils：播放raw util

## networkview：根据WIFI开关来广播切换布局 斗鱼视频联网播放功能

## qcode：高斯生成二维码效果

## recycleviewalluses：最强RecycleView写法（待更新）

## recycleviewgallery：炫酷的RecycleView Gallery

## recycleviewmultitype：一个比较好的第三方RecycleView写法 很稳定

## ringutil：系统声音（待优化）

## scrollview：黑暗之魂3阅读条文效果

## shoppingcar：非常好用 仿饿了么购物车一套代码

## size：dp转换px工具类

## smartbar：返回|主页 工具类 一行解决你的问题

## splash：欢迎页仿猎聘

## statusbar：浸透式topbar

## tablayout：最全tablayout

## toasts：弃用（可参考）

## toasts2：丰富多彩的Toast

## updateapk：XML配置升级APK

## uploadpic：最强微信图片上传（无bug版 修改了loaderManager两次finish的问题）

## usersdk：登录回调处理逻辑跳转

## widget： 1.AlertView：仿IOS弹窗
         2.AutofitTextView：自适应的textview
         3.CircleImageView：圆形ImageView
         4.CircularSeekBar：中规中矩的SeekBar 圆形进度条
         5.SmoothCheckBox：中规中矩的CheckBox
         6.SwitchButton：中规中矩的开关

## zothers：1.AutoHideInputMethodFrameLayout：非常好用的键盘隐藏工具layout
         6.SpannableStringUtils：给textview设置超链接
          ((TextView) helper.getView(R.id.brademo1_tweetText)).setText(SpannableStringUtils.getBuilder(item.getText()).
                         append("点击查看博客链接").setClickSpan(new ClickableSpan() {
                     @Override
                     public void onClick(View widget) {
                         Uri url = Uri.parse("http://blog.51cto.com/liangxiao");
                         Intent intent = new Intent(Intent.ACTION_VIEW);
                         intent.setData(url);
                         mContext.startActivity(intent);
                     }
                 }).create());
         2.ClickableMovementMethod：给textview设置超链接
          ((TextView) helper.getView(R.id.brademo1_tweetText)).setMovementMethod(ClickableMovementMethod.getInstance());
                 helper.getView(R.id.brademo1_tweetText).setFocusable(false);
                 helper.getView(R.id.brademo1_tweetText).setClickable(true);
                 helper.getView(R.id.brademo1_tweetText).setLongClickable(false);
         3.DaojishiUtil：非常好用倒计时的工具类
         4.NoDoubleClickListener：防止double click
         5.SimpleTagImageView：给ImageView打标签

         7.StartHiddenManager：点A B两点跳转逻辑方法

## zuni：非常好的阻尼效果

## 下面是部分效果图：更多用法请移步：http://blog.51cto.com/liangxiao

### ![梁肖51CTO](https://wx4.sinaimg.cn/mw690/60d2c107ly1ftudgzst4og20ba0k0x51.gif "梁肖51CTO")  
### ![梁肖51CTO](https://wx3.sinaimg.cn/mw690/60d2c107ly1ftudgzpoufg20ba0k0jxc.gif "梁肖51CTO")  
### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1ftudgzqnajg20ba0k012m.gif "梁肖51CTO") 
### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1ftnbay0mqbg20dw0opkhi.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx4.sinaimg.cn/mw690/60d2c107ly1ftnbc8mxzyg20dw0opwym.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx3.sinaimg.cn/mw690/60d2c107ly1ftnce3s0mrg20ba0k07ic.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx1.sinaimg.cn/mw690/60d2c107ly1ftncc2d7h3g20ba0k0ajm.gif "梁肖51CTO")
### ![梁肖51CTO](https://wx2.sinaimg.cn/mw690/60d2c107ly1ftnb7pdk7wg20dw0op4qq.gif "梁肖51CTO")
### ![梁肖51CTO](https://s3.51cto.com/wyfs02/M00/9E/B7/wKioL1mVDj6T2v-1AARlCOkAPj8669.gif "梁肖51CTO")

### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531893717234223.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531893822431543.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894411995588.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894417966982.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894423572051.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894430136803.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894448685899.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894462941636.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894466158206.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894471153683.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894477885689.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894545670445.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894563399011.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894577397951.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894591879580.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894597102229.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894697219346.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894702569453.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")
### ![梁肖51CTO](http://s1.51cto.com/images/20180718/1531894707637124.png?x-oss-process=image/watermark,size_16,text_QDUxQ1RP5Y2a5a6i,color_FFFFFF,t_100,g_se,x_10,y_10,shadow_90,type_ZmFuZ3poZW5naGVpdGk= "梁肖51CTO")


























