import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.SpellDetail
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import request.SpellApiClient.getSpells

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SharedActivity() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello, World!") }
        var showImage by remember { mutableStateOf(false) }
        var spells by remember { mutableStateOf(value = emptyList<SpellDetail>()) }
        runBlocking {
            spells = getSpells().results
        }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                greetingText = "Hello, ${getPlatformName()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }
            AnimatedVisibility(
                visible = showImage
            ) {
//                Image(
//                    painterResource("compose-multiplatform.xml"),
//                    contentDescription = "Compose Multiplatform icon"
//                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    spells.forEach { spell ->
                        item {
                            spellSmallCard(spell)
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun spellSmallCard(spell: SpellDetail) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .height(70.dp)
        ,
        backgroundColor = Color(0xFF111418),
        shape = RoundedCornerShape(25.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = spell.name,
                    color = Color(0xffffffff),
                    textAlign = TextAlign.Center
                )
            }
            Card(
                backgroundColor = Color(0xff1ed55f),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = spell.school,
                    textAlign = TextAlign.Center,
                    color = Color(0xffffffff),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }
    }

}

expect fun getPlatformName(): String