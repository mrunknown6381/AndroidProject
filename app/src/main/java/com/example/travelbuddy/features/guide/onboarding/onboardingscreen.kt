package com.example.travelbuddy.features.guide.onboarding

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelbuddy.appcomponents.Pageindicator
import com.example.travelbuddy.appcomponents.textbutton
import com.example.travelbuddy.data.dimens.MP
import com.example.travelbuddy.data.dimens.pageindicatorwidth
import com.example.travelbuddy.navigation.separate.routes
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun onboardingscreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("","Next")
                    1 -> listOf("Back","Next")
                    2 -> listOf("Back","Get Started")
                    else -> listOf("","")
                }
            }
        }
        HorizontalPager(state = pagerState) {index ->
            onboardingpage(modifier = Modifier, page = pages[index])
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MP)
            .navigationBarsPadding(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Pageindicator(modifier = Modifier.width(pageindicatorwidth), pageSize = pages.size, selectedPage = pagerState.currentPage)
            Row (verticalAlignment = Alignment.CenterVertically){
                val scope = rememberCoroutineScope()
                if(buttonState.value[0].isNotEmpty()) {
                    textbutton(text = buttonState.value[0], onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    })
                }

                Spacer(modifier = Modifier.width(10.dp))
                textbutton(text = buttonState.value[1], onClick = {

                    scope.launch {
                        if(pagerState.currentPage == 2){
                            navController.navigate(routes.Screenwelcome.routes)
                        }else{
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage+1
                            )
                        }

                    }
                }
                    )
            }
        }
        Spacer(modifier = Modifier.weight(0.03f))
    }
}
