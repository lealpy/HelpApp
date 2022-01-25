package com.lealpy.simbirsoft_training.presentation.screens.history

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import com.lealpy.simbirsoft_training.R
import com.lealpy.simbirsoft_training.databinding.FragmentHistoryBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private lateinit var binding : FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)

        binding.test1.setOnClickListener { rxTest1() }
        binding.test2.setOnClickListener { rxTest2() }
        binding.test3.setOnClickListener { rxTest3() }
    }

    private fun rxTest1() {
        val numbers = Observable
            .just(1, 2, 3)
            .doOnNext { number ->
                Log.d(LOG_TAG, "At the doOnNext before subscribeOn. Current item is $number. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { number ->
                Log.d(LOG_TAG, "At the doOnNext after observeOn. Current item is $number. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }

        val numbersSubscription = numbers.subscribe { number ->
            Log.d(LOG_TAG, "At the subscribe. Current item is $number. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
        }
    }

    private fun rxTest2() {
        data class Person (
            val name : String,
            val age : Int
        )

        val age = Observable
            .just(18, 21, 34)
            .doOnNext { number ->
                Log.d(LOG_TAG, "At the doOnNext. Current item is $number. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

        val names = Observable
            .just("Ann", "Jake")
            .doOnNext { name ->
                Log.d(LOG_TAG, "At the doOnNext. Current item is $name. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

        val persons : Observable<Person> = Observable.zip(
            names,
            age,
            {name, age ->
                Person(name, age)
            }
        )
            .doOnNext { person ->
            Log.d(LOG_TAG, "At the doOnNext. Current person is $person. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

        val numbersSubscription = age.subscribe { number ->
            Log.d(LOG_TAG, "At the subscribe. Current item is $number. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
        }

        val namesSubscription = names.subscribe { name ->
            Log.d(LOG_TAG, "At the subscribe. Current item is $name. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
        }

        val personsSubscription = persons.subscribe { person ->
            Log.d(LOG_TAG, "At the subscribe. Current person is $person. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
        }
    }

    private fun rxTest3() {
        val names = Observable
            .just("Ann", "Jake", "Kate")
            .doOnNext { name ->
                Log.d(LOG_TAG, "At the doOnNext before subscribeOn. Current item is $name. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }
            .subscribeOn(Schedulers.computation())
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { name ->
                Log.d(LOG_TAG, "At the doOnNext after observeOn. Current item is $name. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
            }

        val namesSubscription = names.subscribe { name ->
            Log.d(LOG_TAG, "At the subscribe. Current item is $name. CurrentThread is ${Thread.currentThread().name}, id ${Thread.currentThread().id}.")
        }
    }

    companion object {
        private const val LOG_TAG = "MyLog"
    }

}
