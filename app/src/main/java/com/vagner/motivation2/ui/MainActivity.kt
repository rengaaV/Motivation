package com.vagner.motivation2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.vagner.motivation2.MotivationConstants
import com.vagner.motivation2.R
import com.vagner.motivation2.SecurityPreferences
import com.vagner.motivation2.data.Mock
import com.vagner.motivation2.data.Phrase
import com.vagner.motivation2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.INCLUSIVE

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        handlerUserName()

        handlerFilter(R.id.image_inclusive)

        handlerNextPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageEmoticon.setOnClickListener(this)
        binding.imageInclusive.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }


    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handlerNextPhrase()

        } else if (view.id in listOf(R.id.image_inclusive, R.id.image_emoticon, R.id.image_sunny)) {
            handlerFilter(view.id)
        }

    }

    private fun handlerNextPhrase() {

        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handlerFilter(id: Int) {
        binding.imageInclusive.setColorFilter(ContextCompat.getColor(this, R.color.motivation_2))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.motivation_2))
        binding.imageEmoticon.setColorFilter(ContextCompat.getColor(this, R.color.motivation_2))

        when (id) {
            R.id.image_inclusive -> {
                binding.imageInclusive.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.INCLUSIVE
            }
            R.id.image_emoticon -> {
                binding.imageEmoticon.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.EMOTICON
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY

            }
        }

    }

    private fun handlerUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textName.text = "Olá , $name ! "
    }
}