package org.betaapps.foodie.presentation.core.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import org.betaapps.foodie.data.model.User
import org.betaapps.foodie.presentation.profile.ProfileScreen
import org.betaapps.foodie.presentation.shoppingcart.ShoppingCartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedAppBar(
    modifier: Modifier = Modifier,
    user: User,
    basketItemsNumber: Int = 0,
    navigator: Navigator
) {

    TopAppBar(
        modifier = modifier,
        title = { Text("Food App") },
        actions = {
            IconButton(
                onClick = {
                    navigator.push(ProfileScreen())
                }
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null
                )
            }

            BadgedBox(
                badge = {
                    if (user.order?.items?.isNotEmpty() == true) {
                        Badge(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ) {
                            Text(basketItemsNumber.toString())
                        }
                    }
                }
            ) {
                IconButton(
                    onClick = {
                        navigator.push(ShoppingCartScreen())
                    }
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
        )
    )

}