package ml.socshared.vkadapter.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error {
    @SerializedName("error_code")
    public int errorCode;

    @SerializedName("error_msg")
    public String errorMsg;

    @SerializedName("request_params")
    List<RequestParams> requestParams;

}
