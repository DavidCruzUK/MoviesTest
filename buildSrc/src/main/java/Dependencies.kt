import version.Versions

object Dependencies {
    const val Kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin}"
    const val CoreKTX = "androidx.core:core-ktx:${Versions.CoreKTX}"
    const val ActivityKTX = "androidx.activity:activity-ktx:${Versions.ActivityKTX}"
    const val AppCompat = "androidx.appcompat:appcompat:${Versions.AppCompat}"
    const val Material = "com.google.android.material:material:${Versions.Material}"
    const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.ConstraintLayout}"

    const val Retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.Retrofit2}"
    const val Retrofit2Gson = "com.squareup.retrofit2:converter-gson:${Versions.Retrofit2}"

    const val Dagger = "com.google.dagger:dagger:${Versions.Dagger}"

    const val Coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines}"
    const val CoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines}"
}