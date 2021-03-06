package org.tianjyan.pwd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.tianjyan.pwd.R;
import org.tianjyan.pwd.application.BaseActivity;
import org.tianjyan.pwd.application.MD5;
import org.tianjyan.pwd.application.PwdGen;
import org.tianjyan.pwd.model.SettingKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPasswordActivity extends BaseActivity implements TextWatcher {

    //region field
    private static final int LENGTH = 8;
    @BindView(R.id.set_password_first) EditText pwdEt;
    @BindView(R.id.set_password_second) EditText rePwdEt;
    @BindView(R.id.set_password_next) Button nextBtn;
    //endregion

    //region function

    //region override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        ButterKnife.bind(this);

        pwdEt.addTextChangedListener(this);
        rePwdEt.addTextChangedListener(this);
        pwdEt.requestFocus();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(pwdEt.getText().length() > 0 && rePwdEt.getText().length() > 0){
            nextBtn.setEnabled(true);
        }
        else{
            nextBtn.setEnabled(false);
        }
    }
    //endregion

    //region click event

    @OnClick(R.id.set_password_next)
    public void nextClick(View v) {
        if(pwdEt.getText().toString().equals(rePwdEt.getText().toString())){
            String pwd = pwdEt.getText().toString();
            super.putSetting(SettingKey.LOCK_PWD, MD5.getMD5(pwd));
            super.putSetting(SettingKey.NO_LOCK_PWD, "false");
            genKey();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, R.string.different_password, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.set_password_skip)
    public void skipClick(View v) {
        super.putSetting(SettingKey.NO_LOCK_PWD, "true");
        genKey();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    //endregion

    //region private
    private void genKey(){
        String key = super.getSetting(SettingKey.KEY, "");
        if(key.equals("")){
            key = PwdGen.generatePassword(LENGTH,
                    PwdGen.Optionality.MANDATORY,
                    PwdGen.Optionality.MANDATORY,
                    PwdGen.Optionality.MANDATORY,
                    PwdGen.Optionality.MANDATORY);
            super.putSetting(SettingKey.KEY, key);
        }
    }
    //endregion

    //endregion
}
