package com.example.codelabsmysoothe

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelabsmysoothe.ui.theme.CodeLabsMySootheTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeLabsMySootheTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxHeight(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

// Preview -----------------------------------------------------------------------------------------
@Preview(
    name = "light theme",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "dark theme",
    group = "themes",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun MainColumnPreview() {
    HomeColumn()
}

//@Preview(
//    name = "light theme",
//    showBackground = true,
//    uiMode = UI_MODE_NIGHT_NO
//)
//@Preview(
//    name = "dark theme",
//    group = "themes",
//    uiMode = UI_MODE_NIGHT_YES
//)
//@Composable
//fun AlignYourBodyPreview() {
//    CodeLabsMySootheTheme {
//        AlignYourBodyElement(
//            drawable = R.drawable.ab1_inversions,
//            text = R.string.ab1_inversions
//        )
//    }
//}
//
//@Preview(
//    name = "light theme",
//    showBackground = true,
//    uiMode = UI_MODE_NIGHT_NO
//)
//@Preview(
//    name = "dark theme",
//    group = "themes",
//    uiMode = UI_MODE_NIGHT_YES
//)
//@Composable
//fun FavoriteCollectionCardPreview() {
//    CodeLabsMySootheTheme {
//        Surface {
//            FavoriteCollectionCard(
//                drawable = R.drawable.fc2_nature_meditations,
//                text = R.string.fc2_nature_meditations
//            )
//        }
//    }
//}
//
//@Preview(
//    name = "light theme",
//    showBackground = true,
//    uiMode = UI_MODE_NIGHT_NO
//)
//@Preview(
//    name = "dark theme",
//    group = "themes",
//    uiMode = UI_MODE_NIGHT_YES
//)
//@Composable
//fun HomeBottomNavigationPreview() {
//    HomeBottomNavigation()
//}

// Widgets -----------------------------------------------------------------------------------------

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp() {
    CodeLabsMySootheTheme {
        Surface(
            modifier = Modifier
                .background(MaterialTheme.colors.error),
            color = Color.Black
        ) {
            Scaffold(
                bottomBar = { HomeBottomNavigation() }
            ) {
                HomeColumn()
            }
        }
    }
}

@Composable
fun HomeColumn() {
    CodeLabsMySootheTheme {
        Surface(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(1f)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar()
                HomeSection(
                    { AlignYourBodyLabel() },
                    { AlignYourBodyRow() }
                )
                HomeSection(
                    { FavoriteCollectionsLabel() },
                    { FavoriteCollectionsGrid() }
                )
                Spacer(modifier = Modifier.fillMaxHeight())
            }
        }
    }
}

@Composable
fun SearchBar() {
    CodeLabsMySootheTheme {
        Surface(
            elevation = 5.dp,
            modifier = Modifier
                .heightIn(min = 56.dp)
                .padding(all = 8.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            TextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null)
                },
                placeholder = {
                    Text(stringResource(id = R.string.placeholder_search))
                },
            )
        }
    }
}

@Composable
fun HomeSection(
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Column {
        title()
        content()
    }
}

@Composable
fun AlignYourBodyLabel() {
    CodeLabsMySootheTheme {
        Surface {
            Text(
                "ALIGN YOUR BODY",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 35.dp, bottom = 8.dp),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun AlignYourBodyRow() {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        modifier = Modifier
    ) {
        items(alignYourBodyData) { element ->
            AlignYourBodyElement(
                drawable = element.drawable,
                text = element.text
            )
        }
    }
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    CodeLabsMySootheTheme {
        Surface(
            elevation = 5.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(all = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(all = 8.dp),
            ) {
                Image(
                    painterResource(id = drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(88.dp)
                )
                Text(
                    stringResource(id = text),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun FavoriteCollectionsLabel() {
    CodeLabsMySootheTheme {
        Surface {
            Text(
                "FAVORITE COLLECTIONS",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 40.dp, bottom = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun FavoriteCollectionsGrid() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 1.dp
        ),
        modifier = Modifier
            .height(120.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text
            )
        }
    }
}


@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int
) {
    CodeLabsMySootheTheme {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(200.dp),
            elevation = 5.dp
        ) {
            Row {
                Image(
                    painterResource(id = drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                )
                Text(
                    stringResource(id = text),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun HomeBottomNavigation() {
    BottomNavigation(
    ) {
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text(stringResource(id = R.string.bottom_navigation_home)) }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Person, contentDescription = null) },
            label = { Text(stringResource(id = R.string.bottom_navigation_profile)) }
        )
    }
}

private fun Modifier.bottomElevation(): Modifier = this.then(Modifier.drawWithContent {
    val paddingPx = 8.dp.toPx()
    clipRect(
        left = 0f,
        top = 0f,
        right = size.width,
        bottom = size.height + paddingPx
    ) {
        this@drawWithContent.drawContent()
    }
})


// Models ------------------------------------------------------------------------------------------
private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)