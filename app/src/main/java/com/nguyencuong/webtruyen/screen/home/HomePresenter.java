package com.nguyencuong.webtruyen.screen.home;

import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.BasePresenter;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/13/2017.
 * Email: vancuong2941989@gmail.com
 */

public class HomePresenter extends BasePresenter {

    private final HomeContract.View screenView;

    protected HomePresenter(BaseContractView screenView) {
        super(screenView);
        this.screenView = (HomeContract.View) screenView;

    }
}
