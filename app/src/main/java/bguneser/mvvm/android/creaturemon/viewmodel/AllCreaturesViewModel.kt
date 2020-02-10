package bguneser.mvvm.android.creaturemon.viewmodel

import android.arch.lifecycle.ViewModel
import bguneser.mvvm.android.creaturemon.model.CreatureRepository
import bguneser.mvvm.android.creaturemon.model.room.RoomRepository


class AllCreaturesViewModel (private val repository: CreatureRepository = RoomRepository()) : ViewModel(){

    private val allCreaturesLiveData = repository.getAllCreatures()

    fun getAllCreaturesLiveData () = allCreaturesLiveData

    fun clearAllCreatures() = repository.clearAllCreatures()

}