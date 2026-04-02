package com.example.productviewersample.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.productviewersample.getFavouritesList
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class FavouritesDBTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var favouritesDB: FavouritesDB
    @Inject
    lateinit var favouritesDao: FavouritesDao

    private val sourceFavourites = getFavouritesList()
    private suspend fun insertAllFavourites(){
        favouritesDao.addToFavourites(*sourceFavourites.toTypedArray())
    }

    @Before
    fun setUp(){
            hiltRule.inject()
    }

    @After
    fun reset(){
        favouritesDB.close()
    }



    //input data/get data tests
    @Test
    fun addToFavourites_getAllFavourites_validInputOutput() = runTest{
        insertAllFavourites()

        val resultList= favouritesDao.getAllFavourites().first()

        assertThat(resultList.size).isEqualTo(sourceFavourites.size)
        assertThat(resultList.first()).isEqualTo(sourceFavourites.first())

    }

    @Test
    fun getAllFavourites_emptyData_returnEmpty() = runTest {
        val resultList= favouritesDao.getAllFavourites().first()

        assertThat(resultList.size).isNotEqualTo(sourceFavourites.size)
        assertThat(resultList).isEmpty()
    }

    @Test
    fun addToFavourites_singleEntity_validOutput() = runTest{
        favouritesDao.addToFavourites(sourceFavourites[0])

        val resultList = favouritesDao.getAllFavourites().first()

        assertThat(resultList.size).isEqualTo(1)
        assertThat(resultList.first()).isEqualTo(sourceFavourites.first())
        assertThat(resultList).doesNotContain(sourceFavourites[1])

    }

    //delete tests
    @Test
    fun deleteFromFavourites_properEntityDeleted() = runTest {
        insertAllFavourites()

        favouritesDao.deleteFromFavourites(sourceFavourites[0])

        val resultList = favouritesDao.getAllFavourites().first()
        assertThat(resultList.size).isNotEqualTo(sourceFavourites.size)
        assertThat(resultList.size).isEqualTo(1)
        assertThat(resultList).doesNotContain(sourceFavourites[0])
    }


}