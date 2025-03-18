package com.example.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.ExperimentalFoundationApi
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingScreen()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(pageCount = { 4 })
    val coroutineScope = rememberCoroutineScope()

    val images = listOf(
        R.drawable.anh1,
        R.drawable.anh2,
        R.drawable.anh3,
        R.drawable.anh4
    )
    val titles = listOf(
        "UTH SmartTasks",
        "Easy Time Management",
        "Increase Work Effectiveness",
        "Reminder Notification"
    )
    val descriptions = listOf(
        "",
        "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first.",
        "Time management and the determination of more important tasks will give your job statistics better and always improve.",
        "The advantage of this application is that it also provides reminders for you so you don’t forget to keep doing your assignments well and according to the time you have set."
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pagerState) { page ->
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = titles[page], fontSize = 24.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = descriptions[page], fontSize = 16.sp)
                Spacer(modifier = Modifier.height(32.dp))

                // Row chứa nút "Quay lại" & "Tiếp theo" hoặc "Bắt đầu"
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Nút quay lại (chỉ hiển thị khi không phải trang đầu tiên)
                    if (page > 0) {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page - 1)
                            }
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.back), // Đổi lại tên ảnh thành `back.png`
                                contentDescription = "Back",
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.size(48.dp)) // Giữ căn chỉnh
                    }

                    // Nút "Next" hoặc "Get Started"
                    if (page == images.lastIndex) {
                        Button(onClick = { /* Navigate to main screen */ }) {
                            Text("Get Started")
                        }
                    } else {
                        Button(onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page + 1)
                            }
                        }) {
                            Text("Next")
                        }
                    }
                }
            }
        }
    }
}
