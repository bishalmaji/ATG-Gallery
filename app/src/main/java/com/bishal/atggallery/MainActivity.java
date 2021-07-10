package com.bishal.atggallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
private NavController navController;
private DrawerLayout drawerLayout;
private  AppBarConfiguration appBarConfiguration;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController=Navigation.findNavController(this,R.id.fragment4);
         drawerLayout=findViewById(R.id.drawerlayout);

appBarConfiguration=new AppBarConfiguration.Builder(navController.getGraph()).setDrawerLayout(drawerLayout).build();
NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
NavigationView navigationView=findViewById(R.id.navigation_view);
NavigationUI.setupWithNavController(navigationView,navController);
     }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment4);
        return  NavigationUI.navigateUp(navController,appBarConfiguration)||super.onSupportNavigateUp();

    }


}