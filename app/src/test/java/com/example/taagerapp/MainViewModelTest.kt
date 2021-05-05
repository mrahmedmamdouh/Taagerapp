package com.example.taagerapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.taagerapp.model.Product
import com.example.taagerapp.model.repository.MainRepository
import com.example.taagerapp.requests.ApiInterface
import com.example.taagerapp.ui.viewmodel.MainViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.timeout
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import java.text.SimpleDateFormat
import java.util.*


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Mock
    lateinit var api: ApiInterface
    private lateinit var mainRepository: MainRepository
    private lateinit var mainViewModel: MainViewModel
    private lateinit var weatherObserver: Observer<List<Product>>

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(api)
        mainViewModel = MainViewModel(
            mainRepository,
            testDispatcher
        )
        weatherObserver = mock()
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testApi() = testDispatcher.runBlockingTest {
        val responseList: MutableList<Product> = mutableListOf()
        val product = Product("1", 1618928797, "name 1", 6)
        responseList.add(product)
        Mockito.`when`(api.getProductsAsync()).thenReturn(responseList)

        mainViewModel.repositoriesLiveData.observeForever(weatherObserver)
        mainViewModel.fetchProducts()
        delay(40)
        verify(weatherObserver, timeout(50)).onChanged(responseList)
    }

    @Test
    fun test_convert_time() {
        val df = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        val date = Date()
        val stringDate = df.format(date)
        val dateToLong = date.time
        val afterString = mainViewModel.convertToTime(dateToLong)
        Assert.assertEquals(stringDate, afterString)
    }
}