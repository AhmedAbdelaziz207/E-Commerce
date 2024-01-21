package com.example.domain.exception

import java.io.IOException

class ServerException(serverMessage:String):IOException(serverMessage) {
}