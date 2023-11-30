package com.example.faraja_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.faraja_app.ui.theme.Faraja_AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeContainer()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContainer() {
    var selectedOption by remember { mutableStateOf("Communities") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val switcherOpacity by animateFloatAsState(if (drawerState.isClosed) 1f else 0f, label = "")
    val communitiesOpacity by animateFloatAsState(if (drawerState.isClosed) 1f else 0f, label = "")

    NavBar(drawerState = drawerState)

    Switcher(
        opacity = switcherOpacity,
        onToggle = { option -> selectedOption = option }
    )

    // Here we use CommunityData.communityList from the separate file
    Communities(communities = CommunityData.communityList, opacity = communitiesOpacity)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope() // Create a CoroutineScope

    TopAppBar(
        title = { /* No title here */ },
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    // Toggle the drawer state
                    if (drawerState.isClosed) {
                        drawerState.open() // Open the drawer
                    } else {
                        drawerState.close() // Close the drawer
                    }
                }
            }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu") // Replace with your desired icon
            }
        }
        // You can add other AppBar configurations if necessary
    )
}

@Composable
fun Switcher(
    opacity: Float,
    onToggle: (option: String) -> Unit
) {
    var selectedOption by remember { mutableStateOf("Communities") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(opacity),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ToggleButton(
                text = "Communities",
                selected = selectedOption == "Communities",
                onToggle = { selectedOption = "Communities"; onToggle("Communities") }
            )

            Spacer(Modifier.width(8.dp))

            ToggleButton(
                text = "Counselors",
                selected = selectedOption == "Counselors",
                onToggle = { selectedOption = "Counselors"; onToggle("Counselors") }
            )
        }
    }
}




@Composable
fun CommunityCard(community: Community) {
    Column(modifier = Modifier.clickable { /* To Do */ }) {
        Row(
            modifier = Modifier.padding(top = 15.dp, start = 15.dp)
        ) {
            Image(painter = painterResource(R.drawable.guitar), contentDescription = "Community profile picture", modifier = Modifier
                .size(40.dp)
                .clip(
                    CircleShape
                )
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = community.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = "${community.members} members",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.width(140.dp))
            ExtendedFloatingActionButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.height(30.dp)
            ) {
                Row {
                    Icon(Icons.Filled.Add, contentDescription = "", modifier = Modifier.size(18.dp))
                    Text(text = "Join", modifier = Modifier.padding(start = 10.dp))
                }
            }
        }
        Text(text = community.description, modifier = Modifier.padding(16.dp, top = 8.dp, end = 25.dp, bottom = 10.dp), style = MaterialTheme.typography.bodySmall)
    }

}

@Composable
fun Communities(communities: List<Community>, opacity: Float) {
    AnimatedVisibility(
        visible = opacity > 0,
        enter = fadeIn() + expandHorizontally(),
        exit = fadeOut() + shrinkHorizontally()
    ) {
        LazyColumn(modifier = Modifier.padding(top = 75.dp)) {
            items(communities) { community ->
                CommunityCard(community = community)
            }
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