package com.nguyencuong.webtruyen.data.remote;

/**
 *
 * Created by pc on 4/11/2017.
 */

public class ApiError {

    private int code;

    private String message;

    public ApiError() {
    }

    public ApiError(int code) {
        this.code = code;
        getMsgWithCode();
    }

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        if (message == null) {
            getMsgWithCode();
        }
        return message;
    }

    private void getMsgWithCode() {

        switch (code) {
            default:
                message = "Unknow.";
                break;
            case -1:
                message = "Không có kết nối mạng.";
                break;
            case -2:
                message = "Không thể kết nối tới dịch vụ máy chủ.";
                break;
            case -3:
                message = "Không có dữ liệu.";
                break;
        }
    }

    public static class Builder {
        ApiError apiError;

        public Builder() {
            apiError = new ApiError();
        }

        public Builder setCode(int code) {
            apiError.code = code;
            return this;
        }

        public Builder setMessage(String message) {
            apiError.message = message;
            return this;
        }

        public ApiError build() {
            return apiError;
        }
    }
}
