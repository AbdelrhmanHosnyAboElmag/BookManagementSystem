package com.example.bookstoremanageruser.presentation.ui.compound

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoremanageruser.R
import com.example.bookstoremanageruser.data.local.entity.BookReservedEntity
import com.example.bookstoremanageruser.data.local.entity.BookType

@Composable
fun ItemBookReserved(
    bookEntityItem: BookReservedEntity,
    modifier: Modifier = Modifier,
    itemDelete: (Long) -> Unit,
    onItemClick: (BookReservedEntity) -> Unit,
) {
    Row(
        modifier = modifier
            .clickable { onItemClick(bookEntityItem) }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Book cover image
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(72.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )

        // Book details
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = bookEntityItem.title,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = bookEntityItem.author,
                style = TextStyle(color = Color.Gray, fontSize = 14.sp)
            )
            Text(
                text = bookEntityItem.optionalNotes,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.Black
                )
            )
        }

        // Delete button
        IconButton(
            onClick = { itemDelete(bookEntityItem.id) },
            modifier = Modifier.size(36.dp)
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null, tint = Color.Red)
        }

        // Book type icon (replace with your actual icons)
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = when (bookEntityItem.bookType) {
                    BookType.HORROR -> Icons.Default.Face
                    BookType.ADVENTURE -> Icons.Default.Warning
                    BookType.FICTION -> Icons.Default.Star
                    BookType.ACTION -> Icons.Default.Build
                    BookType.SCIENTIFIC -> Icons.Default.Create
                },
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
