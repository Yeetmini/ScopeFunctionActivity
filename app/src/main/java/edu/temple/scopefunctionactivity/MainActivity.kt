package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Get test data array
        val testDataArray = getTestDataArray()
        println("Test Data Array: $testDataArray")

        // Test averageLessThanMedian method
        val result = averageLessThanMedian(testDataArray.map { it.toDouble() })
        println("Is Average Less Than Median: $result")

        val collection = listOf(1, 2, 3, 4, 5)
        val context: Context = this

        // Test recycledView is null
        val view1 = getView(2, null, collection, context)
        println("View 1: $view1")

        // Test recycledView is a TextView
        val recycledTextView = TextView(context)
        val view2 = getView(3, recycledTextView, collection, context)
        println("View 2: $view2")
    }
    }



    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray(): List<Int> =
        MutableList(10) { Random.nextInt() }
            .apply { sort() }

    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean =
        listOfNumbers.average().let { avg ->
            listOfNumbers.sorted().let { sortedList ->
                if (sortedList.size % 2 == 0)
                    (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
                    sortedList[sortedList.size / 2]
            }.let { median -> avg < median }
        }
    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View =
        recycledView?.let { it as TextView } ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22f
        }.apply {
            text = collection[position].toString()
        }


