package com.nguyencuong.webtruyen.widget.slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nguyencuong.webtruyen.Constants;
import com.nguyencuong.webtruyen.R;
import com.nguyencuong.webtruyen.model.Slider;
import com.nguyencuong.webtruyen.util.IntentUtils;
import com.nguyencuong.webtruyen.util.StringUtils;

import es.dmoral.toasty.Toasty;

/**
 * Fragment show cover in {@link BookSliderView}
 */
public class BookSliderFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_SLIDER = "ARG_SLIDER";

    private Slider slider;

    public static BookSliderFragment newInstant(Slider slider) {
        BookSliderFragment fragment = new BookSliderFragment();
        Bundle bundle = new Bundle();
        if (slider != null) {
            bundle.putParcelable(ARG_SLIDER, slider);
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView imageView = new ImageView(getActivity());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(layoutParams);

        String urlImage = "http://google.com";
        Bundle bundle = getArguments();
        if (bundle != null) {
            slider = bundle.getParcelable(ARG_SLIDER);
            if (slider != null) {
                urlImage = slider.getImg();
                if (urlImage != null) {
                    urlImage = urlImage.replace(" ", "");
                }
            }
        }

        Glide.with(getActivity())
                .load(urlImage)
                .placeholder(R.drawable.default_cover)
                .error(R.drawable.default_cover)
                .into(imageView);

        return imageView;
    }

    @Override
    public void onClick(View view) {
        if (slider == null) {
            showToast(R.string.error_msg_no_data);

        } else if (slider.getType() == Constants.SLIDER_TYPE_MOVIE) {
            if (slider.getBookId() < 1) {
                showToast(R.string.error_msg_no_data);
            }
            showToast("Show book info. id = " + slider.getBookId());
            //Intent intent = MovieDetailActivity.buildIntent(getActivity(), slider.getMovId());
            //startActivity(intent);
        } else if (slider.getType() == Constants.SLIDER_TYPE_BANNER) {
            if (StringUtils.isEmpty(slider.getLink())) {
                showToast(R.string.error_msg_no_data);
            }

            Intent intent = IntentUtils.openUrlOnWebBrowser(slider.getLink());
            startActivity(intent);
        }
    }

    private void showToast(String msg) {
        Toasty.info(getActivity().getApplicationContext(), msg + "", Toast.LENGTH_SHORT).show();
    }

    private void showToast(@StringRes int resId) {
        Toasty.error(getActivity().getApplicationContext(), getActivity().getString(resId), Toast.LENGTH_SHORT).show();
    }
}
