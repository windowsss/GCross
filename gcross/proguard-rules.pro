# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
-renamesourcefileattribute SourceFile

#-----------------混淆配置设定------------------------------------------------------------------------
-dontshrink
-dontpreverify
#优化  不优化输入的类文件
-dontoptimize
#包明不混合大小写
-dontusemixedcaseclassnames

-flattenpackagehierarchy
-allowaccessmodification
-printmapping map.txt
#-printmapping build/outputs/mapping/release/mapping.txt

#设置混淆的压缩比率 0 ~ 7,指定代码的压缩级别
-optimizationpasses 5
#混淆时是否记录日志
-verbose
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,*Annotation*,EnclosingMethod

#指定混淆是采用的算法，后面的参数是一个过滤器
#这个过滤器是谷歌推荐的算法，一般不做更改
-optimizations !code/simplification/cast,!field/*,!class/merging/*

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
#忽略警告
-ignorewarnings


#解决在6.0系统出现java.lang.InternalError
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}



#-----------------不需要混淆系统组件等-------------------------------------------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends java.lang.Throwable {*;}
-keep public class * extends java.lang.Exception {*;}
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-dontwarn com.tencent.mm.**
-keep class com.tencent.mm.**{*;}
-dontwarn com.sina.**
-keep class com.sina.**{*;}

-dontwarn com.iflytek.**
-keep class com.iflytek.**{*;}

#-----------------不需要混淆第三方类库------------------------------------------------------------------
#-libraryjars libs/Android-support-v4.jar
-dontwarn android.support.v4.**                                             #去掉警告
-keep class android.support.v4.** { *; }                                    #过滤android.support.v4
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-dontwarn org.xmlpull.v1.**
-keep class org.xmlpull.v1.** { *;}

#realm
#-keep class io.realm.annotations.RealmModule
#-keep @io.realm.annotations.RealmModule class *
#-keep class io.realm.internal.Keep
#-keep @io.realm.internal.Keep class * { *; }
#-dontwarn javax.**
#-dontwarn io.realm.**


#-dontwarn com.squareup.okhttp.**
#-keep class com.squareup.okhttp.** {*;}
#-keep interface com.squareup.okhttp.** {*;}
#
#-keep class com.mcxiaoke.volley.** {*;}

-keep class org.apache.**{*;}                                               #过滤commons-httpclient-3.1.jar

-keep class com.fasterxml.jackson.**{*;}                                    #过滤jackson-core-2.1.4.jar等

#-dontwarn com.lidroid.xutils.**                                             #去掉警告
#-keep class com.lidroid.xutils.**{*;}                                       #过滤xUtils-2.6.14.jar
#-keep class * extends java.lang.annotation.Annotation{*;}                   #这是xUtils文档中提到的过滤掉注解

-dontwarn com.baidu.**                                                      #去掉警告
-dontwarn com.baidu.mapapi.**
-keep class com.baidu.** {*;}                                               #过滤BaiduLBS_Android.jar
-keep class vi.com.gdi.bgl.android.**{*;}
-keep class com.baidu.platform.**{*;}
-keep class com.baidu.location.**{*;}
-keep class com.baidu.vi.**{*;}

#eventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.jeemey.snail.databean.**{*;} #防止bean类被混淆

##---------------End: proguard configuration for Gson  ----------

#-keep class com.jeemey.snail.models.**              #两颗星表示把本包和所含子包下的类名都保持
#-keep class com.jeemey.snail.models.*               #一颗星表示只是保持该包下的类名，而子包下的类名还是会被混淆
#以上方法保持类后，会发现类名虽然未混淆，但里面的具体方法和变量命名还是变了，这时如果既想保持类名，又想保持里面的内容不被混淆，我们就需要以下方法了

-keep class com.jeemey.snail.models.** { *; }       #防止models类被混淆

#保持 Serializable 不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep public class * implements java.io.Serializable {*;}

#----------------保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在------------------------------------
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
-keep class com.cross.gcross.utils.LoginClass.** { *; }
-keep class com.cross.gcross.utils.GCrossUtils.** { *; }
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 由于SDK中的部分代码使用反射来调用构造函数，如果被混淆掉，在运行时会提示"NoSuchMethod"错误
-keepclassmembers class * {
public <init>(org.json.JSONObject);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements android.os.Parcelable {
 public <fields>;
 private <fields>;
}

##不混淆资源类
#-keep public class **.R$*{
#   public static <fields>;
#}


-keep  public class java.util.HashMap {
	public <methods>;
}
-keep  public class java.lang.String {
	public <methods>;
}
-keep  public class java.util.List {
	public <methods>;
}

#-keep class android_serialport_api.*{*;}