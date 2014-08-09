-ignorewarnings
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature

-dontoptimize

-keep class javax.inject.** { *; }
-keep class javax.annotation.** { *; }

-keepclasseswithmembers class * { native <methods>; }

-keep public class com.crittercism.**
-keepclassmembers public class com.crittercism.* { *; }

-dontwarn com.squareup.okhttp.**
