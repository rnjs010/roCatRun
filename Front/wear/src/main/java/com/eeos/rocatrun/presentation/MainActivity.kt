package com.eeos.rocatrun.presentation

import android.Manifest
import android.util.Log
import android.os.Bundle
import android.os.Build
import androidx.annotation.RequiresApi
import android.net.Uri
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import com.eeos.rocatrun.R
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Wearable
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SplashScreen()
        }

        requestPermissions()
    }

    override fun onDestroy() {
        super.onDestroy()
        finishAndRemoveTask()
    }
    // 모바일 앱 실행 요청 함수
    fun startMobileApp() {
        val messageClient: MessageClient = Wearable.getMessageClient(this)
        val path = "/start_mobile_app"
        val messageData = "Start Game".toByteArray()

        Wearable.getNodeClient(this).connectedNodes.addOnSuccessListener { nodes ->
            if (nodes.isNotEmpty()) {
                val nodeId = nodes.first().id
                Log.d("WearApp", "연결된 노드: ${nodes.first().displayName}")

                messageClient.sendMessage(nodeId, path, messageData).apply {
                    addOnSuccessListener {
                        Log.d("Wear APP", "메시지 전송 성공")
                        Toast.makeText(this@MainActivity, "모바일 앱 시작 요청 전송 완료", Toast.LENGTH_SHORT).show()
                    }
                    addOnFailureListener {
                        Toast.makeText(this@MainActivity, "모바일 앱 전송 실패: ${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Log.d("WearApp", "연결된 노드가 없습니다.")
                Toast.makeText(this, "연결된 디바이스가 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
     fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BODY_SENSORS,
            Manifest.permission.ACTIVITY_RECOGNITION,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH_SCAN,
            Manifest.permission.POST_NOTIFICATIONS,
        )
        if (permissions.any {
                ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
            }) {
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
    }
}
@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val mainActivity = context as MainActivity  // MainActivity의 메서드 호출을 위해 캐스팅
    val versionText = context.getString(R.string.app_version)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "로캣냥",
            style = TextStyle(
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.neodgm))
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "폰으로 이동해서\n게임을 시작해주세요",
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.neodgm)),
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(20.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 확인 버튼
            Button(
                onClick = {
                    mainActivity.startMobileApp()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00FFCC)
                ),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .width(69.dp)
                    .height(34.dp)
                    .padding(horizontal = 2.dp)
            ) {
                Text(
                    text = "확인",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.neodgm))
                    ),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    softWrap = false  // 줄바꿈 방지
                )
            }


            // 게임 시작 버튼
//            Button(
//                onClick = {
//                    Log.i("확인", "ㅇㅇㅇ $context")
//                    val intent = Intent(context, RunningActivity::class.java)
//                    context.startActivity(intent)
//                    Log.i("로그인", "시도")
//                },
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFFFFA500)
//                ),
//                shape = RoundedCornerShape(16.dp),
//                contentPadding = PaddingValues(0.dp),
//                modifier = Modifier
//                    .width(69.dp)
//                    .height(34.dp)
//                    .padding(horizontal = 3.dp)
//            ) {
//                Text(
//                    text = "게임",
//                    style = TextStyle(
//                        fontSize = 16.sp,
//                        color = Color.Black,
//                        fontWeight = FontWeight.Bold,
//                        fontFamily = FontFamily(Font(R.font.neodgm))
//                    ),
//                    textAlign = TextAlign.Center,
//                    maxLines = 1,
//                    softWrap = false  // 줄바꿈 방지
//                )
//            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // 버전 정보 표시
        Text(
            text = versionText,
            style = TextStyle(
                fontSize = 10.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        )
    }
}