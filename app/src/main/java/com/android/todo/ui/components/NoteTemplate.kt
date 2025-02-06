package com.android.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun NoteTemplate(

) {
    Column(
        modifier = Modifier
            .height(280.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.Cyan)
            .padding(10.dp)
//            .verticalScroll(rememberScrollState())

    ) {
        Text(
            modifier = Modifier
                .padding(10.dp),
            fontSize = 20.sp,
            text = "Note 1",
            fontWeight = FontWeight.W500

        )
        Text(
            fontSize = 20.sp,
            text = " reueyyweryweryweruiweyuiwegfugfidhf" +
                "hhgjegfjregrehgireg" +
                "geghiregrue" +
                "ghrgb")
    }

}