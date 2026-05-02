package com.lasalleaytana.p2_characters_nd_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lasalleaytana.p2_characters_nd_navigation.ui.theme.P2_Characters_nd_NavigationTheme

// Commit Again

//FONTS
val lobster_mlb_font = FontFamily(
    Font(R.font.lobster_mlb_font)
)
val dropline_mlb_font = FontFamily(
    Font(R.font.dropline_mlb_font)
)

// ITEM: DATA
data class ItemData(
    val id: Int,
    val imageRes: Int,
    val title: String,
    val description1: String,
    val imageJewel: Int,
    val jewelDes: String,
    val imageAbility: Int,
    val description2: String,
    val imageCivilian: Int,
    val civilianDes: String,
    val imageKwami: Int,
    val kwamiDes: String,
    val route: String,
    val backgroundRes: Int
)

// ITEM: LIST
val itemList = listOf(
    // LADYBUG
    ItemData(1, R.drawable.ladybug_picture, "Ladybug",
        "Ladybug’s main powers are Lucky Charm (summoning a creative object) &  Miraculous Ladybug (repairing damage).",
        R.drawable.ladybug_jewel,
        "A pair of black circular earrings with a silver lining. Inhabited by the kwami Tikki, these earrings grant the power of creation.",
        R.drawable.ladybug_lucky_charm,
        "Objects that Ladybug creates whenever she uses Lucky Charm. They help her to achieve her objective, which is usually to stop a supervillain. \n" + "Ladybug can also throw that object skywards to activate Miraculous Ladybug, which can repair all the damage.",
        R.drawable.marinette_picture,
        "Passionate fashion design student and baker’s daughter, she uses the Ladybug Miraculous to protect Paris. \n" + "As the superheroine Ladybug, wields the power of creation while juggling high school life and a massive crush on Adrien Agreste.",
        R.drawable.kwami_tikki,
        "Ancient Kwami of Creation. Associated with the Ladybug Miraculous, she empowers her holders.",
        "character1-ladybug",
        R.drawable.background_ladybug,
    ),

    // CHAT NOIR

    // VESPERIA

    // VIPERION
)

// MAIN ACTIVITY
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
                                    imageJewel = it.imageJewel,
                                    jewelDes = it.jewelDes,
                                    imageAbility = it.imageAbility,
                                    description2 = it.description2,
                                    imageCivilian = it.imageCivilian,
                                    civilianDes = it.civilianDes,
                                    imageKwami = it.imageKwami,
                                    kwamiDes = it.kwamiDes,
                                    route = it.route,
                                    backgroundRes = it.backgroundRes,
                                    navController = navController,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// MAIN SCREEN
@Composable
fun MainScreen(navController: NavController) {

    // BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_mlb),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        // OPACITY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {

            // TITLE: MIRACULOUS RUN GAME CHARACTERS
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        // STROKE
                        Text(
                            text = "Miraculous Run Game Characters",
                            fontSize = 39.sp,
                            fontFamily = lobster_mlb_font,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 36.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                drawStyle = Stroke(width = 15f),
                                color = Color.Black,
                            )
                        )
                        // FILL
                        Text(
                            text = "Miraculous Run Game Characters",
                            fontSize = 38.sp,
                            fontFamily = lobster_mlb_font,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 36.sp,
                            color = Color.White,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
            // PROFILE LIST
            items(itemList) { item ->
                ProfileCard(item = item, navController = navController)
            }
        }
    }
}

// CHARACTERS CARDS
@Composable
fun ProfileCard(item: ItemData, navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
            navController.navigate("details/${item.route}")
        }
    ) {
        Box(contentAlignment = Alignment.Center) {
            // BLUR STROKE
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .size(240.dp)
                    .blur(10.dp)
            )
            // IMAGE: CHARACTER
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(240.dp)
            )
        }
        // CHARACTER NAME
        Box {
            Text(
                text = item.title,
                fontSize = 20.sp,
                fontFamily = dropline_mlb_font,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

// DETAILS SCREEN
@Composable
fun DetailsScreen(
    imageRes: Int,
    title: String,
    description1: String,
    imageJewel: Int,
    jewelDes: String,
    imageAbility: Int,
    description2: String,
    imageCivilian: Int,
    civilianDes: String,
    imageKwami: Int,
    kwamiDes: String,
    route: String,
    backgroundRes: Int,
    navController: NavController,
) {

    // IA generated
    var currentImageId by remember(imageRes) { mutableStateOf(imageRes) }

    // BACKGROUND
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        // OPACITY
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            item {
                Box {
                    // BUTTON: TOP
                    Button(
                        onClick = {
                            (navController.popBackStack())
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red.copy(alpha = 0.5f)
                        )
                    ) {
                        Text(
                            text = "❮❮❮❮",
                            fontSize = 16.sp,
                            fontFamily = lobster_mlb_font,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    // TITLE: CHARACTER NAME
                    // STROKE
                    Text(
                        text = title,
                        fontSize = 39.sp,
                        fontFamily = lobster_mlb_font,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            drawStyle = Stroke(width = 15f),
                            color = Color.Black
                        )
                    )
                    // FILL
                    Text(
                        text = title,
                        fontSize = 38.sp,
                        fontFamily = lobster_mlb_font,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // CHARACTER IMAGE
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { // IA generated
                            currentImageId = if (currentImageId == imageRes) {
                                imageCivilian
                            } else {
                                imageRes
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // BLUR STROKE
                    Image(
                        painter = painterResource(id = currentImageId),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(300.dp)
                            .blur(10.dp)
                    )
                    // IMAGE: CHARACTER
                    Image(
                        painter = painterResource(id = currentImageId),
                        contentDescription = "Character Picture",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // DESCRIPTION 1: ABILITIES
                Text(
                    text = description1,
                    fontSize = 18.sp,
                    fontFamily = dropline_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    color = Color.White,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // IMAGE: JEWEL
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // BLUR STROKE
                    Image(
                        painter = painterResource(id = imageJewel),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(300.dp)
                            .blur(10.dp)
                    )
                    Image(
                        painter = painterResource(id = imageJewel),
                        contentDescription = "Jewel Picture",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(300.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // JEWEL DESCRIPTION
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Miraculous \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(jewelDes)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = dropline_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // IMAGE: ABILITY
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // BLUR STROKE
                    Image(
                        painter = painterResource(id = imageAbility),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(300.dp)
                            .blur(10.dp)
                    )
                    Image(
                        painter = painterResource(id = imageAbility),
                        contentDescription = "Ability Picture",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(300.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // DESCRIPTION 2: LUCKY CHARM
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Lucky Charm \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(description2)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = dropline_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // IMAGE: CIVILIAN
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // BLUR STROKE
                    Image(
                        painter = painterResource(id = imageCivilian),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(300.dp)
                            .blur(10.dp)
                    )
                    Image(
                        painter = painterResource(id = imageCivilian),
                        contentDescription = "Civilian Picture",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // CIVILIAN DESCRIPTION
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Marinette Dupain-Cheng \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(civilianDes)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = dropline_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // IMAGE: KWAMI
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // BLUR STROKE
                    Image(
                        painter = painterResource(id = imageKwami),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Black),
                        modifier = Modifier
                            .size(300.dp)
                            .blur(10.dp)
                    )
                    Image(
                        painter = painterResource(id = imageKwami),
                        contentDescription = "Kwami Picture",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(300.dp)
                            .padding(5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // KWAMI DESCRIPTION
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontSize = 28.sp,
                                fontFamily = lobster_mlb_font,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Tikki \n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.White
                            )
                        ) {
                            append(kwamiDes)
                        }
                    },
                    fontSize = 18.sp,
                    fontFamily = dropline_mlb_font,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Justify,
                    lineHeight = 36.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // BUTTON: BOTTOM
                Button(
                    onClick = {
                        (navController.popBackStack())
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.5f)
                    )
                ) {
                    Text(
                        text = "❮❮❮❮",
                        fontSize = 16.sp,
                        fontFamily = lobster_mlb_font,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

        }
    }
}

// PREVIEWS:
// MainScreen Preview
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    P2_Characters_nd_NavigationTheme {
        MainScreen(navController = rememberNavController())
    }
}

// DetailsScreen Preview
@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    P2_Characters_nd_NavigationTheme {
        DetailsScreen(
            imageRes = R.drawable.marinette_picture,
            title = "Character's name",
            description1 = "Abilities",
            imageJewel = R.drawable.ladybug_jewel,
            jewelDes = "Jewel description",
            imageAbility = R.drawable.marinette_picture,
            description2 = "Content description",
            imageCivilian = R.drawable.marinette_picture,
            civilianDes = "Civilian description",
            imageKwami = R.drawable.marinette_picture,
            kwamiDes = "Kwami description",
            route = "main",
            backgroundRes = R.drawable.background_mlb,
            navController = rememberNavController(),
        )
    }
}