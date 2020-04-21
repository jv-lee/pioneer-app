package com.lee.pioneer.view.widget.menu

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.content.res.XmlResourceParser
import android.util.AttributeSet
import android.util.Xml
import android.view.InflateException
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.core.view.setPadding
import com.lee.library.utils.LogUtil
import com.lee.library.utils.SizeUtil
import com.lee.pioneer.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

/**
 * @author jv.lee
 * @date 2020/4/21
 * @description 自定义简单功能的 menu解析器 支持标签 (<menu> <item id/title/icon /></menu>)
 */
class CustomMenuInflater(var context: Context) {

    /** Menu tag name in XML.  */
    private val XML_MENU = "menu"

    /** Group tag name in XML.  */
    private val XML_GROUP = "group"

    /** Item tag name in XML.  */
    private val XML_ITEM = "item"

    private val rootView by lazy {
        LinearLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setBackgroundResource(R.drawable.shape_search_bg)
            val padding = SizeUtil.dp2px(context, 10f)
            setPadding(padding)
            orientation = LinearLayout.VERTICAL
        }
    }

    @SuppressLint("ResourceType")
    fun inflate(@MenuRes menuRes: Int) {
        var parser: XmlResourceParser? = null
        try {
            parser = context.resources.getLayout(menuRes)
            val attrs = Xml.asAttributeSet(parser)
            parseMenu(parser, attrs)
        } catch (e: XmlPullParserException) {
            throw InflateException("Error inflating menu XML", e)
        } catch (e: IOException) {
            throw InflateException("Error inflating menu XML", e)
        } finally {
            parser?.close()
        }
    }

    private fun parseMenu(parser: XmlResourceParser, attrs: AttributeSet?) {
        var eventType = parser.eventType
        var tagName: String

        do {
            if (eventType == XmlPullParser.START_TAG) {
                tagName = parser.name
                if (tagName == XML_MENU) {
                    eventType = parser.next()
                    break
                }
                throw RuntimeException("Expecting menu, got $tagName")
            }
            eventType = parser.next()
        } while (eventType != XmlPullParser.END_DOCUMENT)

        var reachedEndOfMenu = false
        while (!reachedEndOfMenu) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    tagName = parser.name
                    if (tagName == XML_ITEM) {
                        readItem(attrs)
                    }
                }
                XmlPullParser.END_TAG -> {
                    tagName = parser.name
                    if (tagName == XML_MENU) {
                        reachedEndOfMenu = true
                    }
                }
                XmlPullParser.END_DOCUMENT -> throw java.lang.RuntimeException("Unexpected end of document")
            }
            eventType = parser.next()
        }
    }

    private fun readItem(attrs: AttributeSet?) {
        val typed: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMenuItem)
        val itemId = typed.getResourceId(R.styleable.CustomMenuItem_android_id, 0)
        val itemIconId = typed.getResourceId(R.styleable.CustomMenuItem_android_icon, 0)
        val itemTitleText = typed.getText(R.styleable.CustomMenuItem_android_title)

//        MenuItem(itemId, itemIconId, itemTitleText.toString())
        val view = TextView(context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.id = itemId
        view.text = itemTitleText
        rootView.addView(view)

        typed.recycle()
    }

    fun buildMenuView(): LinearLayout {
        return rootView
    }

}