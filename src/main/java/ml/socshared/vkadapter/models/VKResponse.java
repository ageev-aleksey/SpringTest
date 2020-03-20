package ml.socshared.vkadapter.models;

public class VKResponse <ResponseType> {

    public VKResponse(ResponseType ok) {
        okValue = ok;
        error = null;
    }

    public VKResponse(Error fail) {
        okValue = null;
        error = fail;
    }


    public boolean isError() {
        return error != null;
    }

    public Error getError() {
        return error;
    }

    public ResponseType getResponse() {
        return okValue;
    }

    private ResponseType okValue;
    private Error error;
}
