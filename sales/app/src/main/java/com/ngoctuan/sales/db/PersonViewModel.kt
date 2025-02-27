package com.ngoctuan.sales.db


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class PersonViewModel(app: Application) : ViewModel() {

    private val personRepository: PersonRepository = PersonRepository(app)
    fun updatePerson(account: Person) = viewModelScope.launch {
        personRepository.updatePersonByID(account)
    }

    fun insertPerson(account: Person) = viewModelScope.launch {
        personRepository.insertPerson(account)
    }

    fun getAllPeople() = personRepository.getAllPeople()
    fun deleteAllPeople() = personRepository.deleteAllPeople()
    class AccountViewModelFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PersonViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PersonViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}