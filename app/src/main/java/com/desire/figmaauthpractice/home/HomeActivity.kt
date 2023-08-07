package com.desire.figmaauthpractice.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.desire.figmaauthpractice.retrofit.Retrofit
import com.desire.figmaauthpractice.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: RcvAdapter
    var arrayListOfData = arrayListOf<RcvModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i("test","Home Activity onCreate method")
        /*  arrayListOfData = ArrayList<rcvModel>()

          arrayListOfData.add(rcvModel("TSHIRT", "BRAND NAME: POLO", R.drawable.img_tshirt))
          arrayListOfData.add(rcvModel("SHIRT", "BRAND NAME: ZUDIO", R.drawable.img_shirt))
          arrayListOfData.add(rcvModel("TROUSER", "BRAND NAME: POLO", R.drawable.img_trouser))
          arrayListOfData.add(rcvModel("CAP", "BRAND NAME: POLO", R.drawable.img_cap))

          binding.rcvItems.layoutManager =
              LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
          binding.rcvItems.adapter = rcvAdapter(arrayListOfData)
  */
        initView()
        getProducts()
    //    database = LoginDatabase.getDatabase(this)
    }
   /* override fun onStart() {
        super.onStart()
        Log.i("test","Home Activity onStart method")

    }

    override fun onResume() {
        super.onResume()
        Log.i("test","Home Activity onResume method")

    }

    override fun onPause() {
        super.onPause()
        Log.i("test","Home Activity onPause method")

    }

    override fun onStop() {
        super.onStop()
        Log.i("test","Home Activity onStop method")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("test","Home Activity onDestroy method")

    }*/

    private fun getProducts() {
        var call: Call<ArrayList<RcvModel>> = Retrofit.apiInterface.getData()
        call.enqueue(object : Callback<ArrayList<RcvModel>> {
            override fun onResponse(
                call: Call<ArrayList<RcvModel>>,
                response: Response<ArrayList<RcvModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        arrayListOfData.addAll(it)

                        adapter = RcvAdapter(arrayListOfData)
                        binding.rcvAllItems.adapter = adapter
                        binding.rcvAllItems.layoutManager =
                            GridLayoutManager(this@HomeActivity,2)


                        adapter.onItemClick = { item ->
                            Log.i("test", "${item.id}")
                            callAPIToDeleteProduct(item.id)

                            var index = adapter.dataArrayList.indexOfFirst { it.id == item.id }
                            adapter.deleteItem(index)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<RcvModel>>, t: Throwable) {
                Log.i("Test", "Fail")
            }
        })
    }

    private fun initView() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
    private  fun callAPIToDeleteProduct(id: Int){
        var call: Call<Unit> = Retrofit.apiInterface.deleteProduct(id)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@HomeActivity, "Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@HomeActivity, "Failed to Deleted", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@HomeActivity,"Not Deleted",Toast.LENGTH_SHORT).show()
            }
        })
    }
}