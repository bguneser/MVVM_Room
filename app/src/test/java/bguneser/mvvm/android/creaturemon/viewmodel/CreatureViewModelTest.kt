package bguneser.mvvm.android.creaturemon.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bguneser.mvvm.android.creaturemon.model.Creature
import bguneser.mvvm.android.creaturemon.model.CreatureAttributes
import bguneser.mvvm.android.creaturemon.model.CreatureGenerator
import bguneser.mvvm.android.creaturemon.model.CreatureRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/*
Testing View Model
 */

class CreatureViewModelTest{

    private lateinit var  creatureViewModel: CreatureViewModel

    @get:Rule
    var rule : TestRule =InstantTaskExecutorRule()

    /*
    creatureview model to tested
     */

    @Mock
    lateinit var mockGenerator : CreatureGenerator

    @Mock
    lateinit var repository: CreatureRepository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        creatureViewModel= CreatureViewModel(mockGenerator,repository)
    }

    @Test
    fun testSetupCreature(){
        val attributes = CreatureAttributes(10,3,7)
        val stubCreature = Creature(attributes,87,"Test Creature")
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        creatureViewModel.intelligence=10
        creatureViewModel.strength=3
        creatureViewModel.endurance=7

        creatureViewModel.updateCreature()

        assertEquals(stubCreature,creatureViewModel.creature)
    }

    @Test
    fun testCantSaveCreatureWithBlankName(){

        creatureViewModel.intelligence=10
        creatureViewModel.strength=3
        creatureViewModel.endurance=7
        creatureViewModel.drawable=1
        creatureViewModel.name.set("")

        val canSaveCreature = creatureViewModel.canSaveCreature()

        assertEquals(false,canSaveCreature)

    }

    @Test
    fun testCantSaveCreatureWithoutStrength(){

        creatureViewModel.intelligence=10
        creatureViewModel.strength=0
        creatureViewModel.endurance=7
        creatureViewModel.drawable=1
        creatureViewModel.name.set("My creature")


        val canSaveCreature = creatureViewModel.canSaveCreatureWithoutStrength()

        assertEquals(false,canSaveCreature)

    }


}