package com.nguyencuong.webtruyen.ui.home;

import android.os.Handler;

import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.BasePresenter;
import com.nguyencuong.webtruyen.Constants;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.data.remote.ApiError;
import com.nguyencuong.webtruyen.data.remote.model.HomeModel;
import com.nguyencuong.webtruyen.data.remote.services.HomeServices;
import com.nguyencuong.webtruyen.util.LogUtils;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/13/2017.
 * Email: vancuong2941989@gmail.com
 */

public class HomePresenter extends BasePresenter implements HomeServices.ResultCallback, HomeContract.Presenter {

    private static final String TAG = HomePresenter.class.getSimpleName();

    private final HomeContract.View mView;

    private final HomeServices mHomeServices;

    private boolean isLoadmore = false;

    protected HomePresenter(BaseContractView view, HomeServices mHomeServices) {
        super(view);
        this.mView = (HomeContract.View) view;
        this.mView.setPresenter(this);
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
    public void loadMore() {
        if (isLoadmore) {
            isLoadmore = false;
            mHomeServices.cancel();
            mHomeServices.loadHomeData();
            LogUtils.d(TAG, " load more offset = " + mHomeServices.getOffset());
        }
    }

    @Override
    public void reloadPage() {

    }

    @Override
    public void onApiResultFailure(ApiError apiError) {
        mView.showMsgError(true, apiError.getMessage());
        mView.showLoading(false);
    }

    @Override
    public void onApiConnectInternetFailure(ApiError apiError) {
        mView.showMsgError(true, apiError.getMessage());
        mView.showLoading(false);
    }

    @Override
    public void onHomeResultSuccess(HomeModel.Data data) {
        mView.showLoading(false);
        if (data == null && mHomeServices.getOffset() == 0) {
            mView.showMsgError(true, R.string.error_msg_no_data);
            return;
        }
        if (data == null) return;

        // Hide alert error;
        mView.showMsgError(false, null);

        // Add Slider view if first load
        if (mHomeServices.getOffset() == 0) {
            mView.addSliderView(data.getSliders());
        }

        for (HomeModel.Items homeItem : data.getItems()) {
            switch (homeItem.getStyle()) {
                default:
                    mView.addBlockBookListVertical(
                            homeItem.getStyle(),
                            homeItem.getTitle(),
                            homeItem.getLink(),
                            homeItem.getBookList()
                    );
                    break;
                case Constants.HOME_ITEM_STYLE_LIST_H:
                    mView.addBlockBookListHorizontal(
                            homeItem.getImgBg(),
                            homeItem.getTitle(),
                            homeItem.getSubTitle(),
                            homeItem.getLink(),
                            homeItem.getBookList()
                    );
                    break;
                case Constants.HOME_ITEM_STYLE_ADS:
                    mView.addAdsView();
                    break;
            }
        }

        if (data.getItems().size() > 0) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mHomeServices.nextOffset();
                    isLoadmore = true;
                }
            }, 200);
        }
    }
}
