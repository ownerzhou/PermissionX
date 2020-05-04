package com.owner.permissionx

import androidx.fragment.app.FragmentActivity

typealias PermissionCallback = (Boolean, List<String>) -> Unit

object PermissionX {
    private val TAG = "PermissionFragment"

    fun request(
        activity: FragmentActivity,
        vararg permission: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val exitsFragment = fragmentManager.findFragmentByTag(TAG)
        var permissionFragment = if (exitsFragment != null) {
            exitsFragment as PermissionFragment
        } else {
            val fragment = PermissionFragment()
            fragmentManager.beginTransaction().add(fragment,
                TAG
            ).commitNow()
            fragment
        }

        permissionFragment.requestNow(callback, *permission)
    }
}