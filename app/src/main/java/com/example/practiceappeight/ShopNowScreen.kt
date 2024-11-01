package com.example.practiceappeight

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import kotlin.math.roundToLong

// function for the whole screen
@Composable
fun ShopNowScreen(
    navController: NavController,
    color : Int,
    price : Float,
    heading : String
)
{
    // snack bar host state instance
    val snackbarHostState = remember{ SnackbarHostState() }

    // context instance
    val context = LocalContext.current

    // for the full screen
    Scaffold(
        // snackbar host
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
    {
        // for the full screen box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color(color))
        )
        {
            // for placing vertically
            Column()
            {
                // for the upper box white colored
                TopScreen(
                    navController = navController,
                    color = color,
                    heading = heading,
                    price = price,
                    snackbarHostState = snackbarHostState
                )

                // for giving the structure for button
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Transparent)
                    .padding(start = 30.dp, end = 30.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    // for the confirm order button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(color = if(color == Color.Black.toArgb() ||
                                color == Color(0xFF095859).toArgb())
                                    Color.White
                            else
                                Color.Black)
                            .clickable(
                                onClick = {
                                    // showing the toast message on clicking on the button
                                    Toast.makeText(context,
                                        "You'll be directed to the payment screen!",
                                        Toast.LENGTH_SHORT
                                        ).show()
                                }
                            ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(text = "Proceed to Payment",
                            style = TextStyle(
                                fontSize = 25.sp,
                                color = if(color== Color.Black.toArgb() ||
                                    color == Color(0xFF095859).toArgb())
                                    Color.Black
                                else
                                    Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

// funciton for the top of the screen box
@Composable
fun TopScreen(navController: NavController,
              color : Int,
              heading : String,
              price : Float,
              snackbarHostState: SnackbarHostState
)
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .clip(shape = RoundedCornerShape(bottomStart = 110.dp))
            .background(color = Color.White)
            .padding(top = 30.dp, start = 30.dp),
        contentAlignment = Alignment.TopStart
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
        )
        {
            // for the back button
            BackButton(color = color, navController = navController)

            // creating some extra space
            Spacer(modifier = Modifier.height(20.dp))

            // Title for image
            TitleAndSubTitle(heading = heading)

            // for the app middle section
            AppMiddleSection(price = price, snackbarHostState = snackbarHostState)
        }
    }
}


// function for the title and sub title
@Composable
fun TitleAndSubTitle(heading: String)
{
    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        )
        {
            append(heading.replace("\n", " ")) // replacing the \n with space and placing the title
        }
        append("\nnursery pot")
    },
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray
        )
    )
}

// function for the back button
@Composable
fun BackButton(color : Int, navController: NavController)
{
    // for the back button
    Box(modifier = Modifier
        .size(40.dp)
        .clip(shape = CircleShape)
        .background(color = Color(color))
        .clickable(indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {
                navController.navigate("homeScreen")
            }
        ),
    )
    {
        // for the back icon
        Image(painter = painterResource(id = if(color == Color.Black.toArgb()
            || color == Color(0xFF095859).toArgb()) // checking the color and then choosing image
            R.drawable.white_back

        else if(color == Color.White.toArgb() || color == Color.Yellow.toArgb()
            || color == Color(0xFF05FFA6).toArgb())
            R.drawable.black_back
        else
            R.drawable.green_back
        ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.Center)
        )
    }
}

// for the app middle section like image and price
@Composable
fun AppMiddleSection(price: Float, snackbarHostState: SnackbarHostState)
{
    // storing quantity in the state for changing
    var quantity by remember { mutableIntStateOf(1) }

    // storing the price in the state for changing
    var changedPrice by remember { mutableFloatStateOf(price) }

    // for placing vertically
    Row(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Transparent),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    )
    {
        // for the product price and add section
        Box(modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(0.29f)
            .padding(start = 0.dp),
            contentAlignment = Alignment.BottomStart
        )
        {
            // for placing the price and add option vertically
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Transparent)
            )
            {
                // for the price
                Text(
                    text = "$${DecimalFormat("#.##").format(changedPrice * quantity)}",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF578F44)
                    ),
                )

                // creating some space
                Spacer(modifier = Modifier.height(10.dp))

                // for the quantity options
                ProductAddSection(quantityChange = {newQuantity -> quantity = newQuantity})

                // creating some space
                Spacer(modifier = Modifier.height(20.dp))

                // for the icons
                WishlistAndCart(snackbarHostState =  snackbarHostState)
            }
        }

        // for the app image
        Image(
            painter = painterResource(id = R.drawable.image_1),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
        )
    }
}

// function for the product quantity adding section
@Composable
fun ProductAddSection(quantityChange : (Int) -> Unit)
{
    // storing quantity in the state for changing
    var quantity by remember { mutableIntStateOf(1) }

    // for placing horizontally
    Row(modifier = Modifier
        .height(40.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
        )
    {
        // for - button
        Box(modifier = Modifier
            .size(28.dp)
            .background(color = Color.Transparent)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    quantity = if (quantity > 1) quantity - 1
                    else quantity
                    quantityChange(quantity)
                }
            ),
            contentAlignment = Alignment.Center
        )
        {
            Text(text = "–",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                )
            )
        }


        // for the quantity box
        Box(modifier = Modifier
            .width(35.dp)
            .height(30.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color.LightGray),
            contentAlignment = Alignment.Center
        )
        {
            // for quantity`
            Text(text = "$quantity",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            )
        }

        // for + button
        Box(modifier = Modifier
            .size(28.dp)
            .background(color = Color.Transparent)
            .clickable(indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    quantity = if (quantity < 5) quantity + 1
                    else quantity
                    // sending the quantity to call back function
                    quantityChange(quantity)
                }
            ),
            contentAlignment = Alignment.Center
        )
        {
            Text(text = "+",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                )
            )
        }
    }
}

// function to create the wish list and cart options
@Composable
fun WishlistAndCart(snackbarHostState: SnackbarHostState) {

    // variable for Coroutine scope
    val scope = rememberCoroutineScope()

    // list of wish list and cart icon
    val icons = listOf(R.drawable.wishlist, R.drawable.shopping_cart)

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 5.dp, end = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween)
    {
        icons.forEach { icon ->
            Box(modifier = Modifier
                .size(40.dp)
                .clip(shape = CircleShape)
                .border(shape = CircleShape, color = Color(0xFF578F44), width = 2.dp)
                .clickable(indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        if (icon == R.drawable.wishlist)
                            scope.launch {
                                // showing snackbar
                                snackbarHostState.showSnackbar(message = "Item added to wishlist",
                                    actionLabel = "Check Wishlist",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        else
                            scope.launch {
                                // showing snackbar
                                snackbarHostState.showSnackbar(message = "Item added to cart",
                                    actionLabel = "Go to cart",
                                    duration = SnackbarDuration.Short
                                    )
                            }
                    }
                ),
                contentAlignment = Alignment.Center
            )
            {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
