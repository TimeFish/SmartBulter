# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\ProfessionalSoftware\Android\AndroidSdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#腾讯bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#网易云捕
-keep class com.netease.nis.bugrpt.** {*;}
# 保留源文件名并保留行号
-keepattributes SourceFile,LineNumberTable