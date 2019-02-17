package com.lacy.example.keywork.core.base;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.common.base.Preconditions;
import com.lacy.example.keywork.R;
import com.lacy.example.keywork.common.GlobalInfo;
import com.lacy.example.keywork.common.exception.ErrorConstants;
import com.lacy.example.keywork.core.service.ServiceApi;
import com.lacy.example.keywork.core.service.ServiceException;

import java.net.SocketTimeoutException;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.HttpException;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 10, December, 2018 10:55 AM
 */
public interface BaseVP {

    interface BaseView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void showErrorDialog(String errorMessage);

        void refreshView();

    }

    abstract class BasePresenter<V extends BaseView> {
        public ServiceApi mServiceApi;
        public CompositeDisposable mDisposable = new CompositeDisposable();
        public V mView;

        void attachView(V view) {
            Preconditions.checkNotNull(view, "view is null");
            mView = view;
        }

        void detachView() {
            clearDisposable();
            mView = null;
        }

        protected void addDisposable(Disposable disposable) {
            if (mDisposable != null) mDisposable.add(disposable);
        }

        @Nullable
        protected V view() {
            return mView;
        }

        void clearDisposable() {
            if (mDisposable != null) mDisposable.clear();
        }

        public Consumer<Throwable> getNetErrorConsumer(final boolean isShowDialog) {

            return new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    mView.hideLoadingDialog();

                    if (throwable instanceof SocketTimeoutException) {
                        BasePresenter.this.throwSocketTimeoutException(isShowDialog);
                        return;
                    }

                    if (throwable instanceof HttpException) {
                        BasePresenter.this.throwHttpException(isShowDialog);
                        return;
                    }

                    if (throwable instanceof ServiceException) {
                        BasePresenter.this.throwServiceException(throwable, isShowDialog);
                        return;
                    }
                    String msg = throwable.getMessage();
                    if (isShowDialog) mView.showErrorDialog(msg);
                }
            };
        }

        private void throwServiceException(Throwable throwable, boolean isShowDialog) {
            ServiceException ex = (ServiceException) throwable;
            String msg = ex.getMessage();
            int code = ex.getCode();

            if (Integer.compare(code, ErrorConstants.NETWORK_NOT_AVAILABLE) == 1) {
                msg = GlobalInfo.getInstance().getString(R.string.ERROR_NETWORK_NOT_AVAILABLE);
            }

            if (TextUtils.isEmpty(msg)) {
                msg = GlobalInfo.getInstance().getString(R.string.ERROR_SERVICE_RESPONSE_NO_ERROR_CODE);
            }
            if (isShowDialog) {
                mView.showErrorDialog(msg);
            }
        }

        private void throwHttpException(boolean isShowDialog) {
            String msg = GlobalInfo.getInstance().getString(R.string.ERROR_NETWORK_NOT_CONNECTED);
            if (isShowDialog) mView.showErrorDialog(msg);
        }

        private void throwSocketTimeoutException(boolean isShowDialog) {
            String msg = GlobalInfo.getInstance().getString(R.string.ERROR_TIMEOUT);
            if (isShowDialog) mView.showErrorDialog(msg);
        }
    }
}
