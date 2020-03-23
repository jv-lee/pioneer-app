package com.lee.library.widget.dialog.core;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.lee.library.R;

/**
 * @author jv.lee
 * @date 2020-03-07
 * @description 自定义dialog 超类
 */
public abstract class BaseDialog extends Dialog {

    protected CancelListener cancelListener;
    protected ConfrimListener confrimListener;

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.BaseDialog);
        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(getContext(), buildViewId(), null);
        setContentView(view);
        bindView();
    }

    /**
     * dialog的view
     *
     * @return 返回view的 layout 资源ID
     */
    protected abstract int buildViewId();

    /**
     * 绑定view
     */
    protected abstract void bindView();

    public void setCancelListener(CancelListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public void setConfirmListener(ConfrimListener confrimListener) {
        this.confrimListener = confrimListener;
    }
}
