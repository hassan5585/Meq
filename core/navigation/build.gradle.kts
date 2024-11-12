plugins {
    id(libs.plugins.meq.library.base.get().pluginId)
}

android {
    namespace = "tech.mujtaba.meq.core.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}
