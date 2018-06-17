package com.example.kotlinandroidtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import com.example.kotlinandroidtest.parts.TimePickerDialogFragment
import java.util.*


// 基本的には仮想Andoird端末は立ち上げたままで良いっぽいかも。IntelliJの実行を停止したらアプリケーションのみが終了になる。

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmToggle1.textOff
        // 結局文字列としてしか設定できないっぽい？
        fun newTextFocusChangeListener(timeTextViewParam: EditText): View.OnFocusChangeListener {
            // v はView型だけど、v.textView.textとかにするとダメっぽい イベントのviewじゃないのか.. ということでクロージャ？で強引に対応
            return View.OnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    val timePicker = TimePickerDialogFragment()
                    val bundle = Bundle()
                    bundle.putInt("timePickerRunner", timeTextViewParam.id)
                    timePicker.arguments = bundle
                    timePicker.show(supportFragmentManager, "timePicker")
                } else { // blurの意味
                    // NOP
//
//                    if (TextUtils.isEmpty(timeTextViewParam.text)) {
//                        timeTextViewParam.error = "can not empty!"
//                        return@OnFocusChangeListener
//                    }
//                    val timeValue = timeTextViewParam.text
//
                }
            }
        }
        Arrays.asList(alarmTime1, alarmTime2).forEach{ e: EditText ->
//            e!! // assert的な意味らしい 引数にはデフォルトだと?がつくので必要だが、今回は消してるので不要
            e.keyListener = null // これで入力イベントを除去できるらしい（editable = false的な動きになる
            e.onFocusChangeListener = newTextFocusChangeListener(e)
        }
    }
}
//    var sheepCount: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val greeting = "こんにちは"
//        // javaの場合だと、下の2行っぽい書き方が必要らしい。Rというクラスは自動で生成されるもの。DOM的な認識で良いのかも。多分Resourceの略
//        // ちなみに、idはxml(ここではactivity_main.xml)のtextviewタグにandroid:id="@+id/textviewTest"という属性を設定していてそれと関連付けられる。ちゃんとプロパティ扱いになるのは地味に嬉しい機能か
//        // TextView textView = (TextView) findViewById(R.id.textviewTest)
//        // textView.setText("こんにちは");
//        textviewTest.text = greeting
//
//        val trialTime = Date()
//        val calendar = GregorianCalendar()
//        calendar.time = trialTime
//
//        val hour = calendar.get(Calendar.HOUR_OF_DAY)
//        val message = when(hour) {
//            in 1..11 -> "おはよう"
//            in 12..15 -> "こんにちは"
//            else -> "こんばんは"
//        }
//        val textForSetting = message + "眠れませんか？？？"
//        textviewGreeting.text = textForSetting
//
//        rootLayout.setOnClickListener {
//            sheepCount++
//            val sheepText = "ひつじが$sheepCount 匹"
//            textviewGreeting.text = sheepText
//        }
//    }
//}

//object MainActivity2 {
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val hour = 11
//        val message = when(hour) {
//            in 1..11 -> "おはよう"
//            in 12..15 -> "こんにちは"
//            else -> "こんばんは"
//        }
//        print(message)
//    }
//}
