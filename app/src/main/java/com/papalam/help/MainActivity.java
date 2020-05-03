package com.papalam.help;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.title);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(MainActivity.this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_tests:
                setFragment("Тесты", new ContactsFragment());
                break;
            case R.id.navigation_diary:
                setFragment("Дневник", new ContactsFragment());
                break;
            case R.id.navigation_chat:
                setFragment("Чат", new ChatFragment());
                break;
            case R.id.navigation_contacts:
                setFragment("Контакты", new ContactsFragment());
                break;

        }

        return true;
    }

    private void setFragment(String textTitle, Fragment fragment) {
        title.setText(textTitle);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.place_holder, fragment).commit();

    }
}
