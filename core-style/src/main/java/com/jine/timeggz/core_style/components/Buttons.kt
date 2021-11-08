package com.jine.timeggz.core_style.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    onClick: (() -> Unit) = {}
) {
    Button(
        modifier = modifier
            .width(56.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f),
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.38f),
        ),
        onClick = onClick
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = it,
                contentDescription = null
            )
        }
    }
}

@Composable
fun BigButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .width(96.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(8.dp),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = icon,
            contentDescription = null
        )
    }
}
