package com.example.retrofitrxjava.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrxjava.R
import com.example.retrofitrxjava.Retrofit.RetrofitClient
import com.example.retrofitrxjava.adapter.itemAdapter
import com.example.retrofitrxjava.databinding.ActivityMainBinding
import com.example.retrofitrxjava.model.DataModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: itemAdapter

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        getData()
    }

    private fun getData() {
        mCompositeDisposable = CompositeDisposable()
        var data = RetrofitClient.getInstance().getApi().getData()

        mCompositeDisposable?.add(data
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
        // comment
    }

    private fun handleResponse(obj: JsonObject){
        val listjson = obj?.get("entries")?.asJsonArray

        var list = getList(listjson)
        setListinrec(list)
    }

    private fun setListinrec(list: ArrayList<DataModel>) {
        itemAdapter = itemAdapter(this, list)
        binding.rec.setHasFixedSize(true)
        binding.rec.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rec.adapter = itemAdapter
    }

    private fun handleError(t: Throwable){
        Log.w("apicall", "error is $t")
    }

    private fun getList(listjson: JsonArray?): ArrayList<DataModel> {
        val tmplist = arrayListOf<DataModel>()

        for (element in listjson!!){
            val obj = element.asJsonObject
            val dataModel = DataModel(
                obj.get("API").asString,
                obj.get("Description").asString,
                obj.get("Auth").asString,
                obj.get("Category").asString,
                "https://nextalerts.com/wp-content/uploads/2021/10/picasso-apk-5.jpeg",
                12
            )

            if (tmplist.size>99){
                break
            }
            tmplist.add(dataModel)
        }

        return tmplist
    }

}