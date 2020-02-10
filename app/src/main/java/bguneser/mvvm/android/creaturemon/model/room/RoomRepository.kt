package bguneser.mvvm.android.creaturemon.model.room

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import bguneser.mvvm.android.creaturemon.app.CreaturemonApplication.Companion.database
import bguneser.mvvm.android.creaturemon.model.Creature
import bguneser.mvvm.android.creaturemon.model.CreatureRepository


class RoomRepository : CreatureRepository {
  private val creatureDao: CreatureDao = database.creatureDao()

  private val allCreatures : LiveData<List<Creature>>

  init {
    allCreatures=creatureDao.getAllCreatures()
  }


  private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.insert(params[0])
      return null
    }
  }

  private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.clearCreatures(*params)
      return null
    }
  }

  override fun saveCreature(creature: Creature) {

    InsertAsyncTask(creatureDao).execute(creature)
  }

  override fun getAllCreatures()= allCreatures

  override fun clearAllCreatures() {
    val creatureArray =allCreatures.value?.toTypedArray()

    if(creatureArray != null){
      DeleteAsyncTask(creatureDao).execute(*creatureArray)
    }
  }
}