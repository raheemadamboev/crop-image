package xyz.teamgravity.cropimage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.options
import xyz.teamgravity.cropimage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var imageCropLauncher: ActivityResultLauncher<CropImageContractOptions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher()
        button()
    }

    private fun launcher() {
        imageCropLauncher = registerForActivityResult(CropImageContract()) { result ->
            if (result.isSuccessful) {
                binding.imageI.setImageURI(result.uriContent)
            } else {
                Toast.makeText(this, result.error?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun button() {
        onImage()
    }

    private fun onImage() {
        binding.imageB.setOnClickListener {
            imageCropLauncher.launch(options {
                setAspectRatio(16, 9)
            })
        }
    }
}