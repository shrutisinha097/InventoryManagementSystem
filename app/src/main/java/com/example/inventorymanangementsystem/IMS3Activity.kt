package com.example.inventorymanangementsystem

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.inventorymanangementsystem.LoginActivity
import com.google.android.material.navigation.NavigationView

class IMS3Activity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var toolBar: androidx.appcompat.widget.Toolbar
    private lateinit var frameLayout: FrameLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_drawer)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolBar = findViewById(R.id.toolBar)
        frameLayout = findViewById(R.id.frameLayout)
        navigationView = findViewById(R.id.navigationView)

        var previousMenuItem: MenuItem? = null

        setUpToolbar()

        openHome()
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this
            , drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener{

            if (previousMenuItem!=null){
                previousMenuItem?.isChecked=false
            }
            it.isCheckable=true
            it.isChecked=true
            previousMenuItem=it
            when(it.itemId){
                R.id.home ->{ openHome()
                    drawerLayout.closeDrawers()
                }
                R.id.meters ->{
                    val fragment= MeterFragment()
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout,fragment)
                    transaction.commit()
                    supportActionBar?.title="Inventory management system"
                    navigationView.setCheckedItem(R.id.meters)
                    drawerLayout.closeDrawers()
                }
                R.id.transformer ->{
                    val fragment= TransformerFragment()
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout,fragment)
                    transaction.commit()
                    supportActionBar?.title="Inventory management system"
                    navigationView.setCheckedItem(R.id.transformer)
                    drawerLayout.closeDrawers()
                }
                R.id.htBreakers ->{
                    val fragment= HtbreakerFragment()
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout,fragment)
                    transaction.commit()
                    supportActionBar?.title="Inventory management system"
                    navigationView.setCheckedItem(R.id.htBreakers)
                    drawerLayout.closeDrawers()

                }
                R.id.ltBreakers ->{
                    val fragment= LtbreakerFragment()
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout,fragment)
                    transaction.commit()
                    supportActionBar?.title="Inventory management system"
                    navigationView.setCheckedItem(R.id.ltBreakers)
                    drawerLayout.closeDrawers()

                }
                R.id.poles ->{
                    val fragment= TowerorPolesFragment()
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout,fragment)
                    transaction.commit()
                    supportActionBar?.title="Inventory management system"
                    navigationView.setCheckedItem(R.id.poles)
                    drawerLayout.closeDrawers()

                }
                R.id.all ->{
                    val fragment= AllFragment()
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.frameLayout,fragment)
                    transaction.commit()
                    supportActionBar?.title="Inventory management system"
                    navigationView.setCheckedItem(R.id.all)
                    drawerLayout.closeDrawers()

                }
                R.id.logout -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }

            return@setNavigationItemSelectedListener true

        }
    }

    private fun setUpToolbar(){
        setSupportActionBar(toolBar)
        supportActionBar?.title="Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id= item.itemId
        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }
    private fun openHome(){
        val fragment= HomeFragment()
        val transaction=supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout,fragment)
        transaction.commit()
        supportActionBar?.title="Inventory management system"
        navigationView.setCheckedItem(R.id.home)
    }

    override fun onBackPressed() {
        drawerLayout.closeDrawers()
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)){
            !is HomeFragment ->openHome()
            else ->{
                val a = Intent(Intent.ACTION_MAIN)
                a.addCategory(Intent.CATEGORY_HOME)
                a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(a)
            }


        }



    }

}