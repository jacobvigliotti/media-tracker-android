package edu.metrostate.ics342.mediatracker.ui.auth

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.theme.OnPrimaryContainer
import edu.metrostate.ics342.mediatracker.theme.Primary
import edu.metrostate.ics342.mediatracker.theme.PrimaryContainer
import edu.metrostate.ics342.mediatracker.ui.auth.RegisterViewModel.RegisterUiState
import kotlinx.coroutines.flow.compose

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel(),
) {
    val displayName by viewModel.displayName.collectAsState()
    val username by viewModel.username.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val focusManager = LocalFocusManager.current
    val registerState by viewModel.registerState.collectAsState()
    val errorMsg  = (registerState as? RegisterViewModel.RegisterUiState.Error)?.msgResId?.let { stringResource(it) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(id = edu.metrostate.ics342.mediatracker.R.drawable.smart_display),
            contentDescription = "App Icon",
            modifier = Modifier
                .size(width = 72.dp, height = 72.dp)
                .background(color = PrimaryContainer, shape = RoundedCornerShape(size = 12.dp))
                .padding(all = 12.dp),
            colorFilter = ColorFilter.tint(color = Primary)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            stringResource(edu.metrostate.ics342.mediatracker.R.string.register_create_account),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            stringResource(edu.metrostate.ics342.mediatracker.R.string.register_tagline),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(40.dp))

        OutlinedTextField(
            value = displayName,
            onValueChange = viewModel::onDisplayNameChange,
            label = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_displayName)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))


        OutlinedTextField(
            value = username,
            onValueChange = viewModel::onUsernameChange,
            label = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_username)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))


        OutlinedTextField(
            value = email,
            onValueChange = viewModel::onEmailChange,
            label = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_email)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))


        OutlinedTextField(
            value = password,
            onValueChange = viewModel::onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_password)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))


        OutlinedTextField(
            value = confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            label = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_confirmPassword)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(); viewModel.onSignUpClicked() }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMsg != null) {
            Spacer(Modifier.height(8.dp))
            Text(errorMsg, color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { focusManager.clearFocus(); viewModel.onSignUpClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_sign_up_button))
        }


        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_back_to_login))
        }
    }
}

