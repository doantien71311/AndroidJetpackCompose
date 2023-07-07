package com.example.myapplicationjetpackcompose.tuyendung.thongtinungvien

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlarmAdd
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.LocalPostOffice
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.OnlinePrediction
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.util.Calendar
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun ThongTinUngVienHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel
) {

    Dialog(
        onDismissRequest = {
            viewModel.isShowHenPhongVan = false
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),

        )
    {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Hẹn phỏng vấn") },
                    navigationIcon = {
                        IconButton(onClick = {
                            viewModel.isShowHenPhongVan = false

                        }) {
                            Icon(Icons.Default.Clear, "Thoát")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                )
            },

            content = {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Yellow)
                            .padding(it)
                            //.fillMaxHeight()
                           .verticalScroll(rememberScrollState())

                    )
                    {

                        UngVienDanhSachEmailhHenPhongVanScreen(viewModel)

                        FormHenPhongVanScreen(viewModel)


                    }


            }

        )


    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun UngVienDanhSachEmailhHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel
)
{

    Column (

        modifier = Modifier
            .padding(
            )
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Red)


    ) {

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Gởi email phỏng vấn (${viewModel.soluongPhongVan.toString()})",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Divider()

        LazyColumn(
        ) {
            itemsIndexed(
                items = viewModel.listPhongVan
            ) { index, item ->

                Card(

                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .padding(
                            bottom = 5.dp,
                            top = 5.dp,
                            start = 5.dp,
                            end = 5.dp

                        )
                        //.clip(RoundedCornerShape(20.dp))
                        .background(color = Color.Blue)
                        .fillMaxWidth()
                        .height(50.dp)
                        .animateContentSize()

                )
                {

                    Column (
                        modifier = Modifier
//                                        .padding(
//                                            it
//                                        )
                            //.width(100.dp)
                            //.height(50.dp)
                            .fillMaxWidth()
                            .background(color = Color.Green)
                        // .align(Alignment.CenterHorizontally)

                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = 10.dp
                                )
                            ,
                            text = item.email ?: ""
                        )
                    }
                }

            }
        }
    }

}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun FormHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel
) {
    Column(
        modifier = Modifier


    ) {

        CheckedOnlineHenPhongVanScreen(viewModel)

        LinkOnlineHenPhongVanScreen(viewModel)

        DiaDiemHenPhongVanScreen(viewModel)

        DatePickerHenPhongVanScreen(viewModel)

        TimePickerHenPhongVanScreen(viewModel)

        CheckHenNhacNhoScreen(viewModel)

        ThoiGianHenNhacNhoScreen(viewModel)

        ButtonXacNhanHenPhongVanScreen(viewModel)

    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun CheckedOnlineHenPhongVanScreen(

    viewModel: ThongTinUngVienViewModel

) {

    Spacer(modifier = Modifier.height(10.dp))
    Divider()
    Spacer(modifier = Modifier.height(10.dp))

    val checkedState = remember { mutableStateOf(true) }

    Text(
        text = "Phỏng vấn online",
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Left
    )

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun ButtonXacNhanHenPhongVanScreen(

    viewModel: ThongTinUngVienViewModel

) {
    Spacer(modifier = Modifier.height(10.dp))

    Button(

        onClick = {

        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(
                start = 20.dp,
                end = 20.dp
            )
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(text = "Xác nhận")
    }
    Spacer(modifier = Modifier.height(20.dp))
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun DiaDiemHenPhongVanScreen (
    viewModel: ThongTinUngVienViewModel

) {

    val diadiem_henphongvan_value: String by viewModel.diadiem_henphongvan.observeAsState(initial = "")

    Spacer(modifier = Modifier.height(10.dp))

    TextField(

        label = { Text(text = "Địa diểm phỏng vấn") },

        modifier = Modifier.fillMaxWidth(),
        value = diadiem_henphongvan_value,
        singleLine = true,
        maxLines = 1,
        onValueChange = { viewModel.onChangedDiaDiemHenPhongVan(it) },

        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.LocalPostOffice,
                contentDescription = null
            )
        }

    )

    Spacer(modifier = Modifier.height(10.dp))

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun LinkOnlineHenPhongVanScreen (
    viewModel: ThongTinUngVienViewModel

) {

    val diadiem_henphongvan_value: String by viewModel.diadiem_henphongvan.observeAsState(initial = "")

    Spacer(modifier = Modifier.height(10.dp))

    TextField(

        label = { Text(text = "Link phỏng vấn online") },

        modifier = Modifier.fillMaxWidth(),
        value = diadiem_henphongvan_value,
        singleLine = true,
        maxLines = 1,
        onValueChange = { viewModel.onChangedLinkPhongVanOnline(it) },

        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.OnlinePrediction,
                contentDescription = null
            )
        }

    )

    Spacer(modifier = Modifier.height(10.dp))

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable()
fun TimePickerHenPhongVanScreen(
    viewModel: ThongTinUngVienViewModel

)
{
    // Fetching local context
    val mContext = LocalContext.current

    // Declaring and initializing a calendar
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("Chọn thời gian") }

    // Creating a TimePicker dialod
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )


    //Divider()
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "Thời gian phỏng vấn",
        textAlign = TextAlign.Start)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                // Creating a button that on
                // click displays/shows the DatePickerDialog
                mTimePickerDialog.show()
            },
        //verticalArrangement = Arrangement.Center,
        // horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        Icon (
            imageVector = Icons.Default.Timer,
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp
                ),
            text = "${mTime.value}",
            textAlign = TextAlign.Start)

    }
    Spacer(modifier = Modifier.height(10.dp))
   // Divider()


}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DatePickerHenPhongVanScreen (
    viewModel: ThongTinUngVienViewModel
) {
    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("Chọn ngày") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth}/$mYear"
        }, mYear, mMonth, mDay
    )



    //Divider()
    Spacer(modifier = Modifier.height(10.dp))

    Text(text = "Ngày phỏng vấn",
        textAlign = TextAlign.Start)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                // Creating a button that on
                // click displays/shows the DatePickerDialog
                mDatePickerDialog.show()
            },
        //verticalArrangement = Arrangement.Center,
       // horizontalAlignment = Alignment.CenterHorizontally

    )
    {

        Icon (
            imageVector = Icons.Default.Today,
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp
                ),
            text = "${mDate.value}",
            textAlign = TextAlign.Start)

    }

    Spacer(modifier = Modifier.height(10.dp))
    //Divider()
    //Spacer(modifier = Modifier.height(10.dp))

}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CheckHenNhacNhoScreen (
    viewModel: ThongTinUngVienViewModel
) {
    Spacer(modifier = Modifier.height(10.dp))

    val checkedState = remember { mutableStateOf(true) }

    Text(text="Nhắc nhở")
    Row (
        modifier = Modifier
        .fillMaxWidth()
            )
    {

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )

    }
    Spacer(modifier = Modifier.height(10.dp))

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ThoiGianHenNhacNhoScreen (
    viewModel: ThongTinUngVienViewModel
)
{
    Spacer(modifier = Modifier.height(10.dp))

    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()

    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field for correctness.
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text("Thời gian nhắc việc trước") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },

            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AlarmAdd,
                    contentDescription = null
                )
            },

            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))


    }
}



