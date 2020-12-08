package com.example.mvvmstarterproject.ui.offers.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.data.remote.offers.OffersRepository
import com.example.mvvmstarterproject.data.remote.offers.OffersResponse
import com.example.mvvmstarterproject.utils.Event
import com.example.mvvmstarterproject.utils.TestContextProvider
import com.example.mvvmstarterproject.utils.createRandomOffer
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.example.mvvmstarterproject.utils.network.Result
import io.mockk.*

@RunWith(JUnit4::class)
class OffersListViewModelTest{
    @get:Rule
    var testRule: TestRule = InstantTaskExecutorRule()
    @MockK
    lateinit var offersRepository: OffersRepository
    @MockK
    lateinit var offersListObserver: Observer<List<OffersResponse.Section.Offer>>
    @MockK
    lateinit var loadingObserver: Observer<Event<Result.Loading>>
    @MockK
    lateinit var errorObserver: Observer<Event<Result.Error>>
    private val contextProvidersTest = TestContextProvider()
    private lateinit var viewModel: OffersListViewModel

    private val section1DummyOffers = arrayListOf<OffersResponse.Section.Offer>(
        createRandomOffer(), createRandomOffer(), createRandomOffer(), createRandomOffer(), createRandomOffer()
    )
    private val section1DummyTitle = "Top Cash Backs"
    private val section2DummyOffers = arrayListOf<OffersResponse.Section.Offer>(
        createRandomOffer(), createRandomOffer(), createRandomOffer()
    )
    private val section2DummyTitle = "Popular"
    private val dummyTitle = "Offers"
    private val dummyOffersResponse = OffersResponse(
        title = dummyTitle,
        sections = arrayListOf(
            OffersResponse.Section(
                title = section1DummyTitle,
                offers = section1DummyOffers
            ),
            OffersResponse.Section(
                title = section2DummyTitle,
                offers = section2DummyOffers
            )
        )
    )
    private val dummyException = ApplicationException(
        type = ErrorType.Unexpected,
        errorMessageRes = R.string.error_unexpected
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = OffersListViewModel(contextProvidersTest, offersRepository)
        viewModel.offersLiveData.observeForever(offersListObserver)
        viewModel.loading.observeForever(loadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @Test
    fun `get offers change loading live data when sending any request`() {
        coEvery { offersRepository.getListOfOffers() } returns Result.Success(dummyOffersResponse)
        viewModel.getOffersList()
        coVerify {
            loadingObserver.onChanged(any())
        }
    }

    @Test
    fun `get offers post data to offers live data`() {
        coEvery { offersRepository.getListOfOffers() } returns Result.Success(dummyOffersResponse)
        viewModel.getOffersList()
        val expectedOffers = arrayListOf<OffersResponse.Section.Offer>()
        expectedOffers.addAll(section1DummyOffers)
        expectedOffers.addAll(section2DummyOffers)
        coVerify {
            offersListObserver.onChanged(expectedOffers)
        }
    }

    @Test
    fun `get offers change error live data if repository throws an error`() {
        coEvery { offersRepository.getListOfOffers() } throws  dummyException
        viewModel.getOffersList()
        coVerify {
            errorObserver.onChanged(any())
        }
    }

}