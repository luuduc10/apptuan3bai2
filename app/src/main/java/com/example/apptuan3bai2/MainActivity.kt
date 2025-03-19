package com.example.apptuan3bai2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash_screen") {
                composable("splash_screen") { SplashScreen(navController) }
                composable("onboarding1") { OnboardingScreen1(navController) }
                composable("onboarding2") { OnboardingScreen2(navController) }
                composable("onboarding3") { OnboardingScreen3(navController) }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavHostController) {
    var alpha by remember { mutableStateOf(0f) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(durationMillis = 2000)
    )

    LaunchedEffect(Unit) {
        alpha = 1f
        delay(2500)
        navController.navigate("onboarding1") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.uth_logo),
                contentDescription = "UTH Logo",
                modifier = Modifier.alpha(animatedAlpha)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "UTH SmartTasks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.alpha(animatedAlpha)
            )
        }
    }
}

@Composable
fun OnboardingScreen1(navController: NavHostController) {
    OnboardingScreen(
        imageRes = R.drawable.onboarding_image1,
        title = "Easy Time Management",
        description = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
        showBackButton = false,
        onBackClick = {},
        buttonText = "Next",
        onNextClick = { navController.navigate("onboarding2") },
        showSkipButton = true,
        onSkipClick = { navController.navigate("onboarding3") },
        largeCenteredButton = true
    )
}

@Composable
fun OnboardingScreen2(navController: NavHostController) {
    OnboardingScreen(
        imageRes = R.drawable.onboarding_image2,
        title = "Increase Work Effectiveness",
        description = "Time management and the determination of more important tasks will give your job statistics better and always improve",
        showBackButton = true,
        onBackClick = { navController.navigate("onboarding1") },
        buttonText = "Next",
        onNextClick = { navController.navigate("onboarding3") },
        showSkipButton = true,
        onSkipClick = { navController.navigate("onboarding3") },
        largeCenteredButton = true
    )
}

@Composable
fun OnboardingScreen3(navController: NavHostController) {
    OnboardingScreen(
        imageRes = R.drawable.onboarding_image3,
        title = "Reminder Notification",
        description = "The advantage of this application is that it also provides reminders so you don’t forget to keep doing your assignments well and according to the time you have set",
        showBackButton = true,
        onBackClick = { navController.navigate("onboarding2") },
        buttonText = "Get Started",
        onNextClick = { navController.navigate("onboarding1") },
        showSkipButton = true,
        onSkipClick = { navController.navigate("onboarding1") },
        largeCenteredButton = true
    )
}

@Composable
fun OnboardingScreen(
    imageRes: Int,
    title: String,
    description: String,
    showBackButton: Boolean,
    onBackClick: () -> Unit,
    buttonText: String,
    onNextClick: () -> Unit,
    showSkipButton: Boolean = false,
    onSkipClick: (() -> Unit)? = null,
    largeCenteredButton: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.padding(start = 16.dp)) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .padding(4.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(50))
                    )
                }
            }
            if (showSkipButton) {
                TextButton(onClick = { onSkipClick?.invoke() }) {
                    Text(text = "Skip", fontSize = 16.sp, color = Color.Blue)
                }
            }
        }
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Onboarding Illustration",
            modifier = Modifier.fillMaxWidth().height(250.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = description, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.padding(horizontal = 20.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton) {
                Button(
                    onClick = onBackClick,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.size(50.dp)
                        .align(Alignment.CenterVertically),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp) // Đảm bảo icon đủ lớn
                    )
                }
            }
            Button(
                onClick = onNextClick,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .weight(0.7f) // Tăng kích thước nút next/get started
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = buttonText, fontSize = 22.sp, color = Color.White)
            }
        }
    }
}
