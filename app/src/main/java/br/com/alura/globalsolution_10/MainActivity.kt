package br.com.alura.globalsolution_10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.alura.globalsolution_10.fragments.ForumFragment
import br.com.alura.globalsolution_10.fragments.HomeFragment
import br.com.alura.globalsolution_10.fragments.ShareFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottom_navigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Theme.currentTheme)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val shareFragment = ShareFragment()
        val forumFragment = ForumFragment()
        bottom_navigation = findViewById(R.id.bottom_navigation)

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
                R.id.forum -> makeCurrentFragment(forumFragment)
                R.id.share -> makeCurrentFragment(shareFragment)
            }
            true

        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}