package com.example.learnandroidapp.ui.web_view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learnandroidapp.databinding.FragmentWebViewWithHtmlAssetsBinding

/**
 * WebView関連の確認用
 */
class WebViewWithHtmlAssetsFragment : Fragment() {

    private var binding: FragmentWebViewWithHtmlAssetsBinding? = null

    companion object {
        /** ログ出力用のタグ */
        const val TAG = "WebViewWithHtmlAssetsFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "open WebView")
        binding = FragmentWebViewWithHtmlAssetsBinding.inflate(inflater, container, false)

        binding?.webView?.settings?.javaScriptEnabled = false
        binding?.webView?.settings?.textZoom = 100
        binding?.webView?.loadUrl("file:///android_asset/sample.html");

        return binding?.root
    }
}