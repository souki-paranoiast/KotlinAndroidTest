package com.example.kotlinandroidtest.parts

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.widget.TextView
import android.widget.TimePicker

import java.util.Calendar

// 引数とかでTextViewの参照を持ってくるのはできないっぽい？ Bundle経由じゃないといけないっぽい。コンストラクタとか作ると警告が出る。
// 内容読む感じでは空のコンストラクタを裏で作ったりするようなので、Bundle経由じゃないSetメソッドとかも多分ダメ
// 多分この実装のIDの受け渡しが一番無難な方法。そもそもの設計思想と組み方が違うんだろう。

class TimePickerDialogFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(activity, this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        //時刻が選択されたときの処理
        val t = "%02d:%02d".format(hourOfDay, minute)
        activity.findViewById<TextView>((arguments.get("timePickerRunner") as Int)).text = t

    }

}

