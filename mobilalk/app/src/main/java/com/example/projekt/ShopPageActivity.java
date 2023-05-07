package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ShopPageActivity extends AppCompatActivity {

    private FirebaseUser user;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);

        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater findItems = getMenuInflater();
        findItems.inflate(R.menu.shop_menu, menu);

        return true;
    }

    public void toCart(View view)
    {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(500);

        view.animate().translationY(100);

        switch (view.getId())
        {
            case R.id.kabatbut:
                findViewById(R.id.gyemantszet).startAnimation(rotateAnimation);
                break;

            case R.id.sapkabut:
                findViewById(R.id.cap).startAnimation(rotateAnimation);
                break;

            case R.id.cipobut:
                findViewById(R.id.surrano).startAnimation(rotateAnimation);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.cart:
                return true;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;

            case R.id.profile:

                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}