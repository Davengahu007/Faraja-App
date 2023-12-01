package com.example.faraja_app

import android.annotation.SuppressLint
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
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.faraja_app.ui.theme.Faraja_AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Faraja_AppTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("mycommunities") { MyCommunitiesScreen(navController) }
                    composable("counselors") {CounselorsScreen(navController) }
                    composable("newcommunities") { NewCommunitiesScreen(navController)}
                    composable("currentcommunity") { CurrentCommunityScreen(navController)}

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationSection(
    navController: NavHostController,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    TopAppBar(
        title = { /* No title here */ },
        navigationIcon = {
            IconButton(onClick = { /* Handle drawer opening */ }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        actions = {
            Row(Modifier.fillMaxWidth()) {
                Spacer(Modifier.weight(1f)) // Spacer before the buttons

                // Communities Button with fixed width
                Button(
                    onClick = { navController.navigate("mycommunities") },
                    modifier = Modifier.widthIn(min = 140.dp)
                ) {
                    Text("My Communities")
                }

                Spacer(Modifier.width(8.dp)) // Spacer between buttons

                // Counselors Button with fixed width
                Button(
                    onClick = { navController.navigate("counselors") },
                    modifier = Modifier.widthIn(min = 140.dp)
                ) {
                    Text("Counselors")
                }

                Spacer(Modifier.weight(1f)) // Spacer after the buttons
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    var selectedOption by remember { mutableStateOf("My Communities") }

    Scaffold(
        topBar = {
            NavigationSection(
                navController = navController,
                selectedOption = selectedOption,
                onOptionSelected = { option ->
                    selectedOption = option
                    if (option == "My Communities") {
                        // Navigate to Communities screen
                        navController.navigate("mycommunities")
                    }
                }
            )
        }
    ) {
        // Rest of your HomeScreen content
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyCommunitiesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            NavigationSection(
                navController = navController,
                selectedOption = "My Communities",
                onOptionSelected = { /* Handle option selection */ }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("newcommunities") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {
        CommunitiesClickable(CommunityData.communityList, navController)
    }
}


@Composable
fun CommunitiesClickable(communities: List<Community>, navController: NavHostController) {
    LazyColumn(modifier = Modifier.padding(top = 75.dp)) {
        items(communities) { community ->
            CommunityCardClickable(community = community) {
                // Navigate to the CurrentCommunityScreen when a card is clicked
                navController.navigate("currentcommunity")
            }
        }
    }
}


@Composable
fun CommunityCardClickable(community: Community, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(R.drawable.guitar), // Replace with actual image resource or painter
                contentDescription = "Community profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = community.name,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${community.members} members",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = community.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CurrentCommunityScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            NavigationSection(
                navController = navController,
                selectedOption = "Current Community",
                onOptionSelected = { /* Handle option selection */ }
            )
        }
    ) {

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CounselorsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            NavigationSection(
                navController = navController,
                selectedOption = "Counselors",
                onOptionSelected = { /* Handle option selection */ }
            )
        }
    ) {

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewCommunitiesScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            NavigationSection(
                navController = navController,
                selectedOption = "New Communities",
                onOptionSelected = { /* Handle option selection */ }
            )
        }
    ) {
        Communities(CommunityData.communityList, 1f) // Use the existing community list
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope() // Create a CoroutineScope

    TopAppBar(
        title = { "Faraja" },
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

