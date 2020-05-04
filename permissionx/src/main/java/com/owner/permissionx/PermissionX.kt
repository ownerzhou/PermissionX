package com.owner.permissionx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

typealias PermissionCallback = (Boolean, List<String>) -> Unit

object PermissionX {
    private const val TAG = "PermissionFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        val fragmentManager = activity.supportFragmentManager
        request(fragmentManager, callback, *permissions)
    }

    fun request(fragment: Fragment, vararg permissions: String, callback: PermissionCallback) {
        val fragmentManager = fragment.childFragmentManager
        request(fragmentManager, callback, *permissions)
    }

    private fun request(fragmentManager: FragmentManager, callback: PermissionCallback, vararg permissions: String) {
        val exitsFragment = fragmentManager.findFragmentByTag(TAG)
        var permissionFragment = if (exitsFragment != null) {
            exitsFragment as PermissionFragment
        } else {
            val fragment = PermissionFragment()
            fragmentManager.beginTransaction().add(fragment, TAG).commitNow()
            fragment
        }

        permissionFragment.requestNow(callback, *permissions)
    }
}