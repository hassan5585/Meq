package tech.mujtaba.meq.feature.camera.ui.upcoming

import android.app.Activity
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import dagger.hilt.android.EntryPointAccessors
import tech.mujtaba.meq.feature.camera.ui.R
import tech.mujtaba.meq.feature.camera.ui.di.DiEntryPoint
import tech.mujtaba.meq.feature.camera.ui.image.DimensionEstimator
import tech.mujtaba.meq.feature.camera.ui.image.Estimate
import tech.mujtaba.meq.feature.camera.ui.image.ObjectImageAnalyzer
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelper
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelper.Companion.REQUIRED_PERMISSIONS

@Composable
internal fun CameraScreen(backStackEntry: NavBackStackEntry) {
    val activity = LocalContext.current as Activity

    val entryPoint = remember {
        EntryPointAccessors.fromActivity<DiEntryPoint>(activity)
    }
    val permissionHelper = remember {
        entryPoint.permissionHelper()
    }
    val executor = remember {
        entryPoint.executor()
    }
    var isCameraPermissionGranted by remember {
        mutableStateOf(false)
    }
    var isAudioPermissionGranted by remember {
        mutableStateOf(false)
    }
    val status by permissionHelper.status.collectAsState()
    LaunchedEffect(key1 = status) {
        isCameraPermissionGranted = status[REQUIRED_PERMISSIONS[0]] == PermissionHelper.PermissionStatus.GRANTED
        isAudioPermissionGranted = status[REQUIRED_PERMISSIONS[1]] == PermissionHelper.PermissionStatus.GRANTED
        if (!isCameraPermissionGranted) {
            permissionHelper.ensureCameraPermission()
        } else {
            if (!isAudioPermissionGranted) {
                permissionHelper.ensureAudioPermission()
            }
        }
    }
    if (isCameraPermissionGranted && isAudioPermissionGranted) {
        val imageDetector = remember {
            entryPoint.imageDetector()
        }
        val analyzer = remember {
            ObjectImageAnalyzer(imageDetector)
        }
        val results by analyzer.results.collectAsState()
        val controller = remember {
            LifecycleCameraController(activity).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_ANALYSIS
                )
                setImageAnalysisAnalyzer(executor, analyzer)
            }
        }
        val estimator = remember {
            entryPoint.dimensionEstimator()
        }
        Box(modifier = Modifier.fillMaxSize()) {
            CameraPreview(controller = controller, modifier = Modifier.fillMaxSize())
            Overlay(results, estimator)
        }
    }
}

@Composable
private fun BoxScope.Overlay(result: ObjectImageAnalyzer.AnalyzerResult?, estimator: DimensionEstimator) {
    result?.let { analyzerResult ->
        var estimates by remember {
            mutableStateOf<List<Estimate>>(emptyList())
        }
        LaunchedEffect(key1 = analyzerResult) {
            estimates = estimator.estimate(result.results)
        }
        LazyColumn(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 16.dp)
        ) {
            items(estimates) { estimate ->
                Text(
                    text = stringResource(id = R.string.item_estimate_description, estimate.label, estimate.width, estimate.height),
                    color = if (estimate.isReference) {
                        Color.Cyan
                    } else {
                        Color.White
                    }
                )
            }
        }
    }
}
