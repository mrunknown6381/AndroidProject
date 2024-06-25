plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")


}

android {
    namespace = "com.example.travelbuddy"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.travelbuddy"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    //splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")
    //data store
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.google.dagger:hilt-android:2.48.1")

    implementation("com.google.firebase:firebase-firestore:24.10.1")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.compose.material3:material3-android:1.2.0")



    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    //navigation
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    //pager
    runtimeOnly("com.google.accompanist:accompanist-pager:0.33.2-alpha")
    implementation("androidx.compose.material:material-icons-extended-android:1.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))

    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-storage")
    implementation("com.google.firebase:firebase-database")

    implementation("com.google.android.gms:play-services-location:21.1.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")
    implementation("com.airbnb.android:lottie-compose:6.1.0")
}

