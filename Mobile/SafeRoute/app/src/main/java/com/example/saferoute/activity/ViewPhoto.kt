package com.example.saferoute.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.FileProvider
import com.example.saferoute.R
import java.io.ByteArrayOutputStream
import java.io.File
import android.content.ContextWrapper
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception


private const val FILE_NAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photoFile: File
class ViewPhoto() : AppCompatActivity() {

    lateinit var photo: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_photo)
        this.callCameraApp()
    }

    fun callCameraApp() {
        val takePictureIntent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)

        val fileProvider = FileProvider.getUriForFile(this, "br.com.safe.route.fileprovider", photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        if(takePictureIntent.resolveActivity(this.packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        } else {
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_LONG).show()
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val takenImage = data?.extras?.get("data") as Bitmap
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            val imageView: AppCompatImageView = findViewById(R.id.imageView)
            this.photo = takenImage
            imageView.setImageBitmap(takenImage)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

//    fun compressImageToBase64(image: Bitmap): String? {
//        val stream = ByteArrayOutputStream()
//        image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//        val compressedImage = stream.toByteArray()
//        return Base64.encodeToString(compressedImage, Base64.DEFAULT)
//    }

    fun tryAgain(v: View) {
        this.callCameraApp()
    }

    fun confirm(v: View) {
        val photoType = intent.getStringExtra("photoType")

        println(photo)

        val intent = Intent()

        when(photoType) {
            "register_document_front" -> this.saveImageOnInternalStorage(this.photo, "photo_register_document_front")
            "register_document_verse" -> this.saveImageOnInternalStorage(this.photo, "photo_register_document_verse")
            else -> this.saveImageOnInternalStorage(this.photo, "photo_register_selfie")
        }

        setResult(RESULT_OK, intent)
        finish()
    }

    fun saveImageOnInternalStorage(bitmapImage: Bitmap, name: String) {
        val cw = ContextWrapper(applicationContext)
        // path to /data/data/yourapp/app_data/imageDir
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        // Create imageDir
        val mypath = File(directory, "$name.jpg")

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        println("Photo saved on: ${directory.absolutePath}")
    }
}