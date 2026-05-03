# Stack trace için satır numaraları ve kaynak dosya adını koru
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ==================== Kotlin ====================
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}

# ==================== Coroutines ====================
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-dontwarn kotlinx.coroutines.**

# ==================== Retrofit ====================
-keepattributes Signature, InnerClasses, EnclosingMethod
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# ==================== OkHttp ====================
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-dontwarn org.conscrypt.**
-dontwarn org.bouncycastle.**
-dontwarn org.openjsse.**

# ==================== Gson ====================
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

# ==================== Hilt / Dagger ====================
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* <fields>;
    @javax.inject.* <init>(...);
    @dagger.* <fields>;
    @dagger.* <methods>;
}
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection
-dontwarn dagger.internal.codegen.**
-dontwarn com.google.errorprone.annotations.**

# ==================== Room ====================
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# ==================== DataStore ====================
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite* {
    <fields>;
}

# ==================== Coil ====================
-dontwarn coil.**

# ==================== Lottie ====================
-dontwarn com.airbnb.lottie.**
-keep class com.airbnb.lottie.** { *; }

# ==================== Proje modelleri ====================
# API response modellerinin field'ları Gson tarafından okunduğu için korunmalı
-keep class com.base.androidappwithcompose.model.** { *; }
-keep class com.base.androidappwithcompose.data.server.** { *; }
