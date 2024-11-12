package tech.mujtaba.meq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import tech.mujtaba.meq.core.navigation.NavGraphProvider
import tech.mujtaba.meq.core.ui.theme.MeqTheme
import tech.mujtaba.meq.feature.camera.navigation.ObjectDetectionDestination
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelper

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navGraphProviders: Set<@JvmSuppressWildcards NavGraphProvider>

    @Inject
    lateinit var permissionHelper: PermissionHelper // Even though it is not used here, it must be created alongside the activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeqTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ObjectDetectionDestination.route
                ) {
                    navGraphProviders.forEach {
                        it.graph(this)
                    }
                }
            }
        }
    }
}
