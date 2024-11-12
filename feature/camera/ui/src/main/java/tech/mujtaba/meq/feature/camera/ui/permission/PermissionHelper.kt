package tech.mujtaba.meq.feature.camera.ui.permission

import android.Manifest
import kotlinx.coroutines.flow.StateFlow

interface PermissionHelper {
    val status: StateFlow<Map<String, PermissionStatus>>

    fun ensureCameraPermission()
    fun ensureAudioPermission()

    enum class PermissionStatus {
        GRANTED,
        REJECTED
    }

    companion object {
        internal val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }
}
