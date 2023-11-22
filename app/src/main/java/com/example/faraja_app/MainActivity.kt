package com.example.faraja_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.faraja_app.ui.theme.Faraja_AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drawer(
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        },
    ) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {

                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(top = 25.dp, start = 10.dp)
                        .size(38.dp)

                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "")
                }
            }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeContainer()
        }
    }
}

@Composable
fun NavBar() {
    Drawer()
}

@Composable
fun Switcher(
    onToggle: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf("Communities") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ToggleButton(
            text = "Communities",
            selected = selectedOption == "Communities",
            onToggle = {
                selectedOption = "Communities"
                onToggle(selectedOption)
            }
        )

        Spacer(modifier = Modifier.width(16.dp))

        ToggleButton(
            text = "Counselors",
            selected = selectedOption == "Counselors",
            onToggle = {
                selectedOption = "Counselors"
                onToggle(selectedOption)
            }
        )

    }
}
@Composable
fun CommunityCard(community: Community) {
    Row {
        Column {
            Text(text = "Hello ${community.name}")
            Text(text = "Hello ${community.latestMessage}")
        }
    }
}

@Composable
fun ToggleButton(
    text: String,
    selected: Boolean,
    onToggle: () -> Unit
) {
    Button(
        onClick = {
        onToggle()
    },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
        )
    ) {
        Text(text = text, color = if (selected) Color.White else MaterialTheme.colorScheme.primary)

    }
}

data class Community(val name: String, val latestMessage: String)

@Composable
fun HomeContainer() {
    var selectedOption by remember {
        mutableStateOf("Communities")
    }

    val latestMessage by remember {
        mutableStateOf("Nice")
    }

    NavBar()
    Switcher { option ->
        selectedOption = option
    }
    CommunityCard(community = Community("Fun", latestMessage))
}

fun onDrawerItemClicked(item: String) {
    when (item) {
        "Item 1" -> {

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Faraja_AppTheme {
        Greeting("Android")
    }
}