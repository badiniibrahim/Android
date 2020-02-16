package com.badini.movie.data.network

enum class Status {
    DONE,
    LOADING,
    ERROR
}

class NetWorkState(val status: Status, val message : String) {

    companion object{
        val DONE: NetWorkState
        val LOADING: NetWorkState
        val ERROR: NetWorkState

        init {
            DONE = NetWorkState(Status.DONE, "Success")
            LOADING = NetWorkState(Status.LOADING, "Running")
            ERROR = NetWorkState(Status.ERROR, "Something went wrong")
        }
    }

}