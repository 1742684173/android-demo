package com.aloogn.demo.mvp.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aloogn.demo.mvp.base.IBaseView;

public abstract class BaseFragment extends Fragment implements IBaseView {
    public abstract int getContentViewId();
    protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected Context mContext;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(),container,false);
        this.mContext = getActivity();
        initAllMembersView(savedInstanceState);
        return mRootView;
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseActivity)mContext).showLoading();
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseActivity)mContext).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        ((BaseActivity)mContext).showToast(msg);
    }

    @Override
    public void showError() {
        checkActivityAttached();
        ((BaseActivity)mContext).showError();
    }


    protected boolean isAttachedContext(){
        return getActivity() != null;
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached(){
        if(getActivity() == null){
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }
}
