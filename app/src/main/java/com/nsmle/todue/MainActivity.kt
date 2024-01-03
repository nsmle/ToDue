package com.nsmle.todue

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.nsmle.todue.data.repository.TaskDao
import com.nsmle.todue.databinding.ActivityMainBinding
import com.nsmle.todue.ui.dialog.AddTaskBottomSheetDialogFragment
import com.nsmle.todue.utils.interfaces.DialogListener

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		// Hide Title Bar
		if (getSupportActionBar() != null) {
			getSupportActionBar()?.hide();
		}

		val navView: BottomNavigationView = binding.navView
		val navFragmentHost = R.id.nav_host_fragment_activity_main

		val appBar = setOf(
			R.id.navigation_home,
			R.id.navigation_task,
			R.id.navigation_notifications,
		)

		val navController = findNavController(navFragmentHost)
		val appBarConfiguration = AppBarConfiguration(appBar)

		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)

		// Handle Fab
		binding.fab.setOnClickListener {
			val addTaskBottomDialog = AddTaskBottomSheetDialogFragment()

			val fragments = supportFragmentManager.findFragmentById(navFragmentHost)?.childFragmentManager?.fragments
			val activeFragment = fragments?.get(0)


			Log.i("APP", activeFragment?.javaClass?.simpleName + " Is Extends DialogListener " + (activeFragment is DialogListener))
			if (activeFragment is DialogListener) addTaskBottomDialog.setListener(activeFragment)
			addTaskBottomDialog.show(supportFragmentManager, AddTaskBottomSheetDialogFragment.TAG)
		}
	}
}