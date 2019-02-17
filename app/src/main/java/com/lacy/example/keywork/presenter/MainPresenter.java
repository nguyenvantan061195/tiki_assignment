package com.lacy.example.keywork.presenter;

import com.google.gson.Gson;
import com.lacy.example.keywork.common.GlobalInfo;
import com.lacy.example.keywork.common.exception.ErrorConstants;
import com.lacy.example.keywork.core.base.BaseVP;
import com.lacy.example.keywork.core.service.ServiceException;
import com.lacy.example.keywork.core.service.ServiceFactory;
import com.lacy.example.keywork.view.MainView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by vantan - nguyenvantan061195@gmail.com
 * HCMC, Vietnam.
 *
 * @version 1.0
 * @since 13, January, 2019 2:24 PM
 */
public class MainPresenter extends BaseVP.BasePresenter<MainView> {
    public MainPresenter() {
        mServiceApi = ServiceFactory.getServiceInstance();
    }

    /**
     * getAllKeywords
     */
    public void getAllKeywords() {
        mView.showLoadingDialog();
        mDisposable
                .add(buildGetAllKeyword()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBody>() {
                            @Override
                            public void accept(ResponseBody responseBody) throws Exception {
                                String jsonArray = responseBody.string();
                                ArrayList result = new Gson().fromJson(jsonArray, ArrayList.class);
                                mView.getAllKeywordSuccess(result);
                            }
                        }, getNetErrorConsumer(true)));
    }

    /**
     * build get all key word from retrofit
     *
     * @return ResponseBody instance of List<String>
     */
    private Observable<ResponseBody> buildGetAllKeyword() {
        if (GlobalInfo.getInstance().isNetworkConnected()) {
            return Observable.error(new ServiceException(
                    ErrorConstants.NETWORK_NOT_AVAILABLE, null));
        }
        return mServiceApi.getAllKeyword()
                .subscribeOn(GlobalInfo.getInstance().subscribeScheduler());
    }
}
