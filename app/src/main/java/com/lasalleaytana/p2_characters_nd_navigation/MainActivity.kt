package com.lasalleaytana.p2_characters_nd_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import com.lasalleaytana.p2_characters_nd_navigation.ui.theme.P2_Characters_nd_NavigationTheme



//FONTS

//val silian_rail_font = FontFamily(
    //Font(R.font.silian_rail_font)
//)



//val modern_typewriter_font = FontFamily(
    //Font(R.font.modern_typewriter_font)
//)


data class ItemData(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val description1: String,
    val description2: String,
    val tier: String,
    val tierExp: String,
    val imageDes: Int,
    val route: String
)

val itemList = listOf(
    ItemData(1, R.drawable.brachiosaurus_picture, "Brachiosaurus",
        "Roaming Earth between 156 and 145 million years ago during the Jurassic period, Brachiosaurus grew over 80 feet long and weighed more than 28 tons. This dino’s name comes from the Greek words meaning “arm lizard” because its forelegs were longer than its hind legs—another adaption to help it reach high into the trees. ",
        "This dino’s name comes from the Greek words meaning “arm lizard” because its forelegs were longer than its hind legs—another adaption to help it reach high into the trees. It’s part of the sauropod family: huge plant-eaters with long necks, long tails, and a four-legged stance.",
        "WHY PLACED IN TIER S: ",
        "Iconic status, extreme size and functional dominance,",
        R.drawable.brachiosaurus_image_des,"dino1"),
    ItemData(2, R.drawable.spinosaurus_picture, "Spinosaurus",
        "In a murky river that cuts through North Africa, a 50-foot-long hunter swims after a fish the size of a car. It sports six-inch-long teeth and a sail on its back with spines the size of surfboards. The hungry stalker closes in on the fish, gobbling up the meal. Then it glides away in search of more snacks.",
        "Named for its seven-foot-long spines, Spinosaurus lived about a hundred million years ago during the Cretaceous period. It inhabited what is now North Africa’s Sahara region, which at the time featured a large river system. Spinosaurus was well adapted for aquatic life. Its nostrils were further up on its snout than the nostrils of other dinosaurs. ",
        "WHY PLACED IN TIER S: ",
        "High-power attacks, immense size, intimidating sail and specialized semi-aquatic combat ability.",
        R.drawable.spinosaurus_image_des,"dino2"),
    ItemData(3, R.drawable.tyrannosaurus_picture, "Tyrannosaurus rex",
        "A 40-foot-long predator stomps through a forested valley in what’s now western North America, following its nose: The animal sniffs a tasty Triceratops nearby. Moving quickly—about 12 miles an hour—the carnivore catches up to its prey.",
        "Its name comes from the Greek words meaning “tyrant lizard king.” This “king” ruled over what’s now North America and Asia some 68 million years ago, during the Cretaceous period.",
        "WHY PLACED IN TIER S: ",
        "Unparalleled bite force, high defensive durability and overwhelming strength.",
        R.drawable.tyrannosaururs_image_des,"dino3")
)

// Main Activity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            P2_Characters_nd_NavigationTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        composable("main") {
                            MainScreen(navController)
                        }

                        composable(
                            "details/{route}",
                            arguments = listOf(
                                navArgument("route") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->

                            val route = backStackEntry.arguments?.getString("route") ?: ""
                            val item = itemList.find { it.route == route }

                            item?.let {
                                DetailsScreen(
                                    imageRes = it.imageRes,
                                    title = it.title,
                                    description1 = it.description1,
                                    description2 = it.description2,
                                    tier = it.tier,
                                    tierExp = it.tierExp,
                                    imageDes = it.imageDes,
                                    route = it.route,
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// COMPOSABLES:

// MAIN SCREEN
@Composable
fun MainScreen(navController: NavController) {

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Magenta, Color.White, Color.Cyan)
    )

    // BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.jurassic_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            //contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp), // 2 columns (you can change this)
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {

            // TITLE: JURASSIC TIER
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        // STROKE
                        Text(
                            text = "Jurassic Park Tier",
                            fontSize = 38.sp,
                            //fontFamily = silian_rail_font,
                            fontWeight = FontWeight.Bold,

                            style = TextStyle(
                                drawStyle = Stroke(width = 6f),
                                color = Color.Black
                            )
                        )
                        // FILL
                        Text(
                            text = "Jurassic Park Tier",
                            fontSize = 38.sp,
                            //fontFamily = silian_rail_font,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                brush = gradientBrush
                            )
                        )
                    }
                }

            }

            // SPACING
            //item {
                //Spacer(modifier = Modifier.height(5.dp))
            //}

            // PROFILE LIST (THIS IS THE ONLY CORRECT WAY)
            items(itemList) { item ->
                ProfileCard(item = item, navController = navController)
            }
        }
    }
}

// DINOSAURS
@Composable
fun ProfileCard(item: ItemData, navController: NavController) {

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Magenta, Color.Cyan)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
            navController.navigate("details/${item.route}")
        }
    ) {

        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = "Dinosaur Image",
            modifier = Modifier
                .size(140.dp)
        )

        //Spacer(modifier = Modifier.height(8.dp))

        // DINOSAUR NAME
        // STROKE
        Box {
            // STROKE
            Text(
                text = item.title,
                fontSize = 28.sp,
                //fontFamily = modern_typewriter_font,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    drawStyle = Stroke(width = 6f),
                    color = Color.Black
                )
            )
            // FILL
            Text(
                text = item.title,
                fontSize = 28.sp,
                //fontFamily = modern_typewriter_font,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    brush = gradientBrush,
                )
            )
        }

        //Spacer(modifier = Modifier.height(8.dp))

        //Button(
            //onClick = {
                //navController.navigate("details/${item.route}"
                //)
            //},
            //colors = ButtonDefaults.buttonColors(
                //containerColor = Color.Magenta.copy(alpha = 0.5f)
            //)
        //) {
            //Text(
                //text = "⸙",
                //fontSize = 20.sp,
                //fontFamily = modern_typewriter_font,
                //color = Color.White
            //)
        //}
    }
}

// DetailsScreen.Composable
@Composable
fun DetailsScreen(title: String, description1: String, description2: String, tier: String, tierExp: String, imageRes: Int, imageDes: Int, route: String, navController: NavController) {

    val gradientBrush1 = Brush.linearGradient(
        colors = listOf(Color.Magenta, Color.White, Color.Cyan)
    )

    val gradientBrush2 = Brush.linearGradient(
        colors = listOf(Color.Magenta, Color.Cyan)
    )

    // BACKGROUND: GRADIENT
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.jurassic_background),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            //contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )

        // BLACK BOX: OPACITY FOR BACKGROUND
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                // TITLE: DINOSAUR NAME
                Box {
                    // STROKE
                    Text(
                        text = title,
                        fontSize = 45.sp,
                        //fontFamily = silian_rail_font,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            drawStyle = Stroke(width = 6f),
                            color = Color.Black
                        )
                    )
                    // FILL
                    Text(
                        text = title,
                        fontSize = 45.sp,
                        //fontFamily = silian_rail_font,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(
                            brush = gradientBrush1,
                        )
                    )
                }

                // IMAGE: DINOSAUR
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Dinosaur Picture",
                    modifier = Modifier
                        .size(300.dp)
                        .padding(5.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // DESCRIPTION
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(text = description1)
                        }
                        append("\n\n")
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(text = description2)
                        }
                    },
                    fontSize = 18.sp,
                    //fontFamily = modern_typewriter_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp,
                    color = Color.White,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // TIER EXPLANATION
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(brush = gradientBrush2)) {
                            append(text = tier)
                        }
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(text = tierExp)
                        }
                    },
                    fontSize = 18.sp,
                    //fontFamily = modern_typewriter_font,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                // IMAGE FOR DESCRIPTION
                Image(
                    painter = painterResource(id = imageDes),
                    contentDescription = "Dinosaur Picture for Description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(340.dp)
                        .clip(RectangleShape)
                        .border(
                            2.dp,
                            brush = gradientBrush1,
                            shape = RectangleShape
                        )
                        .padding(5.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // BUTTON
                Button(
                    onClick = {
                        (
                                navController.popBackStack()

                                )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Magenta.copy(alpha = 0.5f)
                    )
                ) {
                    Text(
                        text = "メ",
                        fontSize = 18.sp,
                        //fontFamily = modern_typewriter_font,
                        color = Color.White
                    )
                }
            }

        }
    }
}

// PREVIEWS:

// MainScreen.Preview
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    P2_Characters_nd_NavigationTheme {
        MainScreen(navController = rememberNavController())
    }
}

// DetailsScreen.Preview
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    P2_Characters_nd_NavigationTheme {
        DetailsScreen(
            title = "Profile name",
            description1 = "Content",
            description2 = "Content",
            tier = "tier place",
            tierExp = "tier explanation",
            imageRes = R.drawable.brachiosaurus_picture,
            imageDes = R.drawable.brachiosaurus_picture,
            route = "main",
            navController = rememberNavController()
        )
    }
}