package young.home.com.mypassword.model;

import android.os.Bundle;

/**
 * Created by YOUNG on 2016/4/9.
 */
public class AsyncResult<Data> {
    private int result;
    private Data data;
    private Bundle bundle = new Bundle();

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Bundle getBundle() {
        return bundle;
    }
}