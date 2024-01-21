package com.example.domain.exception

import org.json.JSONException

class JsonFormatException( serverMessage:String):JSONException(serverMessage)