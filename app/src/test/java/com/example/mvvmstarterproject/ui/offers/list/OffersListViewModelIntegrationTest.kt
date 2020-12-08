package com.example.mvvmstarterproject.ui.offers.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.data.remote.offers.OffersRepository
import com.example.mvvmstarterproject.data.remote.offers.OffersResponse
import com.example.mvvmstarterproject.data.remote.offers.OffersService
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import com.example.mvvmstarterproject.utils.Event
import com.example.mvvmstarterproject.utils.TestContextProvider
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
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
    lateinit var offersListObserver: Observer<List<OffersResponse.Section.Offer>>
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

    private val noInternetError = ApplicationException(
        type = ErrorType.Network.NoInternetConnection,
        errorMessageRes = R.string.error_no_internet_connection
    )

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
        viewModel.offersLiveData.observeForever(offersListObserver)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun `get offers change view state to ERROR if there is no internet connection`() {
        every { connectivityUtils.isNetworkConnected } returns false
        viewModel.getOffersList()
        coVerifyOrder {
            loadingObserver.onChanged(any())
            errorObserver.onChanged(any())
        }
    }

    @Test
    fun `get offers post list of offers`() {
        every { connectivityUtils.isNetworkConnected } returns true
        viewModel.getOffersList()
        val offersResponse = Gson().fromJson(dummyOffersJson, OffersResponse::class.java)
        val offers = arrayListOf<OffersResponse.Section.Offer>()
        offersResponse.sections.forEach {
                section -> offers.addAll(
            section.offers.apply {
                forEachIndexed { index, offer ->
                    offer.sectionTitle = section.title?:""
                    offer.isSectionVisible = index == 0
                }
            }
        )
        }
        coVerify {
            offersListObserver.onChanged(any())
        }
    }

}