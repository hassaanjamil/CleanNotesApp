package com.app.cleannotesapp.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.cleannotesapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.app.cleannotesapp.feature_note.presentation.navigators.MainNavigator
import com.app.cleannotesapp.feature_note.presentation.notes.NotesScreen
import com.app.cleannotesapp.feature_note.presentation.util.Screen
import com.app.cleannotesapp.ui.theme.CleanNotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanNotesAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    MainNavigator(navController = navController)
                }
            }
        }
    }
}
