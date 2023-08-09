object  Versions {
    const val CORE_KTX = "1.9.0"

    const val ANDROIDX_APPCOMPAT = "1.5.1"
    const val ANDROID_MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"

    const val RXANDROID = "3.0.0"
    const val RXJAVA = "3.1.5"

    const val OKHTTP = "4.10.0"
    const val GSON = "2.10.1"
    const val GSON_CONVERTER = "2.9.0"
    const val RETROFIT = "2.9.0"
    const val CHUCKER = "4.0.0"

    const val KOIN = "3.3.0"

    const val GLIDE = "4.15.1"

    const val MOCKITO = "5.1.1"
    const val MOCKITO_KOTLIN = "5.0.0"

}

object Dependencies {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val CHUCKER_NO_OP = "com.github.chuckerteam.chucker:library-no-op:${Versions.CHUCKER}"
    const val CHUCKER = "com.github.chuckerteam.chucker:library:${Versions.CHUCKER}"

    const val RXJAVA  = "io.reactivex.rxjava3:rxjava:${Versions.RXJAVA}"
    const val RXANDROID = "io.reactivex.rxjava3:rxandroid:${Versions.RXANDROID}"
    const val RXJAVA_ADAPTER = "com.squareup.retrofit2:adapter-rxjava3:${Versions.RETROFIT}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val KOIN = "io.insert-koin:koin-android:${Versions.KOIN}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"

    const val MOCKITO_CORE = "org.mockito:mockito-core:${Versions.MOCKITO}"
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${Versions.MOCKITO_KOTLIN}"
    const val OKHTTP_MOCK_WEBSERVER = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}"
    const val RETROFIT_MOCK = "com.squareup.retrofit2:retrofit-mock:${Versions.RETROFIT}"
    const val MOCKITO_INLINE = "org.mockito:mockito-inline:${Versions.MOCKITO}"
}

object App {
    const val COMPILE_SDK = 33
    const val MIN_SDK = 24
    const val TARGET_SDK = 33
}