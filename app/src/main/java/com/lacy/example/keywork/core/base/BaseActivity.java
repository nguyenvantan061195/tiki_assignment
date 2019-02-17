package com.lacy.example.keywork.core.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;

import com.lacy.example.keywork.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 11:00 AM
 */
public abstract class BaseActivity<T extends BaseVP.BasePresenter> extends AppCompatActivity implements BaseVP.BaseView {
    private Unbinder unbinder = null;
    protected T presenter;
    private ProgressDialog mProgressDialog;
    private AlertDialog mErrorDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // khoi tao presenter
        initPresenter();

        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    protected abstract void initPresenter();

    protected abstract void initView();

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }

    @Override
    public void showLoadingDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mProgressDialog.setTitle("Thông báo");
            mProgressDialog.setMessage("Đang xử lý...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        } else if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showErrorDialog(final String errorMessage) {
        if (mErrorDialog == null) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            dialogBuilder.setTitle("Thông báo");
            dialogBuilder.setMessage(errorMessage).setCancelable(false)
                    .setOnCancelListener(DialogInterface::dismiss).
                    setPositiveButton(R.string.ACTION_CLOSE,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
            mErrorDialog = dialogBuilder.create();
        }

        if (!mErrorDialog.isShowing()) {
            if (errorMessage.contains("<p>") || errorMessage.contains("<li>"))
                mErrorDialog.setMessage(Html.fromHtml(errorMessage));
            else
                mErrorDialog.setMessage(errorMessage);
            mErrorDialog.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Replace fragment in the activity container
     *
     * @param containerId    frame layout container Id
     * @param fragment       fragment to replace
     * @param addToBackStack whether to add or not to back stack
     */
    protected void replaceFragment(
            @IdRes int containerId, @NonNull Fragment fragment, boolean addToBackStack) {

        String fragmentTag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerId, fragment, fragmentTag);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentTag);
        }
        fragmentTransaction.commit();
    }

    /**
     * Replace fragment in the activity container
     * (not add fragment to back stack)
     *
     * @param containerId frame layout container Id
     * @param fragment    fragment to replace
     */
    protected void replaceFragment(@IdRes int containerId, @NonNull Fragment fragment) {
        replaceFragment(containerId, fragment, false);
    }

    @Override
    public void refreshView() {
        // Do nothing
    }

}


