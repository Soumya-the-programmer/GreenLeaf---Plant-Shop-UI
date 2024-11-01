package com.example.practiceappeight

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


// MainActivity class
class MainActivity : ComponentActivity()
{
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // to full fill full status bar
        setContent() // main ui code will be written here
        {
            Navigation()
        }
    }
}


// class for the app data
data class AppData(var name: String)

// function to handle the navigation
@Composable
fun Navigation()
{
    // instance of rememberNavController()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "homeScreen"
    )
    {
        // this is home screen
        composable("homeScreen")
        {
            MainUi(navController = navController)
        }

        // this is shop now screen
        composable(route = "shopNowScreen/{color}/{price}/{heading}",
                arguments = listOf( // defining arguments types in a list
                    navArgument(name = "color"){ type = NavType.IntType }, // type mentioning
                    navArgument(name = "price"){ type = NavType.FloatType }, //     ,,
                    navArgument(name = "heading"){ type = NavType.StringType } //   ,,
                )
            )
        {backStackEntry ->
            // creating variables to pass
            val color = backStackEntry.arguments?.getInt("color")
            val price = backStackEntry.arguments?.getFloat("price")
            val heading = backStackEntry.arguments?.getString("heading")

            ShopNowScreen(
                navController = navController,
                color = color!!,
                price = price!!,
                heading = heading!!
            )
        }
    }
}

// function for the main ui component
@Composable
fun MainUi(navController: NavController)
{
    var uiColor by remember { mutableStateOf(Color(0xFF095859)) }
    // for the whole app structure
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = uiColor)
    )
    {
        // for the upper app structure
        UpperUIStructure(uiColor = {color -> uiColor = color}, appColor = uiColor)

        // for the lower app structure
        LowerUIStructure(navController = navController, color = uiColor)
    }
}

// function for the upper ui structure
@Composable
fun UpperUIStructure(uiColor: (Color) -> Unit, appColor: Color)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.64f)
        .padding(top = 55.dp, start = 35.dp, end = 35.dp),)
    {
        // for placing vertically
        Column(modifier = Modifier.fillMaxSize())
        {
            // name title
            NameTitle(modifier = Modifier.align(Alignment.Start), uiColor = appColor)

            Spacer(modifier = Modifier.height(40.dp)) // creating some space

            // button box
            ButtonBox({ color -> uiColor(color) }) // calling button box function and taking the color from button click

            // layout for the tree images
            SwipeImages()
        }
    }
}

// function for the lower ui structure
@Composable
fun LowerUIStructure(navController: NavController, color : Color)
{
    // price of the item
    val price = 17.92f
    // storing the header and price in the list
    val headingAndPriceList = listOf("Decorative\nPlant", "$$price")

    // for lower ui structure
    Box(modifier = Modifier
        .fillMaxSize()
        .clip(shape = RoundedCornerShape(30.dp))
        .background(color = Color.White)
        .padding(start = 35.dp, end = 35.dp, top = 20.dp, bottom = 30.dp)
    )
    {
        // for placing vertically
        Column {
            // calling price and heading function
            PriceAndHeading(headingAndPriceList = headingAndPriceList)

            // for the description
            Text(
                text = "Shop decorative plants easily and add a touch of green to your space with our interactive app!",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Normal
                )
            )

            Spacer(modifier = Modifier.height(50.dp)) // creating some space

            // for the shop now button
            ShopNowButton(navController = navController,
                color = color,
                price = price,
                heading = headingAndPriceList[0]
            )
        }
    }
}

// function for the shop now button
@Composable
fun ShopNowButton(navController: NavController, color : Color, price : Float, heading : String)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .clip(shape = RoundedCornerShape(16.dp))
        .background(color = Color.Black)
        .clickable{
            navController.navigate("shopNowScreen/${color.toArgb()}/$price/$heading")
        },
        contentAlignment = Alignment.BottomCenter
    )
    {
        // for the icon
        Image(painter = painterResource(id = R.drawable.shop_icon),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.size(40.dp).align(Alignment.CenterStart).offset(x = 15.dp, y = 0.dp)
        )

        // for covering the whole area
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            // for the text
            Text("Buy Now",
                style = TextStyle(
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )

        }
    }
}

// function for the price and the heading
@Composable
fun PriceAndHeading(headingAndPriceList : List<String>)
{
    // structure layout for the text heading and price
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(color = Color.Transparent),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
        )
    {
        // placing the text
        headingAndPriceList.forEach { text ->
            Text(text = text,
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }
}

// function for the named title for the user
@Composable
fun NameTitle(modifier : Modifier = Modifier, uiColor : Color)
{
    val obj = AppData("Soumya") // object creation and data passing

    // Text widget
    Text(text = "Hi, ${obj.name}",
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = if(uiColor == Color.Black || uiColor == Color(0xFF095859))
                        Color.White
                    else
                        Color.Black
            ,
        ),
        modifier = modifier
    )
}

// function for the images swipe
@Composable
fun SwipeImages()
{
    // storing the index in state
    var index by remember { mutableIntStateOf(0) }

    // list of images
    val imageList = listOf( R.drawable.image_1, R.drawable.image_2,
                            R.drawable.image_3, R.drawable.image_4
    )

    // storing the current image in state
    var image by remember { mutableIntStateOf(imageList[index]) }

    // lambda function to change the image on tap
    val changeImage = {changeImage : Boolean ->
        // checking if boolean value true or not
        index = if(changeImage)
                    // implementing circular queue formula
                    (index + 1) % imageList.size
                else
                    // decrementing index unless it's 0
                    if(index > 0) index - 1 else imageList.size - 1

        image = imageList[index] // storing the current index value and getting the current image
    }

    // Coroutine function that implements a independent thread for auto image change
    LaunchedEffect(Unit) // passing nothing but Unit as argument which means nothing
    {
        while(true) {
            delay(1800) // holding 2 seconds or 2000 ms
            changeImage(true) // passing true boolean value
        }
    }

    // for the whole image structure
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Transparent),
        contentAlignment = Alignment.BottomCenter
    )
    {
        // for the image
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
        )

        // to place the clicking feature on the image horizontally
        Row(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent))
        {
            // for left click
            Box(modifier = Modifier
                .weight(1f) // filling the half of screen
                .fillMaxHeight()
                .background(color = Color.Transparent)
                .clickable(indication = null,
                    interactionSource =
                    remember {
                        MutableInteractionSource() /* function that holds the interaction source */
                    }, // remembering the interaction source
                    onClick = {
                        changeImage(false) // passing false to reduce index
                    }
                )
            )

            // for right click
            Box(modifier = Modifier
                .weight(1f) // filling the half of screen
                .fillMaxHeight()
                .background(color = Color.Transparent)
                .clickable(indication = null,
                    interactionSource =
                    remember {
                        MutableInteractionSource() // function that holds the interaction source
                    }, // remembering the interaction source
                    onClick = {
                        changeImage(true) // passing false to reduce index
                    }
                )
            )
        }
    }
}

// function for button box
@Composable
fun ButtonBox(uiColor: (Color) -> Unit)
{
    // list for all the button colors
    val buttonColors = listOf(
        Color.Black, Color(0xFFD1FFBD), Color(0xFF095859),
        Color(0xFF05FFA6), Color.Yellow
    )

    // defining button color on a state
    var selectedColor by remember { mutableStateOf(Color(0xFF095859)) }

    // for the box structure
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(75.dp)
        .clip(shape = RoundedCornerShape(35.dp))
        .background(color = Color.White.copy(alpha = 0.2f))
    )
    {
        // for placing horizontally
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
            .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            buttonColors.forEach{ color ->
                Box(modifier = Modifier
                    .size(if (selectedColor == color) 55.dp else 42.dp)
                    .clip(shape = CircleShape)
                    .border(
                        width = if (selectedColor == color) 2.dp else 0.dp,
                        color = if (selectedColor == color) Color.White else color,
                        shape = CircleShape
                    )
                    .background(color = color)
                    .clickable {
                        selectedColor =
                            color // storing the original color into selected color after clicking
                        uiColor(selectedColor) // passing the selected color to call back function
                    }
                )
                {
                    // checking if selected color match with original color
                    if(selectedColor == color) {
                        Image(
                            // changing the tick mark color based on the color
                            painter = painterResource(id = if(selectedColor == Color.Black || selectedColor == Color(0xFF095859))
                                                                R.drawable.white_tick_mark
                                                            else if(selectedColor == Color.White || selectedColor == Color.Yellow
                                                                        || selectedColor == Color(0xFF05FFA6))
                                                                R.drawable.black_tick_mark
                                                            else
                                                                R.drawable.tick_mark
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

// to check the preview
@Preview(showBackground = true)
@Composable
fun AppNavigationPreview()
{
    Navigation()
}


