package com.desire.figmaauthpractice.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.desire.figmaauthpractice.retrofit.Retrofit
import com.desire.figmaauthpractice.databinding.ActivityHomeBinding
import com.desire.figmaauthpractice.ui.MainActivity
import com.desire.figmaauthpractice.retrofit.DeleteProductResponce
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    lateinit var rcvAdapter: rcvAdapter
    var arrayListOfData = arrayListOf<rcvModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*  arrayListOfData = ArrayList<rcvModel>()

          arrayListOfData.add(rcvModel("TSHIRT", "BRAND NAME: POLO", R.drawable.img_tshirt))
          arrayListOfData.add(rcvModel("SHIRT", "BRAND NAME: ZUDIO", R.drawable.img_shirt))
          arrayListOfData.add(rcvModel("TROUSER", "BRAND NAME: POLO", R.drawable.img_trouser))
          arrayListOfData.add(rcvModel("CAP", "BRAND NAME: POLO", R.drawable.img_cap))

          binding.rcvItems.layoutManager =
              LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
          binding.rcvItems.adapter = rcvAdapter(arrayListOfData)
  */
        ivBack()
        getProducts()
    //    database = LoginDatabase.getDatabase(this)
    }

    private fun getProducts() {
        var call: Call<ArrayList<rcvModel>> = Retrofit.apiInterface.getData()
        call.enqueue(object : Callback<ArrayList<rcvModel>> {
            override fun onResponse(
                call: Call<ArrayList<rcvModel>>,
                response: Response<ArrayList<rcvModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        arrayListOfData.addAll(it)
                        setAdapter()
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<rcvModel>>, t: Throwable) {
                Log.i("Test", "Fail")
            }
        })
    }
    private fun setAdapter() {
        binding.rcvItems.layoutManager =
            GridLayoutManager(this,2)
        binding.rcvItems.adapter = rcvAdapter(arrayListOfData)
    }

    private fun ivBack() {
        binding.ivBack.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    private  fun callAPIToDeleteProduct(id: Int){
        var call: Call<DeleteProductResponce> = Retrofit.apiInterface.deleteProduct(id)
        call.enqueue(object : Callback<DeleteProductResponce> {
            override fun onResponse(
                call: Call<DeleteProductResponce>,
                response: Response<DeleteProductResponce>
            ) {
            }
            override fun onFailure(call: Call<DeleteProductResponce>, t: Throwable) {
            }
        })
    }
}