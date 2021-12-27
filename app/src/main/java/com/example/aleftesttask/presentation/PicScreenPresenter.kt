package com.example.aleftesttask.presentation

import com.example.aleftesttask.R
import com.example.aleftesttask.network.ApiAccessObject
import com.example.aleftesttask.network.entity.PicsListResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PicScreenPresenter: IPicScreenContract.IPresenter {
    private var activity: IPicScreenContract.AbstractPiScreenActivity? = null

    override fun bind(activity: IPicScreenContract.AbstractPiScreenActivity) {
        this.activity = activity

        loadPics()
    }

    override fun refresh() =
        loadPics()

    private fun loadPics() {
        activity?.showLoading(true)

        runBlocking {
            launch {
                getApiCall().enqueue(getCallback())
            }
        }
    }

    private fun getCallback() =
        object : Callback<PicsListResponse> {
            override fun onResponse(
                call: Call<PicsListResponse>,
                response: Response<PicsListResponse>,
            ) {
                activity?.displayPictures(response.body() ?: PicsListResponse(emptyList()))
                activity?.showLoading(false)
            }

            override fun onFailure(call: Call<PicsListResponse>, t: Throwable) {
                activity?.showErrorMessage(activity!!.getString(R.string.pics_error_api_failed))
                activity?.showLoading(false)
            }
    }

    private fun getApiCall(): Call<PicsListResponse> =
        ApiAccessObject.cApiClient.getPicUrls()
}