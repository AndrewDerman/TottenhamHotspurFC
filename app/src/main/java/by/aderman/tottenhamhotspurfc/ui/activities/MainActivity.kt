package by.aderman.tottenhamhotspurfc.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.aderman.tottenhamhotspurfc.R
import by.aderman.tottenhamhotspurfc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavController()
    }

    private fun setBottomNavController() {
        binding.bottomNavView.setupWithNavController(findNavController(R.id.fragment))
    }
}