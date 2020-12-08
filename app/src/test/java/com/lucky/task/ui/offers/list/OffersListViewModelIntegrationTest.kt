package com.lucky.task.ui.offers.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lucky.task.data.remote.offers.OffersRepository
import com.lucky.task.data.remote.offers.OffersResponse
import com.lucky.task.data.remote.offers.OffersService
import com.lucky.task.utils.ConnectivityUtils
import com.lucky.task.utils.Event
import com.lucky.task.utils.TestContextProvider
import com.lucky.task.utils.network.Result
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.impl.annotations.MockK
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class OffersListViewModelIntegrationTest {
    @get:Rule
    val mockWebServer = MockWebServer()
    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    @MockK
    lateinit var connectivityUtils: ConnectivityUtils
    @MockK
    lateinit var pageTitleObserver: Observer<String>
    @MockK
    lateinit var loadingObserver: Observer<Event<Result.Loading>>
    @MockK
    lateinit var errorObserver: Observer<Event<Result.Error>>
    private lateinit var viewModel: OffersListViewModel
    lateinit var repository: OffersRepository
    private val contextProvidersTest = TestContextProvider()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val dummyOffersJson = ClassLoader.getSystemResource("offers.json").readText()

    private val offersService by lazy {
        retrofit.create(OffersService::class.java)
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        mockWebServer.enqueue(
            MockResponse()
                .setBody(dummyOffersJson)
                .setResponseCode(200)
        )
        repository = OffersRepository(contextProvidersTest, offersService = offersService, connectivityUtils = connectivityUtils)
        viewModel = OffersListViewModel(contextProvidersTest, repository)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
        viewModel.offersPageTitle.observeForever(pageTitleObserver)
    }

    @Test
    fun `when get offers called and there is no internet connection loading appears then error displayed and error disappear`() {
        every { connectivityUtils.isNetworkConnected } returns false
        viewModel.getOffersList()
        coVerifyOrder {
            loadingObserver.onChanged(any())
            errorObserver.onChanged(any())
            loadingObserver.onChanged(any())
        }
    }

    @Test
    fun `get offers displays the page title returned from the api`() {
        every { connectivityUtils.isNetworkConnected } returns true
        viewModel.getOffersList()
        val offersResponse = Gson().fromJson(dummyOffersJson, OffersResponse::class.java)

        coVerify {
            pageTitleObserver.onChanged(offersResponse.title)
        }
    }

}