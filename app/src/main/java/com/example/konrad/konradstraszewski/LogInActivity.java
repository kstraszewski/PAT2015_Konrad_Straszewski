package com.example.konrad.konradstraszewski;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LogInActivity extends ActionBarActivity {

    Button onlyButton;
    EditText password_edit, e_mail;

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


    public void LogIn(View view) {


        e_mail = (EditText) findViewById(R.id.edit_EmailAddress);                               //Tworzy Stringa adresu e-mail
        String email = String.valueOf(e_mail.getText());

        Pattern emailPattern = Pattern.compile(".+@.+\\.[a-z]+");                               //Tworzy pattern walidacji e-mail
        Matcher emailMatcher = emailPattern.matcher(email);

        password_edit = (EditText) findViewById(R.id.edit_password);                            //Tworzy Stringa hasła
        String password = String.valueOf(password_edit.getText());

        Pattern passwordPattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z]).{8,}");               //Tworzy Pattern walidacji hasła
        Matcher passwordMatcher = passwordPattern.matcher(password);

        Toast invalid_password = Toast.makeText(this, R.string.invalid_password, Toast.LENGTH_SHORT);  //Tworzy powiadomienia Toast
        Toast invalid_email = Toast.makeText(this, R.string.invalid_email, Toast.LENGTH_SHORT);


        if (email.isEmpty() || password.isEmpty()) {                                      //Sprawdza czy pola są wypełnione
            Toast blank = Toast.makeText(this, R.string.blank, Toast.LENGTH_SHORT);
            blank.show();
        } else {                                                                   //Walidacja hasła
            if (!passwordMatcher.matches()) {
                invalid_password.show();
            } else {                                                              //Walidacja adresu e-mail
                if (!emailMatcher.matches()) {
                    invalid_email.show();
                } else {                                                          //Odpala nowe Main Menu po prawidłowej walidacji
                    Intent mainmenu = new Intent(this, MainMenu.class);
                    startActivity(mainmenu);
                }
            }
        }
    }
}

