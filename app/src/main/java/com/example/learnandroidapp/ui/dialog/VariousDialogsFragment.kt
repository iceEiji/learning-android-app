package com.example.learnandroidapp.ui.dialog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.learnandroidapp.databinding.FragmentVariousDialogsBinding

/**
 * 色々なダイアログ
 */
class VariousDialogsFragment : Fragment() {

    companion object {
        /** ログ出力用のタグ */
        const val TAG = "VariousDialogsFragment"

    }

    private var binding: FragmentVariousDialogsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        binding = FragmentVariousDialogsBinding.inflate(inflater, container, false)
        binding?.buttonEditTextInDialog?.setOnClickListener {
            InputTextDialogFragment.show(
                this,
                "Sample Text",
                onClickPositive = {
                    Log.i(TAG, "inputText=$it")
                }
            )
        }

        return binding?.root
    }


}