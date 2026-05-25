package com.example.application244

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    val username = mutableStateOf("")
    val phone = mutableStateOf("")
    val email = mutableStateOf("")

    fun updateName(newValue: String){
        username.value = newValue
    }
    fun updatePhone(newValue: String){
        phone.value = newValue
    }
    fun updateEmail(newValue: String){
        email.value = newValue
    }

    fun saveCurrentProfile(){
        viewModelScope.launch {
            val newProfile = UserProfile(
                name = username.value.trim(),
                phone = phone.value.trim(),
                email = email.value.trim()
            )
            profileRepository.saveProfileData(profile = newProfile)
            updateName(newValue = "")
            updatePhone(newValue = "")
            updateEmail(newValue = "")
        }
    }

    val getCurrentProfile = profileRepository.getProfile.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserProfile()
    )

    fun shareProfile(context: Context, profile: UserProfile){
        val text = """
            Данные профиля
            Имя: ${profile.name}
            Телефон: ${profile.phone}
            Email: ${profile.email}
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Поделиться профилем")
        context.startActivity(shareIntent)
    }
}