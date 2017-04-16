package com.nguyencuong.webtruyen.screen.home;

import com.nguyencuong.webtruyen.BaseContractPresenter;
import com.nguyencuong.webtruyen.BaseContractView;
import com.nguyencuong.webtruyen.model.Book;
import com.nguyencuong.webtruyen.model.Slider;

import java.util.List;

/**
 * Content class.
 * <p>
 * Created by Mr Cuong on 4/13/2017.
 * Email: vancuong2941989@gmail.com
 */

public interface HomeContract {

    interface View extends BaseContractView<Presenter> {

        void addSliderView(List<Slider> sliders);

        void addBlockBookListVertical(int style, String title, String urlMore, List<Book> books);

        void addBlockBookListHorizontal(String urlBg, String title, String subTitle, String urlMore, List<Book> books);

        void addAdsView();
    }

    interface Presenter extends BaseContractPresenter {

    }
}
