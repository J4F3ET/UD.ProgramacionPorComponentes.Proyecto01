package com.example.proyecto01.services.util

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Locale

class SoundViewModel:ViewModel() {
    private val _state = mutableStateOf(SoundState())
    val state: State<SoundState> = _state

    private var textToSpeech:TextToSpeech? = null

    fun onTextFieldValue(text:String){
        _state.value = state.value.copy(text = text)
    }
    fun textToSpeech(context: Context){
        _state.value = state.value.copy(
            isButtonEnable = false
        )
        this.textToSpeech = TextToSpeech(context){
            if(it == TextToSpeech.SUCCESS){
                textToSpeech?.let{txtToSpeech ->

                    txtToSpeech.language = Locale("es", "ES")
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        _state.value.text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(
                isButtonEnable = true
            )
        }
    }

}