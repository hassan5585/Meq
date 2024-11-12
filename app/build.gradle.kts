plugins {
    id(libs.plugins.meq.app.get().pluginId)
}

android {
    namespace = "tech.mujtaba.meq.app"
    defaultConfig {
        applicationId = "tech.mujtaba.meq"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(project(":core:ui"))
    implementation(project(":feature:camera:ui"))
    implementation(project(":feature:camera:domain"))
    implementation(project(":feature:camera:navigation"))
}
