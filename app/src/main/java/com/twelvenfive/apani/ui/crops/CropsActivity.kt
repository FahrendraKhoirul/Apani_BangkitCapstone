package com.twelvenfive.apani.ui.crops

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.twelvenfive.apani.databinding.ActivityCropsBinding
import com.twelvenfive.apani.ml.CropRecom
import com.twelvenfive.apani.network.response.ForecastdayItem
import com.twelvenfive.apani.ui.ViewModelFactory
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*

class CropsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCropsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var viewModelFactory: ViewModelFactory
    private val cropViewModel: CropViewModel by viewModels { viewModelFactory }
    private var temp = 0.0
    private var hum = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCropsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelFactory = ViewModelFactory.getInstance(binding.root.context)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.icBack.setOnClickListener {
            onBackPressed()
        }
        getMyLastLocation()

        cropViewModel.getForecastWeather().observe(this){result->
            when (result) {
                is com.twelvenfive.apani.network.data.Result.Loading -> {
                    showLoading(true)
                }
                is com.twelvenfive.apani.network.data.Result.Error -> {
                    showLoading(false)
                    Toast.makeText(this, "Gagal Memuat Cuaca", Toast.LENGTH_SHORT).show()
                }
                is com.twelvenfive.apani.network.data.Result.Success -> {
                    showLoading(false)
                    showWeather(result.data as List<ForecastdayItem>)
                    Toast.makeText(this, "Berhasil Memuat Cuaca", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnGenerateCrop.setOnClickListener{
            recomCrop(getTemp(), getHum())
        }
    }

    private fun showWeather(weather: List<ForecastdayItem>){
        var avgTemp = 0.0
        var avgHum = 0.0
        for (i in 0 until weather.size) {
            avgTemp += weather[i].day?.avgtempC as Double
            avgHum += weather[i].day?.avghumidity as Double
        }
        avgTemp = avgTemp / weather.size
        avgHum = avgHum / weather.size

        setTemp(avgTemp)
        setHum(avgHum)

        var avgTempInt:Int = avgTemp.toInt()
        var avgHumInt:Int = avgHum.toInt()

        binding.tvTempValue.text = avgTempInt.toString()
        binding.tvHumValue.text = avgHumInt.toString()
        Log.d("Forecast temp : ", avgTempInt.toString())
        Log.d("Forecast humidity : ", avgHumInt.toString())
    }

    private fun setTemp(value: Double){
        temp = value
    }

    private fun getTemp(): Double{
        return temp
    }

    private fun setHum(value: Double){
        hum = value
    }

    private fun getHum(): Double{
        return temp
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    @SuppressLint("MissingPermission")
    private fun getMyLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            if (location != null) {
                val geocoder = Geocoder(this, Locale.getDefault())
                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )
                val cityName = addresses?.get(0)?.locality

                binding.tvLocation.text = cityName
            }
        }
    }

    private fun recomCrop(temp:Double, hum:Double) {
        try {
            val model = CropRecom.newInstance(applicationContext)
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 2), DataType.FLOAT32)
            val byteBuffer = ByteBuffer.allocateDirect(4 * 2)
            byteBuffer.order(ByteOrder.nativeOrder())
            byteBuffer.putFloat(temp.toFloat())
            byteBuffer.putFloat(hum.toFloat())

            inputFeature0.loadBuffer(byteBuffer)

            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            val confidences = outputFeature0.floatArray
            Log.d("== Crop Recom Result ==", confidences.contentToString())
            // Find the index of the class with the biggest confidence.
            var maxPos = 0
            var maxConfidence = 0f
            for (i in confidences.indices) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i]
                    maxPos = i
                }
            }
            val classes = arrayOf("rice", "maize", "chickpea", "kidneybeans", "pigeonpeas", "mothbeans", "mungbean", "blackgram", "lentil", "pomegranate", "banana", "mango", "grapes",
                "watermelon", "muskmelon", "apple", "orange", "papaya", "coconut", "cotton", "jute", "coffee")
            Log.d("== Crop Recommendation Result ==", classes[maxPos])
            binding.tvRecommendationResult.text = classes[maxPos]

        }catch (e: IOException){
            Log.d("Error Crop", e.message.toString())
        }

    }
}