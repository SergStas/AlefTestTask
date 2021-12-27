package com.example.aleftesttask.ui

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aleftesttask.R
import com.example.aleftesttask.network.entity.PicsListResponse
import com.example.aleftesttask.presentation.IPicScreenContract
import com.example.aleftesttask.presentation.PicScreenPresenter
import com.example.aleftesttask.ui.adapters.PicsListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup

import android.widget.RelativeLayout

import android.view.Window
import android.widget.ImageView
import com.squareup.picasso.Picasso
import android.view.WindowManager
import android.view.Gravity
import androidx.core.content.ContextCompat

class MainActivity : IPicScreenContract.AbstractPiScreenActivity() {
    private val presenter: IPicScreenContract.IPresenter = PicScreenPresenter()

    private val adapter = PicsListAdapter { url -> showImage(url) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bind(this)

        pics_swipe_refresh.setOnRefreshListener {
            adapter.submitList(emptyList())
            presenter.refresh()
        }
        setListAdapter()
    }

    override fun displayPictures(picsData: PicsListResponse) {
        val size = if (resources.getBoolean(R.bool.isTablet)) 3 else 2
        val rows = picsData.urls.chunked(size)
        adapter.submitList(rows)
    }

    override fun showLoading(show: Boolean) {
        pics_pb.isVisible = show
        if (!show) pics_swipe_refresh.isRefreshing = false
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setListAdapter() {
        pics_rv.let {
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false,
            )
            it.adapter = this.adapter
        }
    }

    private fun showImage(url: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val imageView = ImageView(this)
        Picasso.get().load(url).placeholder(R.drawable.ic_no_pictures).into(imageView)

        dialog.addContentView(
            imageView,
            RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        val wlp: WindowManager.LayoutParams = window.attributes
        wlp.gravity = Gravity.CENTER
        dialog.window?.attributes = wlp
        dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.black))

        dialog.show()
    }
}