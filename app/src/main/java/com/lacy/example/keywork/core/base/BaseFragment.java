package com.lacy.example.keywork.core.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 11:52 AM
 */
public abstract class BaseFragment<T extends BaseVP.BasePresenter> extends Fragment implements BaseVP.BaseView {

    private Unbinder unbinder = null;

    protected T presenter;

    protected AppCompatActivity parent;

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    public AppCompatActivity getParent() {
        return parent;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parent = (AppCompatActivity) getActivity();

        // khoi tao presenter
        initPresenter();

        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroyView();
    }

    @Override
    public void showLoadingDialog() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showLoadingDialog();
        }
    }

    @Override
    public void hideLoadingDialog() {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.hideLoadingDialog();
        }
    }

    @Override
    public void showErrorDialog(String errorMessage) {
        BaseActivity activity = (BaseActivity) getActivity();
        if (activity != null) {
            activity.showErrorDialog(errorMessage);
        }
    }

    @Override
    public void refreshView() {
        // Do nothing
    }

}
