package com.nguyencuong.webtruyen.screen.home;

import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.BasePresenter;
import com.nguyencuong.webtruyen.Constants;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.model.HomeModel;
import com.nguyencuong.webtruyen.data.remote.services.HomeServices;

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
        if (data == null && mHomeServices.getOffset() == 0) {
            screenView.showMsgError(true, R.string.error_msg_no_data);
            return;
        }
        if (data == null) return;

        // Hide alert error;
        screenView.showMsgError(false, null);

        // Add Slider view if first load
        if (mHomeServices.getOffset() == 0) {
            screenView.addSliderView(data.getSliders());
        }

        for (HomeModel.Items homeItem : data.getItems()) {

            switch (homeItem.getStyle()) {
                default:
                    screenView.addBlockBookListVertical(
                            homeItem.getStyle(),
                            homeItem.getTitle(),
                            homeItem.getLink(),
                            homeItem.getBookList()
                    );
                    break;
                case Constants.HOME_ITEM_STYLE_LIST_H:
                    screenView.addBlockBookListHorizontal(
                            homeItem.getImgBg(),
                            homeItem.getTitle(),
                            homeItem.getSubTitle(),
                            homeItem.getLink(),
                            homeItem.getBookList()
                    );
                    break;
                case Constants.HOME_ITEM_STYLE_ADS:
                    screenView.addAdsView();
                    break;
            }
        }
    }
}
