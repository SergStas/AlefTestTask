package com.example.aleftesttask.presentation

import androidx.appcompat.app.AppCompatActivity
import com.example.aleftesttask.network.entity.PicsListResponse

interface IPicScreenContract {
    interface IPresenter {
        fun bind(activity: AbstractPiScreenActivity)

        fun refresh()
    }

    interface IPicScreen {
        fun displayPictures(picsData: PicsListResponse)

        fun showLoading(show: Boolean)

        fun showErrorMessage(message: String)
    }

    abstract class AbstractPiScreenActivity : IPicScreen, AppCompatActivity()
}