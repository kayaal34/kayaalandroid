package com.example.kayaalandroid.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kayaalandroid.datastore.ProfilePreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val profilePreferences = ProfilePreferences(application.applicationContext)

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _resumeUrl = MutableStateFlow("")
    val resumeUrl: StateFlow<String> = _resumeUrl

    private val _jobTitle = MutableStateFlow("")
    val jobTitle: StateFlow<String> = _jobTitle

    fun saveProfile(name: String, resumeUrl: String, avatarUri: String, jobTitle: String) {
        _name.value = name
        _resumeUrl.value = resumeUrl
        _avatarUri.value = avatarUri
        _jobTitle.value = jobTitle
    }

    private val _avatarUri = MutableStateFlow("")
    val avatarUri: StateFlow<String> = _avatarUri

    init {
        viewModelScope.launch {
            profilePreferences.profileFlow.collectLatest { profile ->
                _name.value = profile.name
                _resumeUrl.value = profile.resumeUrl
                _avatarUri.value = profile.avatarUri
                _jobTitle.value = profile.jobTitle
            }
        }
    }


    }

