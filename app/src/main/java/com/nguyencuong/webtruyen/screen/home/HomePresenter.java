package com.nguyencuong.webtruyen.screen.home;

import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.BasePresenter;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.model.HomeModel;
import com.nguyencuong.webtruyen.data.remote.services.HomeServices;
import com.nguyencuong.webtruyen.model.Slider;

import java.util.ArrayList;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/13/2017.
 * Email: vancuong2941989@gmail.com
 */

public class HomePresenter extends BasePresenter implements HomeServices.ResultCallback, HomeContract.Presenter {

    private final HomeContract.View screenView;

    final HomeServices mHomeServices;

    protected HomePresenter(BaseContractView screenView, HomeServices mHomeServices) {
        super(screenView);
        this.screenView = (HomeContract.View) screenView;
        this.screenView.setPresenter(this);
        this.mHomeServices = mHomeServices;
        mHomeServices.setResultCallback(this);
    }

    @Override
    public void onStart() {
        mHomeServices.loadHomeData();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        mHomeServices.cancel();
    }

    @Override
    public void onApiResultFailure(ApiError apiError) {
        screenView.showMsgError(true, apiError.getMessage());
        screenView.showLoading(false);
    }

    @Override
    public void onApiConnectInternetFailure(ApiError apiError) {
        screenView.showMsgError(true, apiError.getMessage());
        screenView.showLoading(false);
    }

    @Override
    public void onHomeResultSuccess(HomeModel.Data data) {
        screenView.showLoading(false);
        screenView.addSliderView((ArrayList<Slider>) data.getSliders());
    }
}
