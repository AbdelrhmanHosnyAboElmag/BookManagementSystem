package com.example.bookstoremanageruser.presentation.ui.compound

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoremanageruser.R
import com.example.bookstoremanageruser.data.local.entity.BookEntity

@Composable
fun ItemBook(
    bookEntityItem: BookEntity,
    modifier: Modifier = Modifier,
    onItemClick: (BookEntity) -> Unit) {
    Row(
        modifier = modifier
            .clickable { onItemClick(bookEntityItem) }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = bookEntityItem.title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = bookEntityItem.author,
                style = TextStyle(color = Color.Gray, fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = bookEntityItem.publishDate,
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
                Text(
                    text = bookEntityItem.reservation.name,
                    style = TextStyle(color = Color.Gray, fontSize = 14.sp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(getBookTypeColor(bookEntityItem.bookType))
                .clip(RoundedCornerShape(4.dp))
        ) {
            Text(
                text = bookEntityItem.bookType.name,
                style = TextStyle(color = Color.White, fontSize = 12.sp),
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}