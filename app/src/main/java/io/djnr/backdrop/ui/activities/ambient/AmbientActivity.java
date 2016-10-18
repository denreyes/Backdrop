package io.djnr.backdrop.ui.activities.ambient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.djnr.backdrop.R;
import io.djnr.backdrop.ui.fragments.ambient.AmbientFragment;

/**
 * Created by Dj on 8/25/2016.
 */
public class AmbientActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambient);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new AmbientFragment()).commit();
    }
}
