package com.example.myapplicationjetpackcompose.test.ColumnAndRow

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationjetpackcompose.EnumCoKhong
import com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien.ThongTinUngVienHenPhongVanEvent
import com.example.myapplicationjetpackcompose.ui.theme.MyApplicationJetpackComposeTheme

@Composable
fun OutlineTextFieldScreen (
)

{

    var ma_nsd = remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        modifier = Modifier
            .padding(
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        value = ma_nsd.value,
        onValueChange = {

        },
        label = { Text("Số phút nhắc nhở trước") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.AccessTime,
                contentDescription = null
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OutlineTextFieldScreenPreview() {
    MyApplicationJetpackComposeTheme {
        OutlineTextFieldScreen()
    }
}