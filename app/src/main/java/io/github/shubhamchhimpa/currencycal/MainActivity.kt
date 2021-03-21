package io.github.shubhamchhimpa.currencycal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import io.github.shubhamchhimpa.currencycal.databinding.ActivityMainBinding
import io.github.shubhamchhimpa.currencycal.main.MainViewModel
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bt.setOnClickListener {
            mainViewModel.convert(binding.amount.text.toString(), "USD", "INR")

        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.conversion.collect { event ->
                when (event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.textView.text = event.resultString
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.textView.text = event.errorText
                    }

                    else -> Unit
                }
            }
        }
    }
}

