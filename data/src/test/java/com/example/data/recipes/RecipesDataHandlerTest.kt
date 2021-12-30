package com.example.data.recipes

import com.example.data.api.RecipesDataHandler
import com.example.data.api.models.RecipeDataItem
import com.example.domain.models.request.OperationResult
import com.example.domain.models.request.RecipesDataRequestResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import retrofit2.Response


@ExperimentalCoroutinesApi
class RecipesDataHandlerTest {

    private lateinit var recipesDataHandler: RecipesDataHandler
    private lateinit var fakeRecipesSaver: FakeRecipesSaver
    private lateinit var fakeFoodRecipesApi: FakeFoodRecipesApi

    @Before
    fun createDataHandler() {
        fakeFoodRecipesApi = FakeFoodRecipesApi()
        fakeRecipesSaver = FakeRecipesSaver()
        recipesDataHandler = RecipesDataHandler(fakeFoodRecipesApi, fakeRecipesSaver)
    }

    @Test
    fun testDataHandler() = runBlockingTest {
        val result = recipesDataHandler.getRecipes(emptyMap())
        assertThat(result, `is`(RecipesDataRequestResult.Success))

        fakeRecipesSaver.testResult = OperationResult.Fail
        val failedResult = recipesDataHandler.getRecipes(emptyMap())
        assertThat(failedResult, `is`(RecipesDataRequestResult.UnknownError))

        fakeFoodRecipesApi.testResponse = Response.success(null)
        val noDataResult = recipesDataHandler.getRecipes(emptyMap())
        assertThat(noDataResult, `is`(RecipesDataRequestResult.NoData))

        val errorResponseBody = "{}".toResponseBody("application/json".toMediaTypeOrNull())
        val mockResponse = Response.error<RecipeDataItem>(402, errorResponseBody)
        fakeFoodRecipesApi.testResponse = mockResponse
        val timeOutResult = recipesDataHandler.getRecipes(emptyMap())
        assertThat(timeOutResult, `is`(RecipesDataRequestResult.ApiKetLimited))

        val etcErrorResponse = Response.error<RecipeDataItem>(404, errorResponseBody)
        fakeFoodRecipesApi.testResponse = etcErrorResponse
        val etcErrorResult = recipesDataHandler.getRecipes(emptyMap())
        assert(etcErrorResult is RecipesDataRequestResult.ErrorWithMessage)
    }
}