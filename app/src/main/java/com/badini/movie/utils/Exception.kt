package com.badini.movie.utils

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class ConnectionTimeout(message: String) : IOException(message)