package by.aderman.tottenhamhotspurfc.adapters

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import by.aderman.tottenhamhotspurfc.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object BindingAdapters {

    @JvmStatic
    @BindingAdapter(value = ["image", "error"], requireAll = false)
    fun loadImage(view: ImageView, imageUrl: String?, error: Drawable) {
        Glide.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.no_image_placeholder)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageDrawable(error)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    view.setImageDrawable(resource)
                    return true
                }
            })
            .into(view)
    }
}