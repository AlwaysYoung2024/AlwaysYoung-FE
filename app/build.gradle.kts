plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.alwaysyoung2024_fe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.alwaysyoung2024_fe"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    viewBinding{
        enable = true;
    }
}

dependencies {
    //implementation("com.ramotion.paperonboarding:paper-onboarding:1.1.3")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("com.google.android.material:material:1.4.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}