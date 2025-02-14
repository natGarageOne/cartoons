package com.quiz.cartoons.presentation.view


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.quiz.cartoons.R
import com.quiz.cartoons.data.Person
import com.quiz.cartoons.presentation.view.modal.PersonDetailModal
import com.quiz.cartoons.presentation.viewmodel.CartoonsViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartoonScreen(
    cartoonsViewModel: CartoonsViewModel
) {
    val context = LocalContext.current
    var showModal by remember { mutableStateOf(false) }
    var selectedPerson by remember { mutableStateOf<Person?>(null) }
    val cartoons by cartoonsViewModel.cartoons.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.space))
    val onRefresh: () -> Unit = {
        isRefreshing = true
        coroutineScope.launch {
            cartoonsViewModel.getInitials(context)
            composition?.duration.let {
                kotlinx.coroutines.delay(composition!!.duration.toLong())
            }
            isRefreshing = false
        }
    }
    cartoonsViewModel.getInitials(context)


    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF101438))
    ) {
        if (isRefreshing || cartoons.listPerson == null) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.fillMaxSize()

            )
        } else {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp)
                        .wrapContentHeight(),
                    text = "List of characters",
                    fontSize = 28.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        lineHeight = 20.sp,
                        fontWeight = FontWeight(800)
                    )
                )
                PullToRefreshBox(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                    indicator = {}) {

                    LazyColumn(Modifier.fillMaxSize()) {
                        if (!isRefreshing) {
                            cartoons.listPerson?.let { list ->
                                items(list) { person -> CharacterItem(person){person ->
                                    selectedPerson = person
                                    showModal = true
                                } }
                            }
                        }
                    }
                }

            }
        }

        if (showModal){
            selectedPerson?.let { person ->
                PersonDetailModal(person){
                    showModal = false
                }
            }
        }
    }
}

@Composable
fun CharacterItem(person: Person, onClickPerson: (person: Person) -> Unit) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickPerson(person) },
        shape = RoundedCornerShape(16.dp),
        colors = CardColors(
            containerColor =  Color(0xFF2F3267),
            contentColor =  Color(0xFFECEFF1),
            disabledContentColor =  Color(0xFFCFD8DC),
            disabledContainerColor =  Color(0xFFCFD8DC)
        ),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = person.image,
                contentDescription = person.name,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RectangleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.padding(start = 5.dp)) {
                Text(text = person.name, style = MaterialTheme.typography.bodyLarge,  modifier = Modifier.padding(bottom = 5.dp),)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card (
                        modifier = Modifier
                            .size(15.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardColors(
                            containerColor =
                                when(person.status){
                                    "Alive" -> Color(0xFF00943D)
                                    "Dead" -> Color(0xFF930000)
                                    else -> Color(0xFFFF9100)
                                },
                            contentColor =  Color(0xFFECEFF1),
                            disabledContentColor =  Color(0xFFCFD8DC),
                            disabledContainerColor =  Color(0xFFCFD8DC)
                        ),

                        ){}
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "Status: ${person.status}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Text(text = "Gender: ${person.gender}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Specie: ${person.species}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}