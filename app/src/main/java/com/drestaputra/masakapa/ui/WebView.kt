package com.drestaputra.masakapa.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.drestaputra.masakapa.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class WebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)


        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getStringExtra("url").toString())
        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

//        swipe.setOnRefreshListener { webView.reload() }
        val btBack = findViewById<View>(R.id.btBack) as Button
        btBack.setOnClickListener { finish() }
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }
}