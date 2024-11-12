package tech.mujtaba.meq.feature.camera.ui.permission

import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import tech.mujtaba.meq.feature.camera.ui.permission.PermissionHelper.Companion.REQUIRED_PERMISSIONS

@ActivityScoped
internal class PermissionHelperImpl @Inject constructor(
    private val activity: ComponentActivity
) : PermissionHelper {
    private val _status = MutableStateFlow(
        buildMap {
            REQUIRED_PERMISSIONS.forEach { permission ->
                put(permission, PermissionHelper.PermissionStatus.REJECTED)
            }
        }
    )

    override val status = _status.asStateFlow()

    private val permissionLaunchers = buildMap {
        REQUIRED_PERMISSIONS.forEach { permission ->
            val launcher = activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isPermissionAccepted ->
                setNewPermissionStatus(permission, isPermissionAccepted)
            }

            put(permission, launcher)
        }
    }

    override fun ensureCameraPermission() {
        val permission = REQUIRED_PERMISSIONS[0]
        if (!hasPermission(permission)) {
            permissionLaunchers[permission]?.launch(permission)
        } else {
            setNewPermissionStatus(permission, true)
        }
    }

    override fun ensureAudioPermission() {
        val permission = REQUIRED_PERMISSIONS[1]
        if (!hasPermission(permission)) {
            permissionLaunchers[permission]?.launch(permission)
        } else {
            setNewPermissionStatus(permission, true)
        }
    }

    private fun setNewPermissionStatus(permission: String, isPermissionAccepted: Boolean) {
        val currentStatus = _status.value
        val newStatusForPermission = if (isPermissionAccepted) {
            PermissionHelper.PermissionStatus.GRANTED
        } else {
            PermissionHelper.PermissionStatus.REJECTED
        }
        _status.value = currentStatus.toMutableMap().apply {
            put(permission, newStatusForPermission)
        }.toMap()
    }

    private fun hasPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }
}
