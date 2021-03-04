/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _remTime1 = MutableLiveData("Set Time to start Timer")
    val remTime1: LiveData<String> = _remTime1

    fun onRemChanged(newTime: String) {
        _remTime1.value = newTime
    }

    private val _time3 = MutableLiveData("")
    val time3: LiveData<String> = _time3

    fun setTime3(newTime: String) {
        _time3.value = newTime
    }

    private val _time2 = MutableLiveData("")
    val time2: LiveData<String> = _time2

    fun setTime2(newTime: String) {
        _time2.value = newTime
    }

    private val _time1 = MutableLiveData("")
    val time1: LiveData<String> = _time1

    fun setTime1(newTime: String) {
        _time1.value = newTime
    }
}