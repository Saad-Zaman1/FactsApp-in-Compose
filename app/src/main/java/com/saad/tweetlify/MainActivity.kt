package com.saad.tweetlify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.saad.tweetlify.screens.CategoryScreen
import com.saad.tweetlify.screens.DetailsScreen
import com.saad.tweetlify.ui.theme.TweetlifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TweetlifyTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color.Black,
                                titleContentColor = Color.White
                            ),
                            title = {
                                Text(text = "Categories")
                            })
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }

            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable(route = "main") {
            CategoryScreen {
                navController.navigate("detail/${it}")
            }
        }
        composable(
            route = "detail/{category}", arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailsScreen()
        }
    }
}
