# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.google.android.gms.location.** { *; }
-dontwarn com.google.android.gms.location.**

-keep class androidx.wear.** { *; }
-keep class androidx.appcompat.** { *; }
-keep class androidx.compose.** { *; }
-dontwarn androidx.wear.**
-dontwarn androidx.appcompat.**
-dontwarn androidx.compose.**

-keepclasseswithmembers class * {
    @androidx.compose.ui.tooling.preview.Preview <methods>;
}

-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-keep class com.google.accompanist.** { *; }
-dontwarn com.google.accompanist.**

-keep class com.eeos.rocatrun.model.** { *; }
