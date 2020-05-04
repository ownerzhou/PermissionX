package com.owner.permissionx

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * A simple [Fragment] subclass.
 */
class PermissionFragment : Fragment() {
    private var callback: PermissionCallback? = null

    fun requestNow(callback: PermissionCallback, vararg permissions: String) {
        this.callback = callback
        requestPermissions(permissions, 100)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 100) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let {
                it(allGranted, deniedList)
            }
        }
    }
}
