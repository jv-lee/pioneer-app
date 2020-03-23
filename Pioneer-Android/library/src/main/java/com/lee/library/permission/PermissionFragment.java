package com.lee.library.permission;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @author jv.lee
 * @date 2019/4/14
 */
public class PermissionFragment extends Fragment {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionManager.PERMISSION_CODE) {
            if (grantResults.length > 0) {
                for (int i = 0;i<grantResults.length;i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        if (PermissionManager.getInstance().permissionRequest != null) {
                            PermissionManager.getInstance().permissionRequest.onPermissionFiled(permissions[i]);
                        }
                        return;
                    }
                }
                if (PermissionManager.getInstance().permissionRequest != null) {
                    PermissionManager.getInstance().permissionRequest.onPermissionSuccess();
                }
            }
        }
    }
}
