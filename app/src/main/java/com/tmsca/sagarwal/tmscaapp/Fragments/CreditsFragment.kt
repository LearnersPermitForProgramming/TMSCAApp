package com.tmsca.sagarwal.tmscaapp.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tmsca.sagarwal.tmscaapp.R

class CreditsFragment : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView : View = inflater.inflate(R.layout.credits, container, false)
        return rootView
    }


}