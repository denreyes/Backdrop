package io.djnr.backdrop.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.djnr.backdrop.R;
import io.djnr.backdrop.dagger.module.LoginActivityModule;
import io.djnr.backdrop.dagger.module.MainActivityModule;
import io.djnr.backdrop.ui.App;
import io.djnr.backdrop.ui.activities.main.view.MainActivity;

/**
 * Created by Dj on 10/4/2016.
 */
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.btn_signin)
    SignInButton mBtnSignIn;
    @BindView(R.id.web_view)
    WebView mWebView;
//    @BindView(R.id.btn_signout)
//    Button mBtnSignOut;
//    @BindView(R.id.btn_revoke)
//    Button mBtnRevoke;
//    @BindView(R.id.img_bg)
//    ImageView mImageBg;

    //    private ProgressDialog mProgressDialog;
//    @Inject
    GoogleApiClient mGoogleApiClient;
//    @Inject
    GoogleSignInOptions mGso;
    private static final int RC_SIGN_IN = 9001;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupComponent();

        mWebView.loadDataWithBaseURL("file:///android_asset/", "<html style=\"" +
                "    background-image: url('raindrop.gif');\n" +
                "    background-repeat: no-repeat;\n" +
                "    background-attachment: fixed;\n" +
                "    background-position: center;\"></html>", "text/html", "utf-8", "");
//        Glide.with(this).load(R.drawable.raindrop).asGif().centerCrop().into(mImageBg);

        mGso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, mGso)
                .build();

        mBtnSignIn.setScopes(mGso.getScopeArray());
    }

    private void setupComponent() {
        App.get(this)
                .getAppComponent()
                .getLoginActivityComponent(new LoginActivityModule(this))
                .inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
//            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
//                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            Log.i(TAG, "handle success!");
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
//            mTextStatus.setText("Signed in as: " + acct.getDisplayName());
//            updateUI(true);

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("ACCOUNT", acct);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);

        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

//    private void showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setMessage("Loading");
//            mProgressDialog.setIndeterminate(true);
//        }
//
//        mProgressDialog.show();
//    }
//
//    private void hideProgressDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.hide();
//        }
//    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            mBtnSignIn.setEnabled(false);
//            mBtnSignOut.setEnabled(true);
//            mBtnRevoke.setEnabled(true);
        } else {
//            mTextStatus.setText("Signed Out");

            mBtnSignIn.setEnabled(true);
//            mBtnSignOut.setEnabled(false);
//            mBtnRevoke.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_signin)
    public void onSigninClicked() {
        signIn();
    }

//    @OnClick(R.id.btn_signout)
//    public void onSignoutClicked() {
//        signOut();
//    }
//
//    @OnClick(R.id.btn_revoke)
//    public void onRevokeClicked() {
//        revokeAccess();
//    }
}
