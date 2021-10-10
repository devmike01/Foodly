package com.example.foody

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.foody.features.MainActivityViewModel
import com.example.foody.repository.FoodRepositoryImpl
import com.example.foody.repository.FoodService
import com.example.foody.repository.models.FoodResponse
import com.example.foody.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {
    //Start writing test

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()


    private val foodService = Mockito.mock(FoodService::class.java)

    private val repository = Mockito.mock(FoodRepositoryImpl::class.java)

    private val mainActivityViewModel = MainActivityViewModel(repository)

    private var mainThreadSurrogate: ExecutorCoroutineDispatcher? = null

    private var observer = mock<Observer<Resource<List<FoodResponse>>>>()

    @Captor
    lateinit var argumentCaptor : ArgumentCaptor<Resource<List<FoodResponse>>>

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @Before
    fun init(){
        mainThreadSurrogate = newSingleThreadContext("UI thread")
        mainThreadSurrogate?.run { Dispatchers.setMain(this) }

    }

    @Test
    fun `check that all mocked object are not null`(){
        assertNotNull(foodService)
        assertNotNull(repository)
        assertNotNull(mainActivityViewModel)
    }

    @Test
    fun `test functions for returned data`(){
        val foodList = foodService.getFood()
        val apiFoodList = emptyList<FoodResponse>()
        lenient().`when`(foodService.getFood()).thenReturn(apiFoodList)
        lenient().`when`(repository.getFood()).thenReturn(foodList)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test observer`() = runBlockingTest{
        verifyNoInteractions(observer)
        mainActivityViewModel.getFood()
        mainActivityViewModel.foodLiveData.observeForever(observer)
        verify(observer, atLeastOnce()).onChanged(argumentCaptor.capture())
        assertNotNull(argumentCaptor.value)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate?.close()
        mainThreadSurrogate = null
    }
}