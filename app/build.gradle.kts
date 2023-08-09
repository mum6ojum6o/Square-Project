
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.squaretakehomeproject"
    compileSdk = App.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.squaretakehomeproject"
        minSdk = App.MIN_SDK
        targetSdk = App.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("${Dependencies.CORE_KTX}")
    implementation("${Dependencies.ANDROIDX_APPCOMPAT}")
    implementation("${Dependencies.CONSTRAINT_LAYOUT}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation("androidx.compose.ui:ui-tooling:1.4.3")

    //rx
    implementation ("${Dependencies.RXJAVA}")
    implementation ("${Dependencies.RXANDROID}")

    //koin
    implementation ("${Dependencies.KOIN}")
    //okhttp
    implementation ("${Dependencies.OKHTTP}")
    //retrofit
    implementation ("${Dependencies.RETROFIT}")
    implementation ("${Dependencies.GSON}")
    implementation ("${Dependencies.GSON_CONVERTER}")
    implementation ("${Dependencies.RXJAVA_ADAPTER}")
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    //chucker
    debugImplementation ("${Dependencies.CHUCKER}")
    releaseImplementation ("${Dependencies.CHUCKER_NO_OP}")

    //glide
    implementation ("${Dependencies.GLIDE}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    testImplementation("${Dependencies.RETROFIT_MOCK}")
    testImplementation("${Dependencies.OKHTTP_MOCK_WEBSERVER}")
    testImplementation("${Dependencies.MOCKITO_CORE}")
    testImplementation("${Dependencies.MOCKITO_INLINE}")
    testImplementation("${Dependencies.MOCKITO_KOTLIN}")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

}