package com.adrian.speedrun.main.usecase

import com.adrian.speedrun.main.datasource.RunsDataSource
import com.adrian.speedrun.main.domain.model.*
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetUserTest {

    @Mock
    lateinit var runsDataSourceMock: RunsDataSource

    @InjectMocks
    internal lateinit var getUser: GetUser

    @Before
    fun injectMocks() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getUser() {
        val getUserResponse = UserResponse(
            UserData("id1",
                UserNames("nameTest")
            ))
        `when`(runsDataSourceMock.getUser("id1")).thenReturn(Single.just(getUserResponse))

        val result = getUser.execute("id1")

        val testObserver = TestObserver<UserData>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val resultObserver = testObserver.values()[0]
        assertThat(resultObserver.id, `is`("id1"))
        assertThat(resultObserver.names.international, `is`("nameTest"))
    }

    @Test
    fun getUserWithOneFieldNull() {
        val getUserResponse = UserResponse(
            UserData("id1",
                UserNames(null)
            ))
        `when`(runsDataSourceMock.getUser("id1")).thenReturn(Single.just(getUserResponse))

        val result = getUser.execute("id1")

        val testObserver = TestObserver<UserData>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val resultObserver = testObserver.values()[0]
        assertThat(resultObserver.id, `is`("id1"))
        assertNull(resultObserver.names.international)
    }
}