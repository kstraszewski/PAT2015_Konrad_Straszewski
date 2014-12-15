package com.example.konrad.konradstraszewski;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity2 extends ActionBarActivity {

    Button onlyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);
        onlyButton = (Button) findViewById(R.id.button);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void doTheMagic(View view) {

        final int animDuration = 300;

        CardView cardView = (CardView) findViewById(R.id.card);

        AnimatorSet animatorSetRight = new AnimatorSet();

        ObjectAnimator translationX= ObjectAnimator.ofFloat(cardView,View.TRANSLATION_X,500); // Obiekt przejścia po lini X

        ObjectAnimator translationX_back= ObjectAnimator.ofFloat(cardView,View.TRANSLATION_X,0); // Powrót

        ObjectAnimator rotate = ObjectAnimator.ofFloat(cardView,View.ROTATION,360); // Obiekt obrotu
        rotate.setRepeatCount(1);
        rotate.setRepeatMode(ValueAnimator.REVERSE);

        rotate.setDuration(animDuration);                                               // Ustawienie czasu
        translationX.setDuration(animDuration);
        translationX_back.setDuration(animDuration);

        rotate.setStartDelay(animDuration/2);                                             // Ustawianie opóźnień
        translationX_back.setStartDelay(animDuration*3/2);

        animatorSetRight.play(translationX).with(rotate).before(translationX_back);             // Ustawienie kolejności

        animatorSetRight.start();

    }
}
