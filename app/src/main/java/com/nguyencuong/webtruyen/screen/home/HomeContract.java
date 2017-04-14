package com.nguyencuong.webtruyen.screen.home;

import com.nguyencuong.webtruyen.BaseContractPresenter;
import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.model.Slider;

import java.util.ArrayList;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/13/2017.
 * Email: vancuong2941989@gmail.com
 */

public interface HomeContract {

    interface View extends BaseContractView<Presenter> {

        void addSliderView(ArrayList<Slider> sliders);
    }

    interface Presenter extends BaseContractPresenter {

    }
}
