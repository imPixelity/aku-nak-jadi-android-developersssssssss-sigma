package com.vitreon.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitreon.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold() { paddingValues ->
                    ProfileScreen(modifier = Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.background(colorResource(R.color.green_bg))
    ) {
        ProfileHeader(modifier = Modifier.weight(1f))
        ContactInformation()
    }
}

@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = "Android Logo",
            modifier = Modifier
                .size(100.dp)
                .background(colorResource(R.color.black))
        )

        Text(
            text = stringResource(id = R.string.profile_name),
            fontSize = 36.sp
        )

        Text(
            text = stringResource(id = R.string.profile_profession),
            color = colorResource(id = R.color.green),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContactInformation() {
    val contactFields = listOf<ContactData>(
        ContactData(
            icon = painterResource(id = R.drawable.phone),
            iconDescription = "Phone Number",
            text = stringResource(id = R.string.phone_number)
        ),
        ContactData(
            icon = painterResource(id = R.drawable.share),
            iconDescription = "Share",
            text = stringResource(id = R.string.share)
        ),
        ContactData(
            icon = painterResource(id = R.drawable.mail),
            iconDescription = "Email",
            text = stringResource(id = R.string.email)
        )
    )

    Column(
        modifier = Modifier.wrapContentWidth()
    ) {
        for (contact in contactFields) {
            ContactField(data = contact)
        }
    }
}

data class ContactData(
    val icon: Painter,
    val iconDescription: String,
    val text: String
)

@Composable
fun ContactField(data: ContactData) {
    Row {
        Icon(
            painter = data.icon,
            contentDescription = data.iconDescription
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = data.text
        )
    }
}