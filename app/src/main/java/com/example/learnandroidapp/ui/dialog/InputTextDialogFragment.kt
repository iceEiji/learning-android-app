package com.example.learnandroidapp.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult


class InputTextDialogFragment: DialogFragment() {

    companion object {
        const val TAG = "InputTextDialogFragment"
        private const val ARGS_KEY = "args_key"
        private const val REQUEST_KEY = "request_key"
        private const val RESULT_KEY = "result_key"

        fun show(
            target: Fragment,
            data: String,
            onClickPositive: (text: String) -> Unit
        ) {
            newInstance(data).run {
                target
                    .childFragmentManager
                    .setFragmentResultListener(REQUEST_KEY, target.viewLifecycleOwner) { requestKey, bundle ->
                        if (requestKey != REQUEST_KEY) return@setFragmentResultListener
                        when {
                            bundle.containsKey(RESULT_KEY) -> {
                                // 呼び出し元の処理実行とデータ返却
                                onClickPositive.invoke(bundle.getString(RESULT_KEY, ""))
                            }
                        }
                    }
                show(target.childFragmentManager, "InputTextDialogFragment")
            }
        }

        private fun newInstance(data: String): InputTextDialogFragment {
            val args = Bundle()
            args.putString(ARGS_KEY, data)

            val fragment = InputTextDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 呼び出し元からのデータ受け取り
        val data = arguments?.getString(ARGS_KEY)

        // EditTextの生成
        val editText = AppCompatEditText(requireActivity())
        editText.setText(data)
        editText.requestFocus()

        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("テキスト入力")
            .setView(editText)
            .setPositiveButton("OK") { dialog, id ->
                Log.i(TAG, "dialog:$dialog which:$id")
                setFragmentResult(REQUEST_KEY, bundleOf(
                    RESULT_KEY to editText.text.toString()
                ))
            }
            .setNegativeButton("キャンセル", null)

        val createdDialog = builder.create()
        /* NOTE: builder.create()で生成されたものに設定しないと、キーボードの自動表示が効かないので注意。
            例えば下記のような実装では、キーボードでは表示されない。
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.showSoftInput(editText, InputMethodManager.SHOW_FORCED)
         */
        createdDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return createdDialog
    }
}