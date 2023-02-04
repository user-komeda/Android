package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.shop_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.button)
        val listView = view.findViewById<ListView>(R.id.list_view)
        val result = (activity as MainActivity?)?.readData()

        val adapter = this.context?.let { it1 ->
            result?.let { it2 ->
                ArrayAdapter<Data>(
                    it1,
                    android.R.layout.simple_list_item_1,
                    it2.toMutableList()
                )
            }
        }

        listView.adapter = adapter
        button.setOnClickListener {
            (activity as MainActivity?)?.test("aaa ")
        }
    }


}